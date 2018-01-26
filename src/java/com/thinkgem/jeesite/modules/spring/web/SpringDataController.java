/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.spring.web;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.spring.entity.SpringData;
import com.thinkgem.jeesite.modules.spring.service.SpringDataService;

/**
 * 春运专项数据Controller
 * @author Dylan Wang
 * @version 2018-01-26
 */
@Controller
@RequestMapping(value = "${adminPath}/spring/springData")
public class SpringDataController extends BaseController {

	@Autowired
	private SpringDataService springDataService;

	@ModelAttribute
	public SpringData get(@RequestParam(required=false) String id) {
		SpringData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = springDataService.get(id);
		}
		if (entity == null){
			entity = new SpringData();
		}
		return entity;
	}
	
	@RequiresPermissions("spring:springData:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpringData springData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpringData> page = springDataService.findPage(new Page<SpringData>(request, response), springData); 
		model.addAttribute("page", page);
		return "modules/spring/springDataList";
	}

	@RequiresPermissions("spring:springData:view")
	@RequestMapping(value = "form")
	public String form(SpringData springData, Model model) {
		springDataService.setCruisedata(springData);
		model.addAttribute("springData", springData);
		return "modules/spring/springDataForm";
	}

	@RequiresPermissions("spring:springData:edit")
	@RequestMapping(value = "save")
	public String save(SpringData springData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, springData)){
			return form(springData, model);
		}
		springDataService.save(springData);
		addMessage(redirectAttributes, "保存春运专项数据成功");
		return "redirect:"+Global.getAdminPath()+"/spring/springData/?repage";
	}
	
	@RequiresPermissions("spring:springData:edit")
	@RequestMapping(value = "delete")
	public String delete(SpringData springData, RedirectAttributes redirectAttributes) {
		springDataService.delete(springData);
		addMessage(redirectAttributes, "删除春运专项数据成功");
		return "redirect:"+Global.getAdminPath()+"/spring/springData/?repage";
	}

}