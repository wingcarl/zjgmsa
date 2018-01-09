/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.intercept.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.intercept.entity.GridManaIntercept;
import com.thinkgem.jeesite.modules.intercept.service.GridManaInterceptService;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 拦截船舶情况Controller
 * @author Dylan Wang
 * @version 2017-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/intercept/gridManaIntercept")
public class GridManaInterceptController extends BaseController {

	@Autowired
	private GridManaInterceptService gridManaInterceptService;
	@Autowired
	private OaCruisedataService oaCruisedataService;
	
	@ModelAttribute
	public GridManaIntercept get(@RequestParam(required=false) String id) {
		GridManaIntercept entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gridManaInterceptService.get(id);
		}
		if (entity == null){
			entity = new GridManaIntercept();
		}
		return entity;
	}
	
	@RequiresPermissions("intercept:gridManaIntercept:view")
	@RequestMapping(value = {"list", ""})
	public String list(GridManaIntercept gridManaIntercept, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GridManaIntercept> page = gridManaInterceptService.findPage(new Page<GridManaIntercept>(request, response), gridManaIntercept); 
		model.addAttribute("page", page);
		return "modules/intercept/gridManaInterceptList";
	}
	
	@RequiresPermissions("intercept:gridManaIntercept:view")
	@RequestMapping(value = {"listView"})
	public String listView(GridManaIntercept gridManaIntercept, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GridManaIntercept> page = gridManaInterceptService.findPage(new Page<GridManaIntercept>(request, response), gridManaIntercept); 
		model.addAttribute("page", page);
		OaCruiseStat stat = new OaCruiseStat();
		//stat.setBeginHappenDate(beginHappenDate);
		stat.setBeginHappenDate(gridManaIntercept.getBeginInterceptDate());
		stat.setEndHappenDate(gridManaIntercept.getEndInterceptDate());
		String grid = request.getParameter("grid");
		
		if("7".equals(grid)){
			stat.setCreate_dept(new Office("6b2bced3b2e7435795159f20259c478a"));
		}else if("11".equals(grid)){
			stat.setCreate_dept(new Office("04082588727246b4816ee441713252b2"));
		}else if("10".equals(grid)){
			stat.setCreate_dept(new Office("f573695109e74c95b846702fb8b4dbb2"));
		}
		List<OaCruiseStat> dataList = new ArrayList<OaCruiseStat>();
		dataList = oaCruisedataService.getStaticsMonthly(stat);
		Integer counter = gridManaInterceptService.getCount(gridManaIntercept);
		stat = dataList.get(0);
		model.addAttribute("stat", stat);
		model.addAttribute("grid", grid);
		model.addAttribute("counter", counter);
		return "modules/intercept/gridManaInterceptListView";
	}
	
	@RequiresPermissions("intercept:gridManaIntercept:view")
	@RequestMapping(value={"listDetail"})
	public String listDetail(GridManaIntercept gridManaIntercept,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<GridManaIntercept> page = gridManaInterceptService.findPage(new Page<GridManaIntercept>(request,response), gridManaIntercept);
		model.addAttribute("page",page);
		String grid = request.getParameter("grid");
		model.addAttribute("grid",grid);
		return "modules/intercept/gridManaInterceptListDetail";
	}
	
	
	@RequiresPermissions("intercept:gridManaIntercept:view")
	@RequestMapping(value = "form")
	public String form(GridManaIntercept gridManaIntercept, Model model) {
		model.addAttribute("gridManaIntercept", gridManaIntercept);
		return "modules/intercept/gridManaInterceptForm";
	}

	@RequiresPermissions("intercept:gridManaIntercept:edit")
	@RequestMapping(value = "save")
	public String save(GridManaIntercept gridManaIntercept, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gridManaIntercept)){
			return form(gridManaIntercept, model);
		}
		gridManaInterceptService.save(gridManaIntercept);
		addMessage(redirectAttributes, "保存拦截船舶情况成功");
		return "redirect:"+Global.getAdminPath()+"/intercept/gridManaIntercept/?repage";
	}
	
	@RequiresPermissions("intercept:gridManaIntercept:edit")
	@RequestMapping(value = "delete")
	public String delete(GridManaIntercept gridManaIntercept, RedirectAttributes redirectAttributes) {
		gridManaInterceptService.delete(gridManaIntercept);
		addMessage(redirectAttributes, "删除拦截船舶情况成功");
		return "redirect:"+Global.getAdminPath()+"/intercept/gridManaIntercept/?repage";
	}
	
	@RequiresPermissions("intercept:gridManaIntercept:view")
	@RequestMapping(value = "viewMap")
	public String viewMap(GridManaIntercept gridManaIntercept, RedirectAttributes redirectAttributes) {
		
		return "modules/intercept/index";
	}
	
	/**
	 * 导入数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("intercept:gridManaIntercept:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<GridManaIntercept> list = ei.getDataList(GridManaIntercept.class);
			for (GridManaIntercept data : list){
				if(StringUtils.isNotBlank(data.getInterceptShipName())){
					try{
						
						BeanValidators.validateWithException(validator, data);
					
						/*if(StringUtils.isNotBlank(ship.getTelephone())){
							BigDecimal bd = new BigDecimal(ship.getTelephone()); 
							ship.setTelephone(bd.toPlainString());
						}*/
						gridManaInterceptService.save(data);;
						successNum++;
					
					}catch(ConstraintViolationException ex){
						failureMsg.append("<br/>登录名 "+data.getInterceptShipName()+" 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList){
							failureMsg.append(message+"; ");
							failureNum++;
						}
					}catch (Exception ex) {
						failureMsg.append("<br/>登录名 "+data.getInterceptShipName()+" 导入失败："+ex.getMessage());
					}
				}
				
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条在港船舶，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/intercept/gridManaIntercept/list?repage";
    }
	/**
	 * 下载数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaInportShip:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			GridManaIntercept gridManaIntercept = new GridManaIntercept();
            String fileName = "导入模板.xls";
    		List<GridManaIntercept> list = Lists.newArrayList(); list.add(gridManaIntercept);
    		new ExportExcel("每日在港船舶动态数据", GridManaIntercept.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/intercept/gridManaIntercept?repage";
    }
	
}