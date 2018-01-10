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
import com.thinkgem.jeesite.modules.danger.entity.DangerCompany;
import com.thinkgem.jeesite.modules.danger.service.DangerCompanyService;

/**
 * 隐患排查对象信息Controller
 * @author Dylan Wang
 * @version 2018-01-08
 */
@Controller
@RequestMapping(value = "${adminPath}/danger/dangerCompany")
public class DangerCompanyController extends BaseController {

	@Autowired
	private DangerCompanyService dangerCompanyService;
	
	@ModelAttribute
	public DangerCompany get(@RequestParam(required=false) String id) {
		DangerCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dangerCompanyService.get(id);
		}
		if (entity == null){
			entity = new DangerCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("danger:dangerCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(DangerCompany dangerCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DangerCompany> page = dangerCompanyService.findPage(new Page<DangerCompany>(request, response), dangerCompany); 
		model.addAttribute("page", page);
		return "modules/danger/dangerCompanyList";
	}

	@RequiresPermissions("danger:dangerCompany:view")
	@RequestMapping(value = "form")
	public String form(DangerCompany dangerCompany, Model model) {
		model.addAttribute("dangerCompany", dangerCompany);
		return "modules/danger/dangerCompanyForm";
	}

	@RequiresPermissions("danger:dangerCompany:edit")
	@RequestMapping(value = "save")
	public String save(DangerCompany dangerCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dangerCompany)){
			return form(dangerCompany, model);
		}
		dangerCompanyService.save(dangerCompany);
		addMessage(redirectAttributes, "保存隐患排查对象信息成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerCompany/?repage";
	}
	
	@RequiresPermissions("danger:dangerCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(DangerCompany dangerCompany, RedirectAttributes redirectAttributes) {
		dangerCompanyService.delete(dangerCompany);
		addMessage(redirectAttributes, "删除隐患排查对象信息成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerCompany/?repage";
	}

}