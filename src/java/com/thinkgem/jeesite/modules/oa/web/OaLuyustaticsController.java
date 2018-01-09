/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

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
import com.thinkgem.jeesite.modules.oa.entity.OaLuyustatics;
import com.thinkgem.jeesite.modules.oa.service.OaLuyustaticsService;

/**
 * 陆域巡查数据Controller
 * @author dylan wang
 * @version 2017-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaLuyustatics")
public class OaLuyustaticsController extends BaseController {

	@Autowired
	private OaLuyustaticsService oaLuyustaticsService;
	
	@ModelAttribute
	public OaLuyustatics get(@RequestParam(required=false) String id) {
		OaLuyustatics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaLuyustaticsService.get(id);
		}
		if (entity == null){
			entity = new OaLuyustatics();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaLuyustatics:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaLuyustatics oaLuyustatics, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaLuyustatics> page = oaLuyustaticsService.findPage(new Page<OaLuyustatics>(request, response), oaLuyustatics); 
		model.addAttribute("page", page);
		return "modules/oa/oaLuyustaticsList";
	}

	@RequiresPermissions("oa:oaLuyustatics:view")
	@RequestMapping(value = "form")
	public String form(OaLuyustatics oaLuyustatics, Model model) {
		model.addAttribute("oaLuyustatics", oaLuyustatics);
		return "modules/oa/oaLuyustaticsForm";
	}

	@RequiresPermissions("oa:oaLuyustatics:edit")
	@RequestMapping(value = "save")
	public String save(OaLuyustatics oaLuyustatics, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaLuyustatics)){
			return form(oaLuyustatics, model);
		}
		oaLuyustaticsService.save(oaLuyustatics);
		addMessage(redirectAttributes, "保存陆域巡查数据成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaLuyustatics/?repage";
	}
	
	@RequiresPermissions("oa:oaLuyustatics:edit")
	@RequestMapping(value = "delete")
	public String delete(OaLuyustatics oaLuyustatics, RedirectAttributes redirectAttributes) {
		oaLuyustaticsService.delete(oaLuyustatics);
		addMessage(redirectAttributes, "删除陆域巡查数据成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaLuyustatics/?repage";
	}

}