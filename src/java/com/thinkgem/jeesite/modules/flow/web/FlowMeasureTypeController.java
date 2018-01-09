/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.flow.entity.FlowMeasureType;
import com.thinkgem.jeesite.modules.flow.service.FlowMeasureTypeService;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;

/**
 * 流量观测按船种分Controller
 * @author Dylan
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/flowMeasureType")
public class FlowMeasureTypeController extends BaseController {

	@Autowired
	private FlowMeasureTypeService flowMeasureTypeService;
	
	@ModelAttribute
	public FlowMeasureType get(@RequestParam(required=false) String id) {
		FlowMeasureType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = flowMeasureTypeService.get(id);
		}
		if (entity == null){
			entity = new FlowMeasureType();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:flowMeasureType:view")
	@RequestMapping(value = {"list", ""})
	public String list(FlowMeasureType flowMeasureType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FlowMeasureType> page = flowMeasureTypeService.findPage(new Page<FlowMeasureType>(request, response), flowMeasureType); 
		model.addAttribute("page", page);
		return "modules/flow/flowMeasureTypeList";
	}

	@RequiresPermissions("flow:flowMeasureType:view")
	@RequestMapping(value = "form")
	public String form(FlowMeasureType flowMeasureType, Model model) {
		model.addAttribute("flowMeasureType", flowMeasureType);
		return "modules/flow/flowMeasureTypeForm";
	}

	@RequiresPermissions("flow:flowMeasureType:edit")
	@RequestMapping(value = "save")
	public String save(FlowMeasureType flowMeasureType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, flowMeasureType)){
			return form(flowMeasureType, model);
		}
		flowMeasureTypeService.save(flowMeasureType);
		addMessage(redirectAttributes, "保存流量观测按船种分成功");
		return "redirect:"+Global.getAdminPath()+"/flow/flowMeasureType/?repage";
	}
	@RequiresPermissions("flow:flowMeasureType:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			OaInportShip ship = new OaInportShip();
            String fileName = "流量观测模板.xls";
    		List<OaInportShip> list = Lists.newArrayList(); list.add(ship);
    		new ExportExcel("流量观测", OaInportShip.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/flow/flowMeasureType?repage";
    }
	 @RequiresPermissions("flow:flowMeasureType:edit")
	 @RequestMapping(value = "import", method=RequestMethod.POST)
	    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
			if(Global.isDemoMode()){
				addMessage(redirectAttributes, "演示模式，不允许操作！");
				return "redirect:" + adminPath + "/flow/flowMeasureType?repage";
			}
			try {
				//excel模板路径  
	            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());  
	            //读取excel模板  
	            HSSFWorkbook wb = new HSSFWorkbook(fs);  
	            //读取了模板内所有sheet内容  
	            HSSFSheet sheet = wb.getSheetAt(0);  
	            int i = 2;
	            try{
	            	flowMeasureTypeService.importReportFromExcel(sheet);
	            	
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            	i++;
	        
	            
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:" + adminPath + "/flow/flowMeasureType/?repage";
	    }
	 
	 @RequiresPermissions("flow:flowMeasureType:view")
		@RequestMapping(value = "analysis")
		public String analysis(FlowMeasureType flowMeasureType, Model model) {
			flowMeasureTypeService.setSectionNameByVal(flowMeasureType);
			if(flowMeasureType.getBeginFlowMeasureDate()==null || flowMeasureType.getEndFlowMeasureDate()==null){
				flowMeasureType.setEndFlowMeasureDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
				flowMeasureType.setBeginFlowMeasureDate(DateUtils.addMonths(flowMeasureType.getEndFlowMeasureDate(), -12));
			}
			List<FlowMeasureType> mc = flowMeasureTypeService.findList(flowMeasureType);
			List<String> dd = new ArrayList<String>();
			List<String> ddjson = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-M");

			Map<String,List<String>> mp = new HashMap<String,List<String>>();
			Map<String,List<FlowMeasureType>> mg = new HashMap<String,List<FlowMeasureType>>();
			List<FlowMeasureType> mts = flowMeasureTypeService.findListBySection(flowMeasureType);
			int count = -1;
			int mmm = -1;
			Calendar cd = Calendar.getInstance();
			for(FlowMeasureType scale : mc){
				cd.setTime(scale.getFlowMeasureDate());
				
				if(mmm != cd.get(Calendar.MONTH)){
					mmm = cd.get(Calendar.MONTH);
					count++;
				}
				List<String> list = mp.get(scale.getSectionName());
				List<FlowMeasureType> list1 = mg.get(scale.getSectionName());
				if(list==null) {
					list = new ArrayList<String>();
					for(int i=0;i<count;i++){
						list.add("/");
					}
				}
				if(list1 == null){
					list1 = new ArrayList<FlowMeasureType>();
					
				}
				list1.add(scale);
				if(flowMeasureType.getFlowType()==null){
					String[] type = {"1"};
					flowMeasureType.setFlowType(type);
				}
				String val = flowMeasureTypeService.getFlowValue(scale,flowMeasureType.getFlowType());
				list.add(val);
				mp.put(scale.getSectionName(), list);
				mg.put(scale.getSectionName(), list1);
			}
			model.addAttribute("flowMeasureTypeList", mp);
			int len  = 0;
			int max = 0;
			for(List<FlowMeasureType> list : mg.values()){
				len = list.size();
				if(len > max){
					max = len;
					dd.clear();
					ddjson.clear();
					for(FlowMeasureType l : list){
						dd.add(sdf.format(l.getFlowMeasureDate()));
						ddjson.add(sdf1.format(l.getFlowMeasureDate()));
					}
				}	
				}
	
			model.addAttribute("dd", dd);
			model.addAttribute("ddjson",JSON.toJSON(ddjson));
			List<String> totalList = new ArrayList<String>();
			for(FlowMeasureType mt : mts){
				totalList.add(flowMeasureTypeService.getFlowValue(mt,flowMeasureType.getFlowType()));
			}
			model.addAttribute("mts",totalList);
			model.addAttribute("mtsjson",JSON.toJSON(totalList));

			model.addAttribute("listjson",JSON.toJSON(mp));

			return "modules/flow/flowMeasureTypeStat";
		}
	@RequiresPermissions("flow:flowMeasureType:edit")
	@RequestMapping(value = "delete")
	public String delete(FlowMeasureType flowMeasureType, RedirectAttributes redirectAttributes) {
		flowMeasureTypeService.delete(flowMeasureType);
		addMessage(redirectAttributes, "删除流量观测按船种分成功");
		return "redirect:"+Global.getAdminPath()+"/flow/flowMeasureType/?repage";
	}

}