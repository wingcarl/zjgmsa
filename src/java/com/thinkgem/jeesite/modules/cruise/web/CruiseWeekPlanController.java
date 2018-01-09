/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseWeekPlan;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseWeekPlanDetails;
import com.thinkgem.jeesite.modules.cruise.service.CruiseWeekPlanService;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisearea;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 对周巡航计划的增删改查Controller
 * @author 王浩宇
 * @version 2017-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/cruise/cruiseWeekPlan")
public class CruiseWeekPlanController extends BaseController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private CruiseWeekPlanService cruiseWeekPlanService;
	
	@ModelAttribute
	public CruiseWeekPlan get(@RequestParam(required=false) String id) {
		CruiseWeekPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cruiseWeekPlanService.get(id);
		}
		if (entity == null){
			entity = new CruiseWeekPlan();
		}
		return entity;
	}
	
	@RequiresPermissions("cruise:cruiseWeekPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(CruiseWeekPlan cruiseWeekPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CruiseWeekPlan> page = cruiseWeekPlanService.findPage(new Page<CruiseWeekPlan>(request, response), cruiseWeekPlan); 
		model.addAttribute("page", page);
		return "modules/cruise/cruiseWeekPlanList";
	}
	
	
	@RequiresPermissions("cruise:cruiseWeekPlan:view")
	@RequestMapping(value = "form")
	public String form(CruiseWeekPlan cruiseWeekPlan, Model model) {
		Office currentUserOffice = cruiseWeekPlan.getCurrentUser().getOffice();
		List<User> officeUsers = new ArrayList<User>();
		officeUsers = systemService.findUserByOfficeId(currentUserOffice.getId());
		List<String> roleUsers = systemService.findUserByRoleId("fc782188e19b4c16b56240e46b2c615c");
		List<String> roleCaptain = systemService.findUserByRoleId("15b735ebd2ff4f758925eb94951425d5");
		List<User> cUsers = new ArrayList<User>();
		List<User> cCaptain = new ArrayList<User>();
		for(User u : officeUsers){
			if(roleUsers.contains(u.getId())){
				cUsers.add(u);
			}
		}
		List<User> captains = new ArrayList<User>();
		captains = systemService.findCaptainByOfficeId(currentUserOffice.getId());
		for(User u : captains){
			if(roleCaptain.contains(u.getId())){
				cCaptain.add(u);
			}
		}
		if(cruiseWeekPlan.getStartDate()==null && cruiseWeekPlan.getEndDate()==null){
			Calendar cal = Calendar.getInstance();
			boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
			
			int weekDay =  cal.get(Calendar.DAY_OF_WEEK);
			if(isFirstSunday){
				weekDay = weekDay - 1;
				if(weekDay == 0)
						weekDay = 7;
			}
			cal.add(Calendar.DATE, 7-weekDay+1);
			cruiseWeekPlan.setStartDate(cal.getTime());
			cal.add(Calendar.DATE,6);
			cruiseWeekPlan.setEndDate(cal.getTime());
		}
	
	//	cruiseWeekPlan.setStartDate(startDate);
		//cruiseWeekPlan.setEndDate(endDate);
		model.addAttribute("cCaptain", cCaptain);
		model.addAttribute("cUsers", cUsers);
		model.addAttribute("cruiseWeekPlan", cruiseWeekPlan);
		
		return "modules/cruise/cruiseWeekPlanForm";
	}

	@RequiresPermissions("cruise:cruiseWeekPlan:edit")
	@RequestMapping(value = "save")
	public String save(CruiseWeekPlan cruiseWeekPlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cruiseWeekPlan)){
			return form(cruiseWeekPlan, model);
		}
		cruiseWeekPlanService.save(cruiseWeekPlan);
		addMessage(redirectAttributes, "保存周巡航计划成功");
		return "redirect:"+Global.getAdminPath()+"/cruise/cruiseWeekPlan/?repage";
	}
	
	@RequiresPermissions("cruise:cruiseWeekPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(CruiseWeekPlan cruiseWeekPlan, RedirectAttributes redirectAttributes) {
		cruiseWeekPlanService.delete(cruiseWeekPlan);
		addMessage(redirectAttributes, "删除周巡航计划成功");
		return "redirect:"+Global.getAdminPath()+"/cruise/cruiseWeekPlan/?repage";
	}
	private Date getMinDate(List<CruiseWeekPlanDetails> dets){
		if(dets==null || dets.size()==0){
			return null;
		}
		Date day = dets.get(0).getArrangeDate();
		for(CruiseWeekPlanDetails d : dets){
			if(d.getArrangeDate().getTime()<day.getTime()){
				day = d.getArrangeDate();
			}
		}
		return day;
	}
	
	private void setExcelValue(CruiseWeekPlan cwp,HSSFSheet s,int row,int i,int j){
		HSSFCell cell = s.getRow(row).getCell(j++);
		String hxtName = cwp.getOffice().getName();
		cell.setCellValue(hxtName);
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getCruiseGrid());
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getCruiseTime());
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getImportantPos());
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getImportantContent());
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getCruiseWeekPlanDetailsList().get(i).getDayCruisePeople());
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getCruiseWeekPlanDetailsList().get(i).getCruiseCaptain().replaceAll(",", ""));
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue("on".equals(cwp.getCruiseWeekPlanDetailsList().get(i).getIsNightCruise())?"是":"否");
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue("".equals(cwp.getCruiseWeekPlanDetailsList().get(i).getNightCruiseGrid())?"无":cwp.getCruiseWeekPlanDetailsList().get(i).getNightCruiseGrid());
		cell = s.getRow(row).getCell(j++);
		cell.setCellValue(cwp.getCruiseWeekPlanDetailsList().get(i).getNightCruisePeople());
	}
	
	private void setExcelRota(List<CruiseWeekPlan> cwpList,HSSFSheet s,int dayInt,int monthInt){
		int i=2,j=1;
		for(CruiseWeekPlan cwp : cwpList){
			String hxt = cwp.getOffice().getName();
			HSSFCell cell = s.getRow(1).getCell(j);
			cell.setCellValue(hxt);
			List<CruiseWeekPlanDetails> cwpd = cwp.getCruiseWeekPlanDetailsList();
			for(CruiseWeekPlanDetails d:cwpd){
				cell = s.getRow(i++).getCell(j);
				cell.setCellValue(d.getCruiseCaptain().split(",")[0]);
			}
			i=2;
			j++;
		}
		for(int m=0;m<7;m++){
			HSSFCell cell = s.getRow(m+2).getCell(0);
			String dayStr = String.valueOf(monthInt)+"/"+String.valueOf(dayInt+m);
			cell.setCellValue(dayStr);
		}
		
	}
	
	@RequiresPermissions("cruise:cruiseWeekPlan:view")
	@RequestMapping(value = "loadPlanData")
	public String getPlanDataByOfficeId( HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		CruiseWeekPlan cruiseWeekPlan = new CruiseWeekPlan();
		cruiseWeekPlan.setOffice(new Office(id));
		JSONObject jsonObject = cruiseWeekPlanService.findCruiseWeekPlanListByOfficeId(cruiseWeekPlan);
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
	
	@RequiresPermissions("cruise:cruiseWeekPlan:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CruiseWeekPlan cruiseWeekPlan, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		//获得list
		List<CruiseWeekPlan> cwpList = cruiseWeekPlanService.findListFetch(cruiseWeekPlan);
		Date minDate = cruiseWeekPlan.getHappenDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(minDate);
		Calendar lastCalendar = Calendar.getInstance();
		lastCalendar.setTime(minDate);
		lastCalendar.add(Calendar.DAY_OF_MONTH, 6);
	    int dayInt = minDate.getDate();
	    int monthInt = minDate.getMonth()+1;
		
	    try {
            String path=request.getSession().getServletContext().getRealPath("templet/巡航计划模板.xls");
            File file = new File(path);  
        	//excel模板路径  
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));  
            //读取excel模板  
            HSSFWorkbook wb = new HSSFWorkbook(fs);  
            //读取了模板内所有sheet内容  
            SimpleDateFormat sdf = new SimpleDateFormat("M.d");
       
            String dayStart = sdf.format(calendar.getTime());
            String dayEnd = sdf.format(lastCalendar.getTime());
            wb.setSheetName(7,"海巡艇值班表"+dayStart+"-"+dayEnd);
            HSSFSheet rotaSheet = wb.getSheetAt(7);
            setExcelRota(cwpList,rotaSheet,dayInt,monthInt);
            List<HSSFSheet> sheetPlan = new ArrayList<HSSFSheet>();
            //获得前七天的sheet表单
            for(int i=0;i<7;i++){
            	String name = sdf.format(calendar.getTime());
            	wb.setSheetName(i, name);
            	calendar.add(Calendar.DAY_OF_MONTH, 1);
            	HSSFSheet sheet = wb.getSheetAt(i);
            	sheetPlan.add(sheet);
            }
            calendar.add(Calendar.DAY_OF_MONTH,-6);
            int i=3,j=0;
            //定死，南丰、沪通大桥、锦丰、保税区
            boolean isFirstForzd = true;
            boolean isFirstForgq = true;
            for(CruiseWeekPlan cwp : cwpList){
            	 
            	User u = cwp.getCreateBy();
    			User user = systemService.getUser(u.getId());
    			String officeName = user.getOffice().getName();
    		
    			int m = 2;
    			if("海巡执法支队".equals(officeName)){
    				i=0;
    				for(HSSFSheet s : sheetPlan){
	    				j=2;
	    				if(isFirstForzd){
	    					setExcelValue(cwp,s,6,i,j);
	    				}else{
	    					setExcelValue(cwp,s,7,i,j);
	    				}
	    				i++;
	    			}
	    			isFirstForzd = false;
    			}else if("港区海事处".equals(officeName)){
    				i=0;
    				for(HSSFSheet s : sheetPlan){
        				j=2;	
        				if(isFirstForgq){
	    					setExcelValue(cwp,s,8,i,j);
	    				}else{
	    					setExcelValue(cwp,s,9,i,j);
	    				}
        				i++;
        			}
    				isFirstForgq = false;
    			}else{
    				i=0;
    				for(HSSFSheet s : sheetPlan){
        				j=2;
        				HSSFCell cell = s.getRow(2).getCell(1);
        				cell.setCellValue(officeName);

	    				setExcelValue(cwp,s,m,i,j);
        				i++;
        			}
    				m++;
    			}
    			Calendar cal1 = Calendar.getInstance();
    			cal1.setTime(minDate);
    			for(HSSFSheet s : sheetPlan){
	    			HSSFCell cell = s.getRow(0).getCell(0);	
	    			
	    			SimpleDateFormat sdf2 = new SimpleDateFormat("M月d日");
	    			SimpleDateFormat sdf3 = new SimpleDateFormat("d");
	    			String title = "张家港海事局"+sdf2.format(cal1.getTime())+"海巡艇巡航执法计划表（"+sdf3.format(cal1.getTime())+"日0600时-";
	    			cal1.add(Calendar.DAY_OF_MONTH, 1);
	    			title += sdf3.format(cal1.getTime())+"日0600时）";
	    			cell.setCellValue(title);
    			}
    		} 
          
            
            OutputStream ou = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode("周巡航计划.xls", "UTF-8"));
            response.setContentType("application/msexcel;charset=UTF-8");
            wb.write(ou);
            ou.flush();
            ou.close();
            return null;
		} catch (Exception e) {
			System.out.println(e);
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "modules/cruise/cruiseWeekPlan/export";
		
		
    }
	
	
}