/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisearea;
import com.thinkgem.jeesite.modules.oa.service.OaCruiseareaService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 巡航水域增删改查Controller
 * @author 王浩宇
 * @version 2017-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaCruisearea")
public class OaCruiseareaController extends BaseController {

	@Autowired
	private OaCruiseareaService oaCruiseareaService;
	
	@ModelAttribute
	public OaCruisearea get(@RequestParam(required=false) String id) {
		OaCruisearea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaCruiseareaService.get(id);
		}
		if (entity == null){
			entity = new OaCruisearea();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaCruisearea:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaCruisearea oaCruisearea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaCruisearea> page = oaCruiseareaService.findPage(new Page<OaCruisearea>(request, response), oaCruisearea); 
		model.addAttribute("page", page);
		return "modules/oa/oaCruiseareaList";
	}

	@RequiresPermissions("oa:oaCruisearea:view")
	@RequestMapping(value = "form")
	public String form(OaCruisearea oaCruisearea, Model model) {
		model.addAttribute("oaCruisearea", oaCruisearea);
		return "modules/oa/oaCruiseareaForm";
	}

	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = "getCruiseArea")
	public String getCruiseareaByOfficeId( HttpServletRequest request,HttpServletResponse response,OaCruisearea oaCruisearea, Model model) {
		String officeId = UserUtils.getUser().getOffice().getParentId();
		oaCruisearea.setOffice(new Office(officeId));
		JSONObject jsonObject = oaCruiseareaService.findCruiseareaListByOfficeId(oaCruisearea);
		PrintWriter pw = null;  
		StringBuffer sb = new StringBuffer();  
		sb.append(JSON.toJSONString(jsonObject));  
		try  
		{  
		    response.setContentType("text/html;charset=UTF-8");  
		    pw = response.getWriter();  
		    pw.write(sb.toString());  
		    pw.close();  
		}  
		catch (IOException e)  
		{  
		    e.printStackTrace();  
		}  
		return null;
	}
	
	@RequiresPermissions("oa:oaCruisearea:edit")
	@RequestMapping(value = "save")
	public String save(OaCruisearea oaCruisearea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCruisearea)){
			return form(oaCruisearea, model);
		}
		oaCruiseareaService.save(oaCruisearea);
		addMessage(redirectAttributes, "保存巡航水域成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaCruisearea/?repage";
	}
	
	@RequiresPermissions("oa:oaCruisearea:edit")
	@RequestMapping(value = "delete")
	public String delete(OaCruisearea oaCruisearea, RedirectAttributes redirectAttributes) {
		oaCruiseareaService.delete(oaCruisearea);
		addMessage(redirectAttributes, "删除巡航水域成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaCruisearea/?repage";
	}

}