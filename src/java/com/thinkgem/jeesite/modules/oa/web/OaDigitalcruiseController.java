/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.restlet.Request;
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
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseSumStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.PortData;
import com.thinkgem.jeesite.modules.oa.service.OaDigitalcruiseService;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 电子巡查相关表Controller
 * @author 王浩宇
 * @version 2017-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaDigitalcruise")
public class OaDigitalcruiseController extends BaseController {

	@Autowired
	private OaDigitalcruiseService oaDigitalcruiseService;
	
	@ModelAttribute
	public OaDigitalcruise get(@RequestParam(required=false) String id) {
		OaDigitalcruise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaDigitalcruiseService.get(id);
		}
		if (entity == null){
			entity = new OaDigitalcruise();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaDigitalcruise:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaDigitalcruise oaDigitalcruise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaDigitalcruise> page = oaDigitalcruiseService.findPage(new Page<OaDigitalcruise>(request, response), oaDigitalcruise); 
		for(OaDigitalcruise p : page.getList()){
			if(DateUtils.isToday(p.getCreateDate())){
				p.setEditable(true);
			}
		}
		model.addAttribute("page", page);
		return "modules/oa/oaDigitalcruiseList";
	}

	@RequiresPermissions("oa:oaDigitalcruise:view")
	@RequestMapping(value = "form")
	public String form(OaDigitalcruise oaDigitalcruise,Model model,RedirectAttributes redirectAttributes) {
		if(StringUtils.isBlank(oaDigitalcruise.getId())){
			//取出前一天的数据
			List<OaDigitalcruise> lists = oaDigitalcruiseService.getDigitalcruiseDataByDate(oaDigitalcruise, DateUtils.getNextDay(new Date()));
					if(lists != null && lists.size()>0){
						oaDigitalcruise = lists.get(0);
						oaDigitalcruise.setId(null);
						oaDigitalcruise.setEditable(true);
					}else{
						oaDigitalcruise.setEditable(true);
					}
		}else if(!DateUtils.isToday(oaDigitalcruise.getCreateDate())){
			addMessage(redirectAttributes, "只能在当日17时之前修改当日数据！");
			return "redirect:"+Global.getAdminPath()+"/oa/oaDigitalcruise/?repage";
		}
		model.addAttribute("oaDigitalcruise", oaDigitalcruise);
		return "modules/oa/oaDigitalcruiseForm";
	}

	@RequiresPermissions("oa:oaDigitalcruise:view")
	@RequestMapping(value = "formSeen")
	public String formSeen(OaDigitalcruise oaDigitalcruise,Model model,RedirectAttributes redirectAttributes) {
		oaDigitalcruise.setEditable(false);
		model.addAttribute("oaDigitalcruise", oaDigitalcruise);
		return "modules/oa/oaDigitalcruiseForm";
	}
	
	@RequiresPermissions("oa:oaDigitalcruise:edit")
	@RequestMapping(value = "save")
	public String save(OaDigitalcruise oaDigitalcruise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaDigitalcruise)){
			return form(oaDigitalcruise, model,redirectAttributes);
		}
		oaDigitalcruiseService.save(oaDigitalcruise);
		addMessage(redirectAttributes, "保存电子巡查数据成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaDigitalcruise/?repage";
	}
	
	@RequiresPermissions("oa:oaDigitalcruise:edit")
	@RequestMapping(value = "delete")
	public String delete(OaDigitalcruise oaDigitalcruise, RedirectAttributes redirectAttributes) {
		if(DateUtils.isToday(oaDigitalcruise.getCreateDate())){
			oaDigitalcruiseService.delete(oaDigitalcruise);
			addMessage(redirectAttributes, "删除电子巡查数据成功");
			return "redirect:"+Global.getAdminPath()+"/oa/oaDigitalcruise/?repage";
		}else{
			addMessage(redirectAttributes, "只能删除当天数据");
			return "redirect:"+Global.getAdminPath()+"/oa/oaDigitalcruise/?repage";
		}
	}

	
	@RequestMapping(value = "portSumStat")
	public String portSumStat(@RequestParam Map<String, Object> paramMap,HttpServletRequest request, PortData portData,Model model,RedirectAttributes redirectAttributes) {
		//获取部门id
		String depId = request.getParameter("office.id"); 
		portData.setOfficeId(depId);
		PortData dataList = oaDigitalcruiseService.getPortStatics(portData);
		model.addAttribute("statics", dataList);
		model.addAttribute("officeId", paramMap.get("office.id"));
		model.addAttribute("officeName",paramMap.get("office.name"));
		
		return "modules/oa/oaPortSumStat";
	}
}