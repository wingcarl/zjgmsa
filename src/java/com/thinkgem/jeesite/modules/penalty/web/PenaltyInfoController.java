/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.web;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyBasicInfo;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyInfo;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyRegist;
import com.thinkgem.jeesite.modules.penalty.service.PenaltyBasicInfoService;
import com.thinkgem.jeesite.modules.penalty.service.PenaltyInfoService;
import com.thinkgem.jeesite.modules.penalty.service.PenaltyRegistService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 处罚数据登记表Controller
 * @author dylan wang
 * @version 2017-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/penalty/penaltyInfo")
public class PenaltyInfoController extends BaseController {

	@Autowired
	private PenaltyInfoService penaltyInfoService;
	@Autowired
	private PenaltyRegistService penaltyRegistService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private PenaltyBasicInfoService penaltyBasicInfoService;
	@ModelAttribute
	public PenaltyInfo get(@RequestParam(required=false) String id) {
		PenaltyInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = penaltyInfoService.get(id);
		}
		if (entity == null){
			entity = new PenaltyInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("penalty:penaltyInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PenaltyInfo penaltyInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PenaltyInfo> page = penaltyInfoService.findPage(new Page<PenaltyInfo>(request, response), penaltyInfo); 
		model.addAttribute("page", page);
		return "modules/penalty/penaltyInfoList";
	}

	@RequiresPermissions("penalty:penaltyInfo:view")
	@RequestMapping(value = "form")
	public String form(PenaltyInfo penaltyInfo, Model model,HttpServletRequest request) {
		String infoId = request.getParameter("infoId");
		PenaltyRegist pr = new PenaltyRegist();
		pr.setId(infoId);
		pr = penaltyRegistService.get(pr);
		penaltyInfo.setShipName(pr.getShipName());
		penaltyInfo.setIllegalHappenLoc(pr.getFindLoc());
		penaltyInfo.setIllegalHappenDate(pr.getFindDate());
		penaltyInfo.setContactTelephone(pr.getTelephone());
		
		//找出自己部门，及自己下级部门，角色为执法人员的姓名，id及执法证号！
		Office currentUserOffice = penaltyInfo.getCurrentUser().getOffice();
		List<User> userList = systemService.findOfficerByOfficeId(currentUserOffice.getId());
		List<String> roleUsers = systemService.findUserByRoleId("fc782188e19b4c16b56240e46b2c615c");
		List<User> cUsers = new ArrayList<User>();
		for(User u : userList){
			if(roleUsers.contains(u.getId())){
				cUsers.add(u);
			}
		}
		List<PenaltyBasicInfo> infoList = penaltyBasicInfoService.findList(new PenaltyBasicInfo());
		model.addAttribute("infoList", infoList);
		model.addAttribute("penaltyInfo", penaltyInfo);
		model.addAttribute("cUsers", cUsers);
		return "modules/penalty/penaltyInfoForm";
	}

	@RequiresPermissions("penalty:penaltyInfo:edit")
	@RequestMapping(value = "save")
	public String save(PenaltyInfo penaltyInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, penaltyInfo)){
			return form(penaltyInfo, model,request);
		}
		penaltyInfoService.save(penaltyInfo);
		addMessage(redirectAttributes, "保存违章数据成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyInfo/?repage";
	}
	
	@RequiresPermissions("penalty:penaltyInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PenaltyInfo penaltyInfo, RedirectAttributes redirectAttributes) {
		penaltyInfoService.delete(penaltyInfo);
		addMessage(redirectAttributes, "删除违章数据成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyInfo/?repage";
	}

}