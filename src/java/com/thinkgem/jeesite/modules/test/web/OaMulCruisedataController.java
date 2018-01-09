/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

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
import com.thinkgem.jeesite.modules.test.entity.OaMulCruisedata;
import com.thinkgem.jeesite.modules.test.service.OaMulCruisedataService;

/**
 * 巡航数据带字表Controller
 * @author dylan wang
 * @version 2017-02-26
 */
@Controller
@RequestMapping(value = "${adminPath}/test/oaMulCruisedata")
public class OaMulCruisedataController extends BaseController {

	@Autowired
	private OaMulCruisedataService oaMulCruisedataService;
	
	@ModelAttribute
	public OaMulCruisedata get(@RequestParam(required=false) String id) {
		OaMulCruisedata entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaMulCruisedataService.get(id);
		}
		if (entity == null){
			entity = new OaMulCruisedata();
		}
		return entity;
	}
	
	@RequiresPermissions("test:oaMulCruisedata:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaMulCruisedata oaMulCruisedata, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaMulCruisedata> page = oaMulCruisedataService.findPage(new Page<OaMulCruisedata>(request, response), oaMulCruisedata); 
		model.addAttribute("page", page);
		return "modules/test/oaMulCruisedataList";
	}

	@RequiresPermissions("test:oaMulCruisedata:view")
	@RequestMapping(value = "form")
	public String form(OaMulCruisedata oaMulCruisedata, Model model) {
		model.addAttribute("oaMulCruisedata", oaMulCruisedata);
		return "modules/test/oaMulCruisedataForm";
	}

	@RequiresPermissions("test:oaMulCruisedata:edit")
	@RequestMapping(value = "save")
	public String save(OaMulCruisedata oaMulCruisedata, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaMulCruisedata)){
			return form(oaMulCruisedata, model);
		}
		oaMulCruisedataService.save(oaMulCruisedata);
		addMessage(redirectAttributes, "保存巡航数据成功");
		return "redirect:"+Global.getAdminPath()+"/test/oaMulCruisedata/?repage";
	}
	
	@RequiresPermissions("test:oaMulCruisedata:edit")
	@RequestMapping(value = "delete")
	public String delete(OaMulCruisedata oaMulCruisedata, RedirectAttributes redirectAttributes) {
		oaMulCruisedataService.delete(oaMulCruisedata);
		addMessage(redirectAttributes, "删除巡航数据成功");
		return "redirect:"+Global.getAdminPath()+"/test/oaMulCruisedata/?repage";
	}

}