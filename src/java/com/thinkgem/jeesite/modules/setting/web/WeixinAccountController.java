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
import com.thinkgem.jeesite.modules.setting.entity.WeixinAccount;
import com.thinkgem.jeesite.modules.setting.service.WeixinAccountService;

/**
 * 微信公众号设置Controller
 * @author tangchao
 * @version 2016-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/weixinAccount")
public class WeixinAccountController extends BaseController {

	@Autowired
	private WeixinAccountService weixinAccountService;
	
	@ModelAttribute
	public WeixinAccount get(@RequestParam(required=false) String id) {
		WeixinAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinAccountService.get(id);
		}
		if (entity == null){
			entity = new WeixinAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("setting:weixinAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinAccount weixinAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinAccount> page = weixinAccountService.findPage(new Page<WeixinAccount>(request, response), weixinAccount); 
		model.addAttribute("page", page);
		return "modules/setting/weixinAccountList";
	}

	@RequiresPermissions("setting:weixinAccount:view")
	@RequestMapping(value = "form")
	public String form(WeixinAccount weixinAccount, Model model) {
		model.addAttribute("weixinAccount", weixinAccount);
		return "modules/setting/weixinAccountForm";
	}

	@RequiresPermissions("setting:weixinAccount:edit")
	@RequestMapping(value = "save")
	public String save(WeixinAccount weixinAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinAccount)){
			return form(weixinAccount, model);
		}
		weixinAccountService.save(weixinAccount);
		addMessage(redirectAttributes, "保存微信公众号设置成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinAccount/?repage";
	}
	
	@RequiresPermissions("setting:weixinAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinAccount weixinAccount, RedirectAttributes redirectAttributes) {
		weixinAccountService.delete(weixinAccount);
		addMessage(redirectAttributes, "删除微信公众号设置成功");
		return "redirect:"+Global.getAdminPath()+"/setting/weixinAccount/?repage";
	}

}