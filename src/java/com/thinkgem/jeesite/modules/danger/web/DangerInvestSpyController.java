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
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestSpy;
import com.thinkgem.jeesite.modules.danger.service.DangerInvestSpyService;

/**
 * danger_spyController
 * @author dylan wang
 * @version 2017-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/danger/dangerInvestSpy")
public class DangerInvestSpyController extends BaseController {

	@Autowired
	private DangerInvestSpyService dangerInvestSpyService;
	
	@ModelAttribute
	public DangerInvestSpy get(@RequestParam(required=false) String id) {
		DangerInvestSpy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dangerInvestSpyService.get(id);
		}
		if (entity == null){
			entity = new DangerInvestSpy();
		}
		return entity;
	}
	
	@RequiresPermissions("danger:dangerInvestSpy:view")
	@RequestMapping(value = {"list", ""})
	public String list(DangerInvestSpy dangerInvestSpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		String dangerInvestDetail = request.getParameter("dangerInvest");
		dangerInvestSpy.setDangerInvestDetail(new DangerInvestDetail(dangerInvestDetail));
		Page<DangerInvestSpy> page = dangerInvestSpyService.findPage(new Page<DangerInvestSpy>(request, response), dangerInvestSpy); 
		model.addAttribute("page", page);
		model.addAttribute("dangerDetail",dangerInvestDetail);
		return "modules/danger/dangerInvestSpyList";
	}

	@RequiresPermissions("danger:dangerInvestSpy:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, DangerInvestSpy dangerInvestSpy, Model model) {
		String dangerInvestDetail = request.getParameter("detail");
		dangerInvestSpy.setDangerInvestDetail(new DangerInvestDetail(dangerInvestDetail));
		model.addAttribute("dangerInvestSpy", dangerInvestSpy);
		return "modules/danger/dangerInvestSpyForm";
	}

	@RequiresPermissions("danger:dangerInvestSpy:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,DangerInvestSpy dangerInvestSpy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dangerInvestSpy)){
			return form(request,dangerInvestSpy, model);
		}
	
		dangerInvestSpyService.save(dangerInvestSpy);
		addMessage(redirectAttributes, "保存隐患跟踪情况成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInvestSpy/?repage&dangerInvest="+dangerInvestSpy.getDangerInvestDetail().getId();
	}
	
	@RequiresPermissions("danger:dangerInvestSpy:edit")
	@RequestMapping(value = "delete")
	public String delete(DangerInvestSpy dangerInvestSpy, RedirectAttributes redirectAttributes) {
		dangerInvestSpyService.delete(dangerInvestSpy);
		addMessage(redirectAttributes, "删除隐患跟踪情况成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInvestSpy/?repage";
	}

}