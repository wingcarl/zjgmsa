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
import com.thinkgem.jeesite.modules.setting.entity.WeixinExpandconfig;
import com.thinkgem.jeesite.modules.setting.service.WeixinExpandconfigService;

/**
 * 扩展接口Controller
 * @author tangchao
 * @version 2016-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinExpandconfig")
public class WeixinExpandconfigController extends BaseController {

	@Autowired
	private WeixinExpandconfigService weixinExpandconfigService;
	
	@ModelAttribute
	public WeixinExpandconfig get(@RequestParam(required=false) String id) {
		WeixinExpandconfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinExpandconfigService.get(id);
		}
		if (entity == null){
			entity = new WeixinExpandconfig();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinExpandconfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinExpandconfig weixinExpandconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinExpandconfig> page = weixinExpandconfigService.findPage(new Page<WeixinExpandconfig>(request, response), weixinExpandconfig); 
		model.addAttribute("page", page);
		return "modules/setting/weixinExpandconfigList";
	}

	@RequiresPermissions("setting:weixinExpandconfig:view")
	@RequestMapping(value = "form")
	public String form(WeixinExpandconfig weixinExpandconfig, Model model) {
		model.addAttribute("weixinExpandconfig", weixinExpandconfig);
		return "modules/setting/weixinExpandconfigForm";
	}

	@RequiresPermissions("setting:weixinExpandconfig:edit")
	@RequestMapping(value = "save")
	public String save(WeixinExpandconfig weixinExpandconfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinExpandconfig)){
			return form(weixinExpandconfig, model);
		}
		weixinExpandconfigService.save(weixinExpandconfig);
		addMessage(redirectAttributes, "保存扩展接口成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinExpandconfig/?repage";
	}
	
	@RequiresPermissions("setting:weixinExpandconfig:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinExpandconfig weixinExpandconfig, RedirectAttributes redirectAttributes) {
		weixinExpandconfigService.delete(weixinExpandconfig);
		addMessage(redirectAttributes, "删除扩展接口成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinExpandconfig/?repage";
	}

}