/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.web;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStopPlan;
import com.thinkgem.jeesite.modules.cruise.service.CruiseStopPlanService;

/**
 * 停航检修计划Controller
 * @author Dylan Wang
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/cruise/cruiseStopPlan")
public class CruiseStopPlanController extends BaseController {

	@Autowired
	private CruiseStopPlanService cruiseStopPlanService;
	
	@ModelAttribute
	public CruiseStopPlan get(@RequestParam(required=false) String id) {
		CruiseStopPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cruiseStopPlanService.get(id);
		}
		if (entity == null){
			entity = new CruiseStopPlan();
		}
		return entity;
	}
	
	@RequiresPermissions("cruise:cruiseStopPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(CruiseStopPlan cruiseStopPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(cruiseStopPlan.getBeginStopDate()==null || cruiseStopPlan.getEndStopDate()==null){
			cruiseStopPlan.setBeginStopDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
			cruiseStopPlan.setEndStopDate(DateUtils.addMonths(cruiseStopPlan.getBeginStopDate(), 1));
		}
		Page<CruiseStopPlan> page = cruiseStopPlanService.findPage(new Page<CruiseStopPlan>(request, response), cruiseStopPlan); 
		model.addAttribute("page", page);
		return "modules/cruise/cruiseStopPlanList";
	}

	@RequiresPermissions("cruise:cruiseStopPlan:view")
	@RequestMapping(value = "form")
	public String form(CruiseStopPlan cruiseStopPlan, Model model) {
		model.addAttribute("cruiseStopPlan", cruiseStopPlan);
		return "modules/cruise/cruiseStopPlanForm";
	}

	@RequiresPermissions("cruise:cruiseStopPlan:edit")
	@RequestMapping(value = "save")
	public String save(CruiseStopPlan cruiseStopPlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cruiseStopPlan)){
			return form(cruiseStopPlan, model);
		}
		
		if(cruiseStopPlanService.isUnique(cruiseStopPlan)){
			cruiseStopPlanService.save(cruiseStopPlan);
			addMessage(redirectAttributes, "保存停航检修计划成功");
			return "redirect:"+Global.getAdminPath()+"/cruise/cruiseStopPlan/?repage";
		}else{
			model.addAttribute("message", "您所选的日期，已有相近的海巡艇停航检修，请选择其它日期。或您已填写本月停航计划，请至列表页面修改");
			return "modules/cruise/cruiseStopPlanForm";
		}
		
		
	}
	
	@RequiresPermissions("cruise:cruiseStopPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(CruiseStopPlan cruiseStopPlan, RedirectAttributes redirectAttributes) {
		cruiseStopPlanService.delete(cruiseStopPlan);
		addMessage(redirectAttributes, "删除停航检修计划成功");
		return "redirect:"+Global.getAdminPath()+"/cruise/cruiseStopPlan/?repage";
	}

}