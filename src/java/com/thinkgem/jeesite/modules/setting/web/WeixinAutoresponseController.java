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
import com.thinkgem.jeesite.modules.setting.entity.WeixinAutoresponse;
import com.thinkgem.jeesite.modules.setting.service.WeixinAutoresponseService;

/**
 * 关键字回复Controller
 * @author tangchao
 * @version 2016-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinAutoresponse")
public class WeixinAutoresponseController extends BaseController {

	@Autowired
	private WeixinAutoresponseService weixinAutoresponseService;
	
	@ModelAttribute
	public WeixinAutoresponse get(@RequestParam(required=false) String id) {
		WeixinAutoresponse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinAutoresponseService.get(id);
		}
		if (entity == null){
			entity = new WeixinAutoresponse();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinAutoresponse:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinAutoresponse weixinAutoresponse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinAutoresponse> page = weixinAutoresponseService.findPage(new Page<WeixinAutoresponse>(request, response), weixinAutoresponse); 
		model.addAttribute("page", page);
		return "modules/setting/weixinAutoresponseList";
	}

	@RequiresPermissions("setting:weixinAutoresponse:view")
	@RequestMapping(value = "form")
	public String form(WeixinAutoresponse weixinAutoresponse, Model model) {
		model.addAttribute("weixinAutoresponse", weixinAutoresponse);
		return "modules/setting/weixinAutoresponseForm";
	}

	@RequiresPermissions("setting:weixinAutoresponse:edit")
	@RequestMapping(value = "save")
	public String save(WeixinAutoresponse weixinAutoresponse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinAutoresponse)){
			return form(weixinAutoresponse, model);
		}
		weixinAutoresponseService.save(weixinAutoresponse);
		addMessage(redirectAttributes, "保存关键字回复成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinAutoresponse/?repage";
	}
	
	@RequiresPermissions("setting:weixinAutoresponse:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinAutoresponse weixinAutoresponse, RedirectAttributes redirectAttributes) {
		weixinAutoresponseService.delete(weixinAutoresponse);
		addMessage(redirectAttributes, "删除关键字回复成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinAutoresponse/?repage";
	}

}