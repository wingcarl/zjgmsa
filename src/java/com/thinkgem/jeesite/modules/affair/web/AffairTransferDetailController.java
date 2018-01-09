/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.web;

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
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferDetail;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferSecond;
import com.thinkgem.jeesite.modules.affair.service.AffairTransferDetailService;
import com.thinkgem.jeesite.modules.affair.service.AffairTransferInfoService;
import com.thinkgem.jeesite.modules.affair.service.AffairTransferSecondService;

/**
 * 二程船详细信息表Controller
 * @author Dylan Wang
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/affair/affairTransferDetail")
public class AffairTransferDetailController extends BaseController {

	@Autowired
	private AffairTransferDetailService affairTransferDetailService;
	@Autowired
	private AffairTransferInfoService affairTransferInfoService;
	@Autowired
	private AffairTransferSecondService affairTransferSecondService;
	@ModelAttribute
	public AffairTransferDetail get(@RequestParam(required=false) String id) {
		AffairTransferDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = affairTransferDetailService.get(id);
		}
		if (entity == null){
			entity = new AffairTransferDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("affair:affairTransferDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(AffairTransferDetail affairTransferDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<AffairTransferInfo> page = affairTransferInfoService.findPage(new Page<AffairTransferInfo>(request, response), affairTransferInfo); 
		Page<AffairTransferDetail> page = affairTransferDetailService.findPage(new Page<AffairTransferDetail>(request,response), affairTransferDetail);
		model.addAttribute("page", page);
		return "modules/affair/affairTransferDetailList";
	}

	@RequiresPermissions("affair:affairTransferDetail:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,AffairTransferInfo affairTransferInfo, Model model) {
		affairTransferInfo = affairTransferInfoService.get(affairTransferInfo);
		String secondId = request.getParameter("secondId");
		AffairTransferSecond second = affairTransferSecondService.get(secondId);
		List<AffairTransferDetail> lists = second.getAffairTransferDetailList();
		for(AffairTransferDetail l : lists){
			String url = l.getImgSrc();
			if(StringUtils.isNotBlank(url) && url!=null){
				url = url.replace("_thumbs/", "");
			}
			
			l.setImgSrc(url);
		}
		model.addAttribute("affairTransferInfo", affairTransferInfo);
		model.addAttribute("second",second);
		return "modules/affair/affairTransferDetailForm";
	}
	
	@RequiresPermissions("affair:affairTransferDetail:view")
	@RequestMapping(value = "deal")
	public String deal(HttpServletRequest request,HttpServletResponse response,AffairTransferDetail affairTransferDetail, Model model) {
		String archive = request.getParameter("archive");
		String sid = request.getParameter("sid");
		AffairTransferDetail detail = affairTransferDetailService.get(new AffairTransferDetail(sid));
		if("1".equals(archive)){
			detail.setIsArchive("1");
		}else{
			detail.setIsArchive("2");
		}
		
		affairTransferDetailService.archive(detail);
		return null;
	}
	
	@RequiresPermissions("affair:affairTransferDetail:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request , AffairTransferSecond affairTransferSecond, Model model, RedirectAttributes redirectAttributes) {
		
		
		affairTransferDetailService.save(affairTransferSecond.getAffairTransferDetailList(),affairTransferSecond);
		addMessage(redirectAttributes, "保存二程船详细信息表成功");
		return "redirect:"+Global.getAdminPath()+"/affair/affairTransferInfo/?repage";
	}
	
	@RequiresPermissions("affair:affairTransferDetail:edit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(HttpServletRequest request , AffairTransferSecond affairTransferSecond, Model model, RedirectAttributes redirectAttributes) {
		String candidatesIds = request.getParameter("candidatesIds");
		String secondId = request.getParameter("secondId");
		String auditComment = request.getParameter("audit_comment");
		affairTransferDetailService.saveAudit(affairTransferSecond,candidatesIds,auditComment);
		
		addMessage(redirectAttributes, "保存二程船详细信息表成功");

		return "redirect:" + adminPath + "/act/task/todo/";
	}
	@RequiresPermissions("affair:affairTransferDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(AffairTransferDetail affairTransferDetail, RedirectAttributes redirectAttributes) {
		affairTransferDetailService.delete(affairTransferDetail);
		addMessage(redirectAttributes, "删除二程船详细信息表成功");
		return "redirect:"+Global.getAdminPath()+"/affair/affairTransferDetail/?repage";
	}

}