/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.danger.entity.DangerInfo;
import com.thinkgem.jeesite.modules.danger.service.DangerCompanyService;
import com.thinkgem.jeesite.modules.danger.service.DangerInfoService;

/**
 * 隐患信息表Controller
 * @author Dylan Wang
 * @version 2018-01-08
 */
@Controller
@RequestMapping(value = "${adminPath}/danger/dangerInfo")
public class DangerInfoController extends BaseController {

	@Autowired
	private DangerInfoService dangerInfoService;
	@Autowired
	private DangerCompanyService dangerCompanyService;
	
	@ModelAttribute
	public DangerInfo get(@RequestParam(required=false) String id) {
		DangerInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dangerInfoService.get(id);
		}
		if (entity == null){
			entity = new DangerInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("danger:dangerInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(DangerInfo dangerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DangerInfo> page = dangerInfoService.findPage(new Page<DangerInfo>(request, response), dangerInfo); 
		model.addAttribute("page", page);
		return "modules/danger/dangerInfoList";
	}

	@RequiresPermissions("danger:dangerInfo:view")
	@RequestMapping(value = "form")
	public String form(DangerInfo dangerInfo, Model model) {
		List<DangerCompany> companyList = dangerCompanyService.findList(new DangerCompany());
		model.addAttribute("dangerInfo", dangerInfo);
		model.addAttribute("companyList", companyList);
		return "modules/danger/dangerInfoForm";
	}

	@RequiresPermissions("danger:dangerInfo:edit")
	@RequestMapping(value = "save")
	public String save(DangerInfo dangerInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dangerInfo)){
			return form(dangerInfo, model);
		}
		dangerInfoService.save(dangerInfo);
		addMessage(redirectAttributes, "保存隐患信息表成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInfo/?repage";
	}
	
	@RequiresPermissions("danger:dangerInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(DangerInfo dangerInfo, RedirectAttributes redirectAttributes) {
		dangerInfoService.delete(dangerInfo);
		addMessage(redirectAttributes, "删除隐患信息表成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInfo/?repage";
	}

}