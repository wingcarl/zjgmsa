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
import com.thinkgem.jeesite.modules.msa.entity.MsaTonghang;
import com.thinkgem.jeesite.modules.msa.service.MsaTonghangService;

/**
 * 通航环境表Controller
 * @author Dylan Wang
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/msa/msaTonghang")
public class MsaTonghangController extends BaseController {

	@Autowired
	private MsaTonghangService msaTonghangService;
	
	@ModelAttribute
	public MsaTonghang get(@RequestParam(required=false) String id) {
		MsaTonghang entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msaTonghangService.get(id);
		}
		if (entity == null){
			entity = new MsaTonghang();
		}
		return entity;
	}
	
	@RequiresPermissions("msa:msaTonghang:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsaTonghang msaTonghang, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsaTonghang> page = msaTonghangService.findPage(new Page<MsaTonghang>(request, response), msaTonghang); 
		model.addAttribute("page", page);
		return "modules/msa/msaTonghangList";
	}

	@RequiresPermissions("msa:msaTonghang:view")
	@RequestMapping(value = "form")
	public String form(MsaTonghang msaTonghang, Model model) {
		model.addAttribute("msaTonghang", msaTonghang);
		return "modules/msa/msaTonghangForm";
	}

	@RequiresPermissions("msa:msaTonghang:edit")
	@RequestMapping(value = "save")
	public String save(MsaTonghang msaTonghang, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msaTonghang)){
			return form(msaTonghang, model);
		}
		msaTonghangService.save(msaTonghang);
		addMessage(redirectAttributes, "保存通航环境表成功");
		return "redirect:"+Global.getAdminPath()+"/msa/msaTonghang/?repage";
	}
	
	@RequiresPermissions("msa:msaTonghang:edit")
	@RequestMapping(value = "delete")
	public String delete(MsaTonghang msaTonghang, RedirectAttributes redirectAttributes) {
		msaTonghangService.delete(msaTonghang);
		addMessage(redirectAttributes, "删除通航环境表成功");
		return "redirect:"+Global.getAdminPath()+"/msa/msaTonghang/?repage";
	}

}