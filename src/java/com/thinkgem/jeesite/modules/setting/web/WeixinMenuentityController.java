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
import com.thinkgem.jeesite.modules.setting.entity.WeixinMenuentity;
import com.thinkgem.jeesite.modules.setting.service.WeixinMenuentityService;

/**
 * 自定义菜单Controller
 * @author tangchao
 * @version 2016-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinMenuentity")
public class WeixinMenuentityController extends BaseController {

	@Autowired
	private WeixinMenuentityService weixinMenuentityService;
	
	@ModelAttribute
	public WeixinMenuentity get(@RequestParam(required=false) String id) {
		WeixinMenuentity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinMenuentityService.get(id);
		}
		if (entity == null){
			entity = new WeixinMenuentity();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinMenuentity:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinMenuentity weixinMenuentity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinMenuentity> page = weixinMenuentityService.findPage(new Page<WeixinMenuentity>(request, response), weixinMenuentity); 
		model.addAttribute("page", page);
		return "modules/setting/weixinMenuentityList";
	}

	@RequiresPermissions("setting:weixinMenuentity:view")
	@RequestMapping(value = "form")
	public String form(WeixinMenuentity weixinMenuentity, Model model) {
		model.addAttribute("weixinMenuentity", weixinMenuentity);
		return "modules/setting/weixinMenuentityForm";
	}

	@RequiresPermissions("setting:weixinMenuentity:edit")
	@RequestMapping(value = "save")
	public String save(WeixinMenuentity weixinMenuentity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinMenuentity)){
			return form(weixinMenuentity, model);
		}
		weixinMenuentityService.save(weixinMenuentity);
		addMessage(redirectAttributes, "保存自定义菜单成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinMenuentity/?repage";
	}
	
	@RequiresPermissions("setting:weixinMenuentity:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinMenuentity weixinMenuentity, RedirectAttributes redirectAttributes) {
		weixinMenuentityService.delete(weixinMenuentity);
		addMessage(redirectAttributes, "删除自定义菜单成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinMenuentity/?repage";
	}

}