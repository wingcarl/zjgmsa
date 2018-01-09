/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.schedule.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.schedule.entity.ScheduleDetail;
import com.thinkgem.jeesite.modules.schedule.service.ScheduleDetailService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 生成全局排班表Controller
 * @author dylan wang
 * @version 2017-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/schedule/scheduleDetail")
public class ScheduleDetailController extends BaseController {

	@Autowired
	private ScheduleDetailService scheduleDetailService;
	
	@ModelAttribute
	public ScheduleDetail get(@RequestParam(required=false) String id) {
		ScheduleDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scheduleDetailService.get(id);
		}
		if (entity == null){
			entity = new ScheduleDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("schedule:scheduleDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScheduleDetail scheduleDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<ScheduleDetail> page = scheduleDetailService.findPage(new Page<ScheduleDetail>(request, response), scheduleDetail); 
		List<ScheduleDetail> list = page.getList();
		for(ScheduleDetail d : list){
			if("1".equals(d.getConfirm()))
					d.setConfirm("已确认");
			else
					d.setConfirm("未确认");
		}
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/schedule/scheduleDetailList";
	}
	@RequiresPermissions("schedule:scheduleDetail:view")
	@RequestMapping(value = {"confirm"})
	public void confirm(ScheduleDetail scheduleDetail, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		scheduleDetail = scheduleDetailService.get(scheduleDetail);
		scheduleDetail.setUpdateBy(UserUtils.getUser());
		Date d = scheduleDetail.getScheduleDate();
		PrintWriter out = response.getWriter();
		if(DateUtils.isSameDay(d, new Date())){
			scheduleDetailService.confirm(scheduleDetail);
			out.write("确认成功");
		}
			
		else
			out.write("只能在当天确认值班情况");
	}
	@RequiresPermissions("schedule:scheduleDetail:view")
	@RequestMapping(value = "form")
	public String form(ScheduleDetail scheduleDetail, Model model) {
		model.addAttribute("scheduleDetail", scheduleDetail);
		return "modules/schedule/scheduleDetailForm";
	}

	@RequiresPermissions("schedule:scheduleDetail:edit")
	@RequestMapping(value = "save")
	public String save(ScheduleDetail scheduleDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scheduleDetail)){
			return form(scheduleDetail, model);
		}
		scheduleDetailService.save(scheduleDetail);
		addMessage(redirectAttributes, "保存排班表成功");
		return "redirect:"+Global.getAdminPath()+"/schedule/scheduleDetail/?repage";
	}
	
	@RequiresPermissions("schedule:scheduleDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(ScheduleDetail scheduleDetail, RedirectAttributes redirectAttributes) {
		scheduleDetailService.delete(scheduleDetail);
		addMessage(redirectAttributes, "删除排班表成功");
		return "redirect:"+Global.getAdminPath()+"/schedule/scheduleDetail/?repage";
	}
	
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("schedule:scheduleDetail:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ScheduleDetail> list = ei.getDataList(ScheduleDetail.class);
			for (ScheduleDetail sd : list){
				try{
					BeanValidators.validateWithException(validator, sd);
					scheduleDetailService.save(sd);;
					successNum++;			
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+sd.getOffice().getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+sd.getOffice().getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/schedule/scheduleDetail/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("schedule:scheduleDetail:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xls";
    		List<ScheduleDetail> list = Lists.newArrayList(); list.add(new ScheduleDetail());
    		new ExportExcel("用户数据", ScheduleDetail.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/schedule/scheduleDetail/list?repage";
    }

}