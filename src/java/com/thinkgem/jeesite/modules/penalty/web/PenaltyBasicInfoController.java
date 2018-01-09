/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyBasicInfo;
import com.thinkgem.jeesite.modules.penalty.service.PenaltyBasicInfoService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 处罚基础信息配置表Controller
 * @author dylan wang
 * @version 2017-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/penalty/penaltyBasicInfo")
public class PenaltyBasicInfoController extends BaseController {

	@Autowired
	private PenaltyBasicInfoService penaltyBasicInfoService;
	
	@ModelAttribute
	public PenaltyBasicInfo get(@RequestParam(required=false) String id) {
		PenaltyBasicInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = penaltyBasicInfoService.get(id);
		}
		if (entity == null){
			entity = new PenaltyBasicInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("penalty:penaltyBasicInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PenaltyBasicInfo penaltyBasicInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PenaltyBasicInfo> page = penaltyBasicInfoService.findPage(new Page<PenaltyBasicInfo>(request, response), penaltyBasicInfo); 
		model.addAttribute("page", page);
		return "modules/penalty/penaltyBasicInfoList";
	}

	@RequiresPermissions("penalty:penaltyBasicInfo:view")
	@RequestMapping(value = "form")
	public String form(PenaltyBasicInfo penaltyBasicInfo, Model model) {
		model.addAttribute("penaltyBasicInfo", penaltyBasicInfo);
		return "modules/penalty/penaltyBasicInfoForm";
	}

	@RequiresPermissions("penalty:penaltyBasicInfo:edit")
	@RequestMapping(value = "save")
	public String save(PenaltyBasicInfo penaltyBasicInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, penaltyBasicInfo)){
			return form(penaltyBasicInfo, model);
		}
		penaltyBasicInfoService.save(penaltyBasicInfo);
		addMessage(redirectAttributes, "保存处罚基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyBasicInfo/?repage";
	}
	@RequiresPermissions("penalty:penaltyBasicInfo:edit")
	@RequestMapping(value = "getPenaltyBasicInfo")
	public String getPenaltyBasicInfo( HttpServletRequest request,HttpServletResponse response,PenaltyBasicInfo penaltyBasicInfo, Model model) {
		String id = request.getParameter("id");
		
		penaltyBasicInfo = penaltyBasicInfoService.get(id);
		PrintWriter pw = null;  
		StringBuffer sb = new StringBuffer();  
		sb.append(JSON.toJSONString(penaltyBasicInfo));  
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
	@RequiresPermissions("penalty:penaltyBasicInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PenaltyBasicInfo penaltyBasicInfo, RedirectAttributes redirectAttributes) {
		penaltyBasicInfoService.delete(penaltyBasicInfo);
		addMessage(redirectAttributes, "删除处罚基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyBasicInfo/?repage";
	}

}