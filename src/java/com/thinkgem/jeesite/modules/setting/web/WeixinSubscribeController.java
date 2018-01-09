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
import com.thinkgem.jeesite.modules.setting.entity.WeixinSubscribe;
import com.thinkgem.jeesite.modules.setting.service.WeixinSubscribeService;

/**
 * 欢迎语Controller
 * @author tangchao
 * @version 2016-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinSubscribe")
public class WeixinSubscribeController extends BaseController {

	@Autowired
	private WeixinSubscribeService weixinSubscribeService;
	
	@ModelAttribute
	public WeixinSubscribe get(@RequestParam(required=false) String id) {
		WeixinSubscribe entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinSubscribeService.get(id);
		}
		if (entity == null){
			entity = new WeixinSubscribe();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinSubscribe:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinSubscribe weixinSubscribe, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinSubscribe> page = weixinSubscribeService.findPage(new Page<WeixinSubscribe>(request, response), weixinSubscribe); 
		model.addAttribute("page", page);
		return "modules/setting/weixinSubscribeList";
	}

	@RequiresPermissions("setting:weixinSubscribe:view")
	@RequestMapping(value = "form")
	public String form(WeixinSubscribe weixinSubscribe, Model model) {
		model.addAttribute("weixinSubscribe", weixinSubscribe);
		return "modules/setting/weixinSubscribeForm";
	}

	@RequiresPermissions("setting:weixinSubscribe:edit")
	@RequestMapping(value = "save")
	public String save(WeixinSubscribe weixinSubscribe, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinSubscribe)){
			return form(weixinSubscribe, model);
		}
		weixinSubscribeService.save(weixinSubscribe);
		addMessage(redirectAttributes, "保存欢迎语成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinSubscribe/?repage";
	}
	
	@RequiresPermissions("setting:weixinSubscribe:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinSubscribe weixinSubscribe, RedirectAttributes redirectAttributes) {
		weixinSubscribeService.delete(weixinSubscribe);
		addMessage(redirectAttributes, "删除欢迎语成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinSubscribe/?repage";
	}

}