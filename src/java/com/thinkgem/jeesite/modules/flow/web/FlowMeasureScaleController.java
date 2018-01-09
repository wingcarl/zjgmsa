/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.flow.entity.FlowMeasureScale;
import com.thinkgem.jeesite.modules.flow.service.FlowMeasureScaleService;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 流量观测数据尺度Controller
 * @author Dylan Wang
 * @version 2017-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/flowMeasureScale")
public class FlowMeasureScaleController extends BaseController {

	@Autowired
	private FlowMeasureScaleService flowMeasureScaleService;
	
	@ModelAttribute
	public FlowMeasureScale get(@RequestParam(required=false) String id) {
		FlowMeasureScale entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = flowMeasureScaleService.get(id);
		}
		if (entity == null){
			entity = new FlowMeasureScale();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:flowMeasureScale:view")
	@RequestMapping(value = {"list", ""})
	public String list(FlowMeasureScale flowMeasureScale, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FlowMeasureScale> page = flowMeasureScaleService.findPage(new Page<FlowMeasureScale>(request, response), flowMeasureScale); 
		model.addAttribute("page", page);
		return "modules/flow/flowMeasureScaleList";
	}

	@RequiresPermissions("flow:flowMeasureScale:view")
	@RequestMapping(value = "form")
	public String form(FlowMeasureScale flowMeasureScale, Model model) {
		model.addAttribute("flowMeasureScale", flowMeasureScale);
		return "modules/flow/flowMeasureScaleForm";
	}
	
	@RequiresPermissions("flow:flowMeasureScale:view")
	@RequestMapping(value = "analysis")
	public String analysis(FlowMeasureScale flowMeasureScale, Model model) {
		flowMeasureScaleService.setSectionNameByVal(flowMeasureScale);
		if(flowMeasureScale.getBeginFindTime()==null || flowMeasureScale.getEndFindTime()==null){
			flowMeasureScale.setEndFindTime(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
			flowMeasureScale.setBeginFindTime(DateUtils.addMonths(flowMeasureScale.getEndFindTime(), -12));
		}
		List<FlowMeasureScale> mc = flowMeasureScaleService.findList(flowMeasureScale);
		List<String> dd = new ArrayList<String>();
		List<String> ddjson = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-M");

		Map<String,List<String>> mp = new HashMap<String,List<String>>();
		Map<String,List<FlowMeasureScale>> mg = new HashMap<String,List<FlowMeasureScale>>();
		List<FlowMeasureScale> mts = flowMeasureScaleService.findListBySection(flowMeasureScale);
		
		for(FlowMeasureScale scale : mc){
			 
			List<String> list = mp.get(scale.getSectionName());
			List<FlowMeasureScale> list1 = mg.get(scale.getSectionName());
			if(list==null) 
				list = new ArrayList<String>();
			if(list1 == null)
				list1 = new ArrayList<FlowMeasureScale>();
			list1.add(scale);
			if(flowMeasureScale.getFlowType()==null){
				String[] type = {"1"};
				flowMeasureScale.setFlowType(type);
			}
			String val = flowMeasureScaleService.getFlowValue(scale,flowMeasureScale.getFlowType());
			list.add(val);
			mp.put(scale.getSectionName(), list);
			mg.put(scale.getSectionName(), list1);
		}
		model.addAttribute("flowMeasureScaleList", mp);
		for(List<FlowMeasureScale> list : mg.values()){
			for(FlowMeasureScale l : list){
				dd.add(sdf.format(l.getFlowMeasureDate()));
				ddjson.add(sdf1.format(l.getFlowMeasureDate()));
			}
			break;
		}		
		model.addAttribute("dd", dd);
		model.addAttribute("ddjson",JSON.toJSON(ddjson));
		List<String> totalList = new ArrayList<String>();
		for(FlowMeasureScale mt : mts){
			totalList.add(flowMeasureScaleService.getFlowValue(mt,flowMeasureScale.getFlowType()));
		}
		model.addAttribute("mts",totalList);
		model.addAttribute("mtsjson",JSON.toJSON(totalList));

		model.addAttribute("listjson",JSON.toJSON(mp));

		return "modules/flow/flowMeasureScaleStat";
	}
	@RequiresPermissions("flow:flowMeasureScale:edit")
	@RequestMapping(value = "save")
	public String save(FlowMeasureScale flowMeasureScale, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, flowMeasureScale)){
			return form(flowMeasureScale, model);
		}
		flowMeasureScaleService.save(flowMeasureScale);
		addMessage(redirectAttributes, "保存流量观测尺度数据成功");
		return "redirect:"+Global.getAdminPath()+"/flow/flowMeasureScale/?repage";
	}
	@RequiresPermissions("flow:flowMeasureScale:view")
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
		return "redirect:" + adminPath + "/flow/flowMeasureScale?repage";
    }
	 @RequiresPermissions("flow:flowMeasureScale:edit")
	 @RequestMapping(value = "import", method=RequestMethod.POST)
	    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
			if(Global.isDemoMode()){
				addMessage(redirectAttributes, "演示模式，不允许操作！");
				return "redirect:" + adminPath + "/flow/flowMeasureScale?repage";
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
	            	flowMeasureScaleService.importReportFromExcel(sheet);
	            	
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            	i++;
	        
	            
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:" + adminPath + "/flow/flowMeasureScale/?repage";
	    }
	@RequiresPermissions("flow:flowMeasureScale:edit")
	@RequestMapping(value = "delete")
	public String delete(FlowMeasureScale flowMeasureScale, RedirectAttributes redirectAttributes) {
		flowMeasureScaleService.delete(flowMeasureScale);
		addMessage(redirectAttributes, "删除流量观测尺度数据成功");
		return "redirect:"+Global.getAdminPath()+"/flow/flowMeasureScale/?repage";
	}

}