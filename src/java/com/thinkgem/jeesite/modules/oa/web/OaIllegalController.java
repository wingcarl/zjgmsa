/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.oa.service.OaIllegalService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;

/**
 * 违法行为增删改查Controller
 * @author dylanwang
 * @version 2017-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaIllegal")
public class OaIllegalController extends BaseController {

	@Autowired
	private OaIllegalService oaIllegalService;
	
	@ModelAttribute
	public OaIllegal get(@RequestParam(required=false) String id) {
		OaIllegal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaIllegalService.get(id);
		}
		if (entity == null){
			entity = new OaIllegal();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaIllegal:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaIllegal oaIllegal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaIllegal> page = oaIllegalService.findPage(new Page<OaIllegal>(request, response), oaIllegal); 
		model.addAttribute("page", page);
		return "modules/oa/oaIllegalList";
	}

	@RequiresPermissions("oa:oaIllegal:view")
	@RequestMapping(value = "form")
	public String form(OaIllegal oaIllegal, Model model) {
		model.addAttribute("oaIllegal", oaIllegal);
		return "modules/oa/oaIllegalForm";
	}

	@RequiresPermissions("oa:oaIllegal:view")
	@RequestMapping(value = "formInline")
	public String formInline(OaIllegal oaIllegal, Model model) {
		model.addAttribute("oaIllegal", oaIllegal);
		return "modules/oa/oaIllegalFormInline";
	}
	
	@RequiresPermissions("oa:oaIllegal:edit")
	@RequestMapping(value = "save")
	public String save(OaIllegal oaIllegal, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaIllegal)){
			return form(oaIllegal, model);
		}
		
		
		oaIllegalService.save(oaIllegal);
		addMessage(redirectAttributes, "保存违法行为成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaIllegal/?repage";
	}
	
	@RequiresPermissions("oa:oaIllegal:edit")
	@RequestMapping(value = "delete")
	public String delete(OaIllegal oaIllegal, RedirectAttributes redirectAttributes) {
		oaIllegalService.delete(oaIllegal);
		addMessage(redirectAttributes, "删除违法行为成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaIllegal/?repage";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaIllegal:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OaIllegal oaIllegal, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "违法船舶数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
    		//Page<OaIllegal> page = oaIllegalService.findPage(new Page<OaIllegal>(request, response), oaIllegal); 
    		List<OaIllegal> list = oaIllegalService.findList1(oaIllegal); 
    		new ExportExcel("违法船舶数据", OaIllegal.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/oa/oaIllegal/?repage";
    }

}