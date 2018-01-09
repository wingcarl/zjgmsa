/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.web;

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
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyRegist;
import com.thinkgem.jeesite.modules.penalty.service.PenaltyRegistService;

/**
 * 处罚初始信息收集Controller
 * @author dylan wang
 * @version 2017-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/penalty/penaltyRegist")
public class PenaltyRegistController extends BaseController {

	@Autowired
	private PenaltyRegistService penaltyRegistService;
	
	@ModelAttribute
	public PenaltyRegist get(@RequestParam(required=false) String id) {
		PenaltyRegist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = penaltyRegistService.get(id);
		}
		if (entity == null){
			entity = new PenaltyRegist();
		}
		return entity;
	}
	
	@RequiresPermissions("penalty:penaltyRegist:view")
	@RequestMapping(value = {"list", ""})
	public String list(PenaltyRegist penaltyRegist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PenaltyRegist> page = penaltyRegistService.findPage(new Page<PenaltyRegist>(request, response), penaltyRegist); 
		model.addAttribute("page", page);
		return "modules/penalty/penaltyRegistList";
	}

	@RequiresPermissions("penalty:penaltyRegist:view")
	@RequestMapping(value = "form")
	public String form(PenaltyRegist penaltyRegist, Model model) {
		model.addAttribute("penaltyRegist", penaltyRegist);
		return "modules/penalty/penaltyRegistForm";
	}

	@RequiresPermissions("penalty:penaltyRegist:edit")
	@RequestMapping(value = "save")
	public String save(PenaltyRegist penaltyRegist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, penaltyRegist)){
			return form(penaltyRegist, model);
		}
		penaltyRegistService.save(penaltyRegist);
		addMessage(redirectAttributes, "保存处罚初始信息收集表成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyRegist/?repage";
	}
	
	@RequiresPermissions("penalty:penaltyRegist:edit")
	@RequestMapping(value = "delete")
	public String delete(PenaltyRegist penaltyRegist, RedirectAttributes redirectAttributes) {
		penaltyRegistService.delete(penaltyRegist);
		addMessage(redirectAttributes, "删除处罚初始信息收集表成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyRegist/?repage";
	}

}