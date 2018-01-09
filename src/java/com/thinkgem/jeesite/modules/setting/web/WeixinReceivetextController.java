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
import com.thinkgem.jeesite.modules.setting.entity.WeixinReceivetext;
import com.thinkgem.jeesite.modules.setting.service.WeixinReceivetextService;

/**
 * 微信接受消息Controller
 * @author tangchao
 * @version 2016-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinReceivetext")
public class WeixinReceivetextController extends BaseController {

	@Autowired
	private WeixinReceivetextService weixinReceivetextService;
	
	@ModelAttribute
	public WeixinReceivetext get(@RequestParam(required=false) String id) {
		WeixinReceivetext entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinReceivetextService.get(id);
		}
		if (entity == null){
			entity = new WeixinReceivetext();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinReceivetext:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinReceivetext weixinReceivetext, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinReceivetext> page = weixinReceivetextService.findPage(new Page<WeixinReceivetext>(request, response), weixinReceivetext); 
		model.addAttribute("page", page);
		return "modules/setting/weixinReceivetextList";
	}

	@RequiresPermissions("setting:weixinReceivetext:view")
	@RequestMapping(value = "form")
	public String form(WeixinReceivetext weixinReceivetext, Model model) {
		model.addAttribute("weixinReceivetext", weixinReceivetext);
		return "modules/setting/weixinReceivetextForm";
	}

	@RequiresPermissions("setting:weixinReceivetext:edit")
	@RequestMapping(value = "save")
	public String save(WeixinReceivetext weixinReceivetext, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinReceivetext)){
			return form(weixinReceivetext, model);
		}
		weixinReceivetextService.save(weixinReceivetext);
		addMessage(redirectAttributes, "保存微信接受消息成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinReceivetext/?repage";
	}
	
	@RequiresPermissions("setting:weixinReceivetext:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinReceivetext weixinReceivetext, RedirectAttributes redirectAttributes) {
		weixinReceivetextService.delete(weixinReceivetext);
		addMessage(redirectAttributes, "删除微信接受消息成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinReceivetext/?repage";
	}

}