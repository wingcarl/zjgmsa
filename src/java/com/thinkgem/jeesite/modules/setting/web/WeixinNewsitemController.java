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
import com.thinkgem.jeesite.modules.setting.entity.WeixinNewsitem;
import com.thinkgem.jeesite.modules.setting.service.WeixinNewsitemService;

/**
 * 图文消息内容列Controller
 * @author tangchao
 * @version 2016-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinNewsitem")
public class WeixinNewsitemController extends BaseController {

	@Autowired
	private WeixinNewsitemService weixinNewsitemService;
	
	@ModelAttribute
	public WeixinNewsitem get(@RequestParam(required=false) String id) {
		WeixinNewsitem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinNewsitemService.get(id);
		}
		if (entity == null){
			entity = new WeixinNewsitem();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinNewsitem:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinNewsitem weixinNewsitem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinNewsitem> page = weixinNewsitemService.findPage(new Page<WeixinNewsitem>(request, response), weixinNewsitem); 
		model.addAttribute("page", page);
		return "modules/setting/weixinNewsitemList";
	}

	@RequiresPermissions("setting:weixinNewsitem:view")
	@RequestMapping(value = "form")
	public String form(WeixinNewsitem weixinNewsitem, Model model) {
		model.addAttribute("weixinNewsitem", weixinNewsitem);
		return "modules/setting/weixinNewsitemForm";
	}

	@RequiresPermissions("setting:weixinNewsitem:edit")
	@RequestMapping(value = "save")
	public String save(WeixinNewsitem weixinNewsitem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinNewsitem)){
			return form(weixinNewsitem, model);
		}
		weixinNewsitemService.save(weixinNewsitem);
		addMessage(redirectAttributes, "保存图文消息内容列成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinNewsitem/?repage";
	}
	
	@RequiresPermissions("setting:weixinNewsitem:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinNewsitem weixinNewsitem, RedirectAttributes redirectAttributes) {
		weixinNewsitemService.delete(weixinNewsitem);
		addMessage(redirectAttributes, "删除图文消息内容列成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinNewsitem/?repage";
	}

}