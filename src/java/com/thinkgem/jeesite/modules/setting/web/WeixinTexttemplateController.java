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
import com.thinkgem.jeesite.modules.setting.entity.WeixinTexttemplate;
import com.thinkgem.jeesite.modules.setting.service.WeixinTexttemplateService;

/**
 * 微信文本消息Controller
 * @author lanbiao
 * @version 2016-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinTexttemplate")
public class WeixinTexttemplateController extends BaseController {

	@Autowired
	private WeixinTexttemplateService weixinTexttemplateService;
	
	@ModelAttribute
	public WeixinTexttemplate get(@RequestParam(required=false) String id) {
		WeixinTexttemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinTexttemplateService.get(id);
		}
		if (entity == null){
			entity = new WeixinTexttemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinTexttemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinTexttemplate weixinTexttemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinTexttemplate> page = weixinTexttemplateService.findPage(new Page<WeixinTexttemplate>(request, response), weixinTexttemplate); 
		model.addAttribute("page", page);
		return "modules/setting/weixinTexttemplateList";
	}

	@RequiresPermissions("setting:weixinTexttemplate:view")
	@RequestMapping(value = "form")
	public String form(WeixinTexttemplate weixinTexttemplate, Model model) {
		model.addAttribute("weixinTexttemplate", weixinTexttemplate);
		return "modules/setting/weixinTexttemplateForm";
	}

	@RequiresPermissions("setting:weixinTexttemplate:edit")
	@RequestMapping(value = "save")
	public String save(WeixinTexttemplate weixinTexttemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinTexttemplate)){
			return form(weixinTexttemplate, model);
		}
		weixinTexttemplateService.save(weixinTexttemplate);
		addMessage(redirectAttributes, "保存微信文本消息成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinTexttemplate/?repage";
	}
	
	@RequiresPermissions("setting:weixinTexttemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinTexttemplate weixinTexttemplate, RedirectAttributes redirectAttributes) {
		weixinTexttemplateService.delete(weixinTexttemplate);
		addMessage(redirectAttributes, "删除微信文本消息成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinTexttemplate/?repage";
	}

}