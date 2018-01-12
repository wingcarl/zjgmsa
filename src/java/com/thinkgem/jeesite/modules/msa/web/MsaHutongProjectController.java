/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.web;

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
import com.thinkgem.jeesite.modules.msa.entity.MsaHutongProject;
import com.thinkgem.jeesite.modules.msa.service.MsaHutongProjectService;

/**
 * 沪通大桥水工动态表Controller
 * @author Dylan Wang
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/msa/msaHutongProject")
public class MsaHutongProjectController extends BaseController {

	@Autowired
	private MsaHutongProjectService msaHutongProjectService;
	
	@ModelAttribute
	public MsaHutongProject get(@RequestParam(required=false) String id) {
		MsaHutongProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msaHutongProjectService.get(id);
		}
		if (entity == null){
			entity = new MsaHutongProject();
		}
		return entity;
	}
	
	@RequiresPermissions("msa:msaHutongProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsaHutongProject msaHutongProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsaHutongProject> page = msaHutongProjectService.findPage(new Page<MsaHutongProject>(request, response), msaHutongProject); 
		model.addAttribute("page", page);
		return "modules/msa/msaHutongProjectList";
	}

	@RequiresPermissions("msa:msaHutongProject:view")
	@RequestMapping(value = "form")
	public String form(MsaHutongProject msaHutongProject, Model model) {
		model.addAttribute("msaHutongProject", msaHutongProject);
		return "modules/msa/msaHutongProjectForm";
	}

	@RequiresPermissions("msa:msaHutongProject:edit")
	@RequestMapping(value = "save")
	public String save(MsaHutongProject msaHutongProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msaHutongProject)){
			return form(msaHutongProject, model);
		}
		msaHutongProjectService.save(msaHutongProject);
		addMessage(redirectAttributes, "保存沪通大桥水工动态表成功");
		return "redirect:"+Global.getAdminPath()+"/msa/msaHutongProject/?repage";
	}
	
	@RequiresPermissions("msa:msaHutongProject:edit")
	@RequestMapping(value = "delete")
	public String delete(MsaHutongProject msaHutongProject, RedirectAttributes redirectAttributes) {
		msaHutongProjectService.delete(msaHutongProject);
		addMessage(redirectAttributes, "删除沪通大桥水工动态表成功");
		return "redirect:"+Global.getAdminPath()+"/msa/msaHutongProject/?repage";
	}

}