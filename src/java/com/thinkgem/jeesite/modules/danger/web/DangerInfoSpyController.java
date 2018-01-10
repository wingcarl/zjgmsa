/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestDetail;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfo;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfoSpy;
import com.thinkgem.jeesite.modules.danger.service.DangerInfoSpyService;

/**
 * danger_spyController
 * @author dylan wang
 * @version 2017-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/danger/dangerInfoSpy")
public class DangerInfoSpyController extends BaseController {

	@Autowired
	private DangerInfoSpyService dangerInfoSpyService;
	
	@ModelAttribute
	public DangerInfoSpy get(@RequestParam(required=false) String id) {
		DangerInfoSpy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dangerInfoSpyService.get(id);
		}
		if (entity == null){
			entity = new DangerInfoSpy();
		}
		return entity;
	}
	
	@RequiresPermissions("danger:dangerInfoSpy:view")
	@RequestMapping(value = {"list", ""})
	public String list(DangerInfoSpy dangerInfoSpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		String dangerInfo = request.getParameter("dangerInfo");
		dangerInfoSpy.setDangerInfo(((new DangerInfo(dangerInfo))));
		Page<DangerInfoSpy> page = dangerInfoSpyService.findPage(new Page<DangerInfoSpy>(request, response), dangerInfoSpy); 
		model.addAttribute("page", page);
		model.addAttribute("dangerInfo",dangerInfo);
		return "modules/danger/dangerInfoSpyList";
	}

	@RequiresPermissions("danger:dangerInfoSpy:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, DangerInfoSpy dangerInfoSpy, Model model) {
		String dangerInfo = request.getParameter("detail");
		dangerInfoSpy.setDangerInfo((new DangerInfo(dangerInfo)));
		model.addAttribute("dangerInfoSpy", dangerInfoSpy);
		return "modules/danger/dangerInfoSpyForm";
	}

	@RequiresPermissions("danger:dangerInfoSpy:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,DangerInfoSpy dangerInfoSpy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dangerInfoSpy)){
			return form(request,dangerInfoSpy, model);
		}
	
		dangerInfoSpyService.save(dangerInfoSpy);
		addMessage(redirectAttributes, "保存隐患跟踪情况成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInfoSpy/?repage&dangerInfo="+dangerInfoSpy.getDangerInfo().getId();
	}
	
	@RequiresPermissions("danger:dangerInfoSpy:edit")
	@RequestMapping(value = "delete")
	public String delete(DangerInfoSpy dangerInfoSpy, RedirectAttributes redirectAttributes) {
		dangerInfoSpyService.delete(dangerInfoSpy);
		addMessage(redirectAttributes, "删除隐患跟踪情况成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInfoSpy/?repage";
	}

}