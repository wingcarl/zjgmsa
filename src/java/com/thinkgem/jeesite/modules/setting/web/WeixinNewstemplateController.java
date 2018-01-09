/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.web;

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
import com.thinkgem.jeesite.modules.setting.entity.WeixinNewstemplate;
import com.thinkgem.jeesite.modules.setting.service.WeixinNewstemplateService;

/**
 * 图文消息Controller
 * @author tangchao
 * @version 2016-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinNewstemplate")
public class WeixinNewstemplateController extends BaseController {

	@Autowired
	private WeixinNewstemplateService weixinNewstemplateService;
	
	@ModelAttribute
	public WeixinNewstemplate get(@RequestParam(required=false) String id) {
		WeixinNewstemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinNewstemplateService.get(id);
		}
		if (entity == null){
			entity = new WeixinNewstemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinNewstemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinNewstemplate weixinNewstemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinNewstemplate> page = weixinNewstemplateService.findPage(new Page<WeixinNewstemplate>(request, response), weixinNewstemplate); 
		model.addAttribute("page", page);
		return "modules/setting/weixinNewstemplateList";
	}

	@RequiresPermissions("setting:weixinNewstemplate:view")
	@RequestMapping(value = "form")
	public String form(WeixinNewstemplate weixinNewstemplate, Model model) {
		model.addAttribute("weixinNewstemplate", weixinNewstemplate);
		return "modules/setting/weixinNewstemplateForm";
	}

	@RequiresPermissions("setting:weixinNewstemplate:edit")
	@RequestMapping(value = "save")
	public String save(WeixinNewstemplate weixinNewstemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinNewstemplate)){
			return form(weixinNewstemplate, model);
		}
		weixinNewstemplateService.save(weixinNewstemplate);
		addMessage(redirectAttributes, "保存图文消息成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinNewstemplate/?repage";
	}
	
	@RequiresPermissions("setting:weixinNewstemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinNewstemplate weixinNewstemplate, RedirectAttributes redirectAttributes) {
		weixinNewstemplateService.delete(weixinNewstemplate);
		addMessage(redirectAttributes, "删除图文消息成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinNewstemplate/?repage";
	}

}