/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.msa.entity.MsaReport;
import com.thinkgem.jeesite.modules.report.entity.ReportMonthly;
import com.thinkgem.jeesite.modules.report.service.ReportMonthlyService;

/**
 * 海事业务月度通报Controller
 * @author Dylan Wang
 * @version 2017-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/report/reportMonthly")
public class ReportMonthlyController extends BaseController {

	@Autowired
	private ReportMonthlyService reportMonthlyService;
	
	@ModelAttribute
	public ReportMonthly get(@RequestParam(required=false) String id) {
		ReportMonthly entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reportMonthlyService.get(id);
		}
		if (entity == null){
			entity = new ReportMonthly();
		}
		return entity;
	}
	
	@RequiresPermissions("report:reportMonthly:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportMonthly reportMonthly, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportMonthly> page = reportMonthlyService.findPage(new Page<ReportMonthly>(request, response), reportMonthly); 
		model.addAttribute("page", page);
		return "modules/report/reportMonthlyList";
	}

	@RequiresPermissions("report:reportMonthly:view")
	@RequestMapping(value = "form")
	public String form(ReportMonthly reportMonthly, Model model) {
		model.addAttribute("reportMonthly", reportMonthly);
		return "modules/report/reportMonthlyForm";
	}

	@RequiresPermissions("report:reportMonthly:edit")
	@RequestMapping(value = "save")
	public String save(ReportMonthly reportMonthly, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reportMonthly)){
			return form(reportMonthly, model);
		}
		reportMonthlyService.save(reportMonthly);
		addMessage(redirectAttributes, "保存海事业务月度通报成功");
		return "redirect:"+Global.getAdminPath()+"/report/reportMonthly/?repage";
	}
	
	@RequiresPermissions("report:reportMonthly:edit")
	@RequestMapping(value = "delete")
	public String delete(ReportMonthly reportMonthly, RedirectAttributes redirectAttributes) {
		reportMonthlyService.delete(reportMonthly);
		addMessage(redirectAttributes, "删除海事业务月度通报成功");
		return "redirect:"+Global.getAdminPath()+"/report/reportMonthly/?repage";
	}
	
	 @RequiresPermissions("report:reportMonthly:edit")
	 @RequestMapping(value = "import", method=RequestMethod.POST)
	    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
			if(Global.isDemoMode()){
				addMessage(redirectAttributes, "演示模式，不允许操作！");
				return "redirect:" + adminPath + "/report/reportMonthly/?repage";
			}
			try {
				//excel模板路径  
	            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());  
	            //读取excel模板  
	            HSSFWorkbook wb = new HSSFWorkbook(fs);  
	            //读取了模板内所有sheet内容  
	            HSSFSheet sheet = wb.getSheetAt(0);  
	            int i = 2;
	            try{
	            	reportMonthlyService.importReportFromExcel(sheet);
	            	
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            	i++;
	        
	            
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:" + adminPath + "/report/reportMonthly/?repage";
	    }

}