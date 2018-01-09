/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vts.web;

import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.vts.entity.VtsWorkData;
import com.thinkgem.jeesite.modules.vts.service.VtsWorkDataService;

/**
 * VTS工作数据Controller
 * @author dylan wang
 * @version 2017-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/vts/vtsWorkData")
public class VtsWorkDataController extends BaseController {

	@Autowired
	private VtsWorkDataService vtsWorkDataService;
	
	@ModelAttribute
	public VtsWorkData get(@RequestParam(required=false) String id) {
		VtsWorkData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vtsWorkDataService.get(id);
		}
		if (entity == null){
			entity = new VtsWorkData();
		}
		return entity;
	}
	
	@RequiresPermissions("vts:vtsWorkData:view")
	@RequestMapping(value = {"list", ""})
	public String list(VtsWorkData vtsWorkData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VtsWorkData> page = vtsWorkDataService.findPage(new Page<VtsWorkData>(request, response), vtsWorkData); 
		model.addAttribute("page", page);
		return "modules/vts/vtsWorkDataList";
	}

	@RequiresPermissions("vts:vtsWorkData:view")
	@RequestMapping(value = {"sum"})
	public String sum(VtsWorkData vtsWorkData, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(vtsWorkData.getBeginCreateDate()==null || vtsWorkData.getEndCreateDate()==null){
			vtsWorkData.setBeginCreateDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
			vtsWorkData.setEndCreateDate(DateUtils.addMonths(vtsWorkData.getBeginCreateDate(), 1));
		}
		List<VtsWorkData> page = vtsWorkDataService.getSumData(vtsWorkData); 
		model.addAttribute("page", page);
		model.addAttribute("v", vtsWorkDataService.getVtsSum(page));
		return "modules/vts/vtsWorkDataSum";
	}
	
	
	@RequiresPermissions("vts:vtsWorkData:view")
	@RequestMapping(value = "form")
	public String form(VtsWorkData vtsWorkData, Model model) {
		model.addAttribute("vtsWorkData", vtsWorkData);
		return "modules/vts/vtsWorkDataForm";
	}

	@RequiresPermissions("vts:vtsWorkData:edit")
	@RequestMapping(value = "save")
	public String save(VtsWorkData vtsWorkData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vtsWorkData)){
			return form(vtsWorkData, model);
		}
		vtsWorkDataService.save(vtsWorkData);
		addMessage(redirectAttributes, "保存VTS工作数据成功");
		return "redirect:"+Global.getAdminPath()+"/vts/vtsWorkData/?repage";
	}
	
	@RequiresPermissions("vts:vtsWorkData:edit")
	@RequestMapping(value = "delete")
	public String delete(VtsWorkData vtsWorkData, RedirectAttributes redirectAttributes) {
		vtsWorkDataService.delete(vtsWorkData);
		addMessage(redirectAttributes, "删除VTS工作数据成功");
		return "redirect:"+Global.getAdminPath()+"/vts/vtsWorkData/?repage";
	}

}