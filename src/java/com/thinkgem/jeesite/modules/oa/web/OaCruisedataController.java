/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.MapUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.entity.EchartsData;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseSumStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisearea;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.OaExportDaily;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.oa.entity.OaReport;
import com.thinkgem.jeesite.modules.oa.entity.Series;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;
import com.thinkgem.jeesite.modules.oa.service.OaDigitalcruiseService;
import com.thinkgem.jeesite.modules.oa.service.OaIllegalService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.service.UserService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 测试表Controller
 * @author 王浩宇
 * @version 2017-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaCruisedata")
public class OaCruisedataController extends BaseController {

	@Autowired
	private OaCruisedataService oaCruisedataService;
	@Autowired
	private OaDigitalcruiseService oaDigitalcruiseService;
	@Autowired
	private OaIllegalService oaIllegalService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private DictService dictService;
	@ModelAttribute
	public OaCruisedata get(@RequestParam(required=false) String id) {
		OaCruisedata entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaCruisedataService.get(id);
		}
		if (entity == null){
			entity = new OaCruisedata();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaCruisedata oaCruisedata, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaCruisedata> page = oaCruisedataService.findPage(new Page<OaCruisedata>(request, response), oaCruisedata); 
		String userId = oaCruisedata.getCurrentUser().getId();//指挥中心用户可以删除数据
		for(OaCruisedata p : page.getList()){
				if(DateUtils.isToday(p.getCreateDate())){
					p.setEditable(true);
				}
			}
		
		model.addAttribute("page", page);
		return "modules/oa/oaCruisedataList";
	}
	
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value="list1")
	public String list1(OaCruisedata oaCruisedata,HttpServletRequest request,HttpServletResponse response,Model model){
		String grid = request.getParameter("grid");
		if("7".equals(grid)){
			oaCruisedata.setCreateBy(new User("9258f2a15eef41e481f633161a25450e"));
		}else if("11".equals(grid)){
			oaCruisedata.setCreateBy(new User("668d5ec787dd41dda958874fabc3d2a2"));
		}else if("10".equals(grid)){
			oaCruisedata.setCreateBy(new User("07da27c293064f97a23b51daf8d1ea31"));
		}
		Page<OaCruisedata> page = oaCruisedataService.findPage(new Page<OaCruisedata>(request,response), oaCruisedata);
		String userId = oaCruisedata.getCurrentUser().getId();
		model.addAttribute("page",page);
		model.addAttribute("grid",grid);
		return "modules/oa/oaCruisedataListView";
	}
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"reports"})
	public String report(OaReport oaReport, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) throws IOException {
		//List<OaReport> data = oaCruisedataService.findList(oaReport); 
		String type = oaReport.getReportType();
		if("4".equals(type)){ //导出每日电子巡查报表
	            String fileName = "张家港海事局巡航统计信息一览表"+DateUtils.getDate("M月d日")+"（现场和电子）.xls";
	            String path=request.getSession().getServletContext().getRealPath("templet/江苏局模板.xls");
	            File file = new File(path);  
	            OaDigitalcruise oaDigitalcruise = new OaDigitalcruise();
	            oaDigitalcruise.setBeginHappenDate(oaReport.getCreateDate());
	            List<OaDigitalcruise> digitalCruiseList = oaDigitalcruiseService.findList(oaDigitalcruise);
	            try {
	            	//excel模板路径  
	                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));  
	                //读取excel模板  
	                HSSFWorkbook wb = new HSSFWorkbook(fs);  
	                //读取了模板内所有sheet内容  
	                HSSFSheet sheet = wb.getSheetAt(0);  
	                int i = 2;
	                int j = 1;
	                for(OaDigitalcruise cruise : digitalCruiseList){
	                	HSSFCell cell = sheet.getRow(i).getCell(j++);  
	 	                cell.setCellValue(cruise.getCreateBy().getOffice().getName());
	 	                HSSFCell cell2 = sheet.getRow(i).getCell(j++);  
	 	                cell2.setCellValue(cruise.getCruisePeople());  
	 	                HSSFCell cell3 = sheet.getRow(i).getCell(j++);  
	 	                cell3.setCellValue(StringEscapeUtils.unescapeHtml4(cruise.getCruiseTime()));   
	 	                HSSFCell cell4 = sheet.getRow(i).getCell(j++);  
	 	                cell4.setCellValue(StringEscapeUtils.unescapeHtml4(cruise.getCruiseArea()));  
	 	                HSSFCell cell5 = sheet.getRow(i).getCell(j++);  
	 	                cell5.setCellValue(StringEscapeUtils.unescapeHtml4(cruise.getSolveProblem()));   
	 	                HSSFCell cell6 = sheet.getRow(i).getCell(j++);  
	 	                cell6.setCellValue(StringEscapeUtils.unescapeHtml4(cruise.getRemarks()));  
	 	                
	 	                j=1;
	 	                i++;
	                }
	                //在相应的单元格进行赋值  
	                HSSFSheet sheet1 = wb.getSheetAt(1); 
	                i = 2;
	                j = 1;
		    		List<OaExportDaily> dailyData = oaCruisedataService.getReport(oaReport);
		    		for(OaExportDaily daily : dailyData){
		    			OaCruiseStat stat = new OaCruiseStat();
		    			stat.setCruiseDataId(daily.getDataId());
		    			List<OaCruiseStat> dataList = oaCruisedataService.getStaticsById(stat);
		    			if(dataList != null && dataList.size()>0){
		    				stat = dataList.get(0);
			    			daily.setXhsc(stat.getTotalTime());
				    		daily.setXhlc(stat.getTotalTime()*12);
				    		daily.setXhcs(stat.getXhcs());
		    			}
		    		}
	                for(OaExportDaily cruise : dailyData){
	                	HSSFCell cell = sheet1.getRow(i).getCell(j++);  
	 	                cell.setCellValue(cruise.getHxt());
	 	                HSSFCell cell2 = sheet1.getRow(i).getCell(j++);  
	 	                cell2.setCellValue(cruise.getZfry());  
	 	                HSSFCell cell3 = sheet1.getRow(i).getCell(j++);  
	 	                cell3.setCellValue(cruise.getXhsj());   
	 	                HSSFCell cell4 = sheet1.getRow(i).getCell(j++);  
	 	                cell4.setCellValue(cruise.getXhsc());  
	 	                HSSFCell cell5 = sheet1.getRow(i).getCell(j++);  
	 	                cell5.setCellValue(cruise.getXhsy());   
	 	                HSSFCell cell6 = sheet1.getRow(i).getCell(j++);  
	 	                cell6.setCellValue( StringEscapeUtils.unescapeHtml4(cruise.getFxjjwt())); 
	 	               
	 	                HSSFCell cell7 = sheet1.getRow(i).getCell(j++);  
	 	                cell7.setCellValue(cruise.getBeizhu());
	 	              
	 	                j=1;
	 	                i++;
	                }
	                //修改模板内容导出新模板  
	                
	                OutputStream ou = response.getOutputStream();
	                response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
	                response.setContentType("application/msexcel;charset=UTF-8");
	                wb.write(ou);
	                ou.flush();
	                ou.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	            System.out.println(path);
		}else if("5".equals(type)){
			  String fileName = "设备故障清单"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
	            String path=request.getSession().getServletContext().getRealPath("templet/设备故障清单.xls");
	            File file = new File(path);  
	            OaDigitalcruise oaDigitalcruise = new OaDigitalcruise();
	            oaDigitalcruise.setBeginHappenDate(oaReport.getCreateDate());
	            List<OaDigitalcruise> digitalCruiseList = oaDigitalcruiseService.findList(oaDigitalcruise);
	            try {
	            	//excel模板路径  
	                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));  
	                //读取excel模板  
	                HSSFWorkbook wb = new HSSFWorkbook(fs);  
	                //读取了模板内所有sheet内容  
	                HSSFSheet sheet = wb.getSheetAt(0);  
	                int i = 3;
	                int j = 1;
	                for(OaDigitalcruise cruise : digitalCruiseList){
	                	HSSFCell cell = sheet.getRow(i).getCell(j++);  
	 	                cell.setCellValue(cruise.getCreateBy().getOffice().getName());
	 	                HSSFCell cell2 = sheet.getRow(i).getCell(j++);  
	 	                cell2.setCellValue(cruise.getCruisePeople());  
	 	                HSSFCell cell3 = sheet.getRow(i).getCell(j++);  
	 	                cell3.setCellValue(StringEscapeUtils.unescapeHtml4(cruise.getEquipProblem()));   
	 	                j=1;
	 	                i++;
	                }
	                OutputStream ou = response.getOutputStream();
	                response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
	                response.setContentType("application/msexcel;charset=UTF-8");
	                wb.write(ou);
	                ou.flush();
	                ou.close();
	            }catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}else if("6".equals(type)){
				 InputStream fin = null;  
			     ServletOutputStream out = null;  
			     File file = null;
				try {
					 file = oaCruisedataService.generateDailyReportByFreeMarker(oaReport);
					 fin = new FileInputStream(file);  
		              
			            response.setCharacterEncoding("utf-8");  
			            response.setContentType("application/msword");  
			            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
			            SimpleDateFormat sdf = new SimpleDateFormat("M.d");
			            String day = sdf.format(new Date());
			            String title = "每日值班通报"+day+".doc";
			            response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(title,"UTF-8"));  
			            out = response.getOutputStream();  
			            byte[] buffer = new byte[512];  // 缓冲区  
			            int bytesToRead = -1;  
			            // 通过循环将读入的Word文件的内容输出到浏览器中  
			            while((bytesToRead = fin.read(buffer)) != -1) {  
			                out.write(buffer, 0, bytesToRead);  
			            }  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {  
		            if(fin != null) fin.close();  
		            if(out != null) out.close();  
		            if(file != null) file.delete(); // 删除临时文件  
		        }  
			}

		
			return "modules/oa/oaReportExport";

		
	}
	
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"reports/exportdaily"})
	public String reportsDaily(OaCruiseStat oaCruiseStat, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
            String fileName = "巡航数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
    		List<OaCruiseStat> dataList = oaCruisedataService.getStatics(oaCruiseStat);
    		List<OaExportDaily> dailyData = new ArrayList<OaExportDaily>();
    		int i = 1;
    		for(OaCruiseStat stat : dataList){
    			OaExportDaily daily= new OaExportDaily();
    			daily.setNumber(i++);
    			daily.setHxt(stat.getName());
    			daily.setZfry(stat.getCruisePeople());
    			daily.setXhsc(stat.getTotalTime());
    			daily.setXhsy("福北水道上段、福中水道、双涧沙水域、取水口水域");
    			daily.setFxjjwt("1、查双狮水域正常，浏海沙水道通航秩序，双涧沙施工区正常。\r\n2、查航道局码头施工作业正常。\r\n3. 43浮驱赶锚泊船 署光吉祥。\r\n4。瑞鸿拖8号靠46号红浮南侧码头现场驱赶。\r\n5. 43-42浮锚泊船队 鲁济宁拖2003,2736,2266,0034.");
    			daily.setBeizhu("beizhu");
    			daily.setZfrysl(stat.getCdzfry());
    			daily.setXhcs(stat.getXhcs());
    			daily.setXhlc(stat.getTotalTime()*12);
    			daily.setWzsl(stat.getFxwz());
    			daily.setQsk(stat.getQsk());
    			dailyData.add(daily);
    		}
    		new ExportExcel("巡航数据", OaExportDaily.class).setDataList(dailyData).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出巡航数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/oa/oaCruisedata/reports?repage";
	}
	
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"stat"})
	public String stat(OaCruiseStat oaCruiseStat, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(oaCruiseStat.getBeginHappenDate()==null || oaCruiseStat.getEndHappenDate()==null){
			oaCruiseStat.setBeginHappenDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
			oaCruiseStat.setEndHappenDate(DateUtils.addMonths(oaCruiseStat.getBeginHappenDate(), 1));
		}
		List<OaCruiseStat> dataList = oaCruisedataService.getStatics(oaCruiseStat);
		EchartsData echartsData = getEcharts(dataList);
		model.addAttribute("statics", dataList);
		model.addAttribute("echartsData",JSON.toJSON(echartsData));
		return "modules/oa/oaCruisedataStat";
	}
	
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"sumStat"})
	public String sumStat(@RequestParam Map<String, Object> paramMap,OaCruiseStat oaCruiseStat, HttpServletRequest request, HttpServletResponse response, Model model) {
		//获取部门id
		String depId = request.getParameter("office.id"); 
		String username = request.getParameter("user.name");
		
		
		oaCruiseStat.setCreate_dept(new Office(depId));
		oaCruiseStat.setUsername(username);
		List<OaCruiseStat> dataList = oaCruisedataService.getStaticsMonthly(oaCruiseStat);
		OaCruiseSumStat stat = new OaCruiseSumStat();
		stat.setBeginHappenDate(oaCruiseStat.getBeginHappenDate());
		stat.setEndHappenDate(oaCruiseStat.getEndHappenDate());
		stat.setBeginHappenDate1(oaCruiseStat.getBeginHappenDate1());
		stat.setEndHappenDate1(oaCruiseStat.getEndHappenDate1());
		if(oaCruiseStat.getBeginHappenDate1()!=null && oaCruiseStat.getEndHappenDate1()!=null){
			oaCruiseStat.setBeginHappenDate(oaCruiseStat.getBeginHappenDate1());
			oaCruiseStat.setEndHappenDate(oaCruiseStat.getEndHappenDate1());
			OaCruiseSumStat stat2 = new OaCruiseSumStat();
			List<OaCruiseStat> dataList2 = oaCruisedataService.getStaticsMonthly(oaCruiseStat);
			OaCruiseStat data2 = new OaCruiseStat();
			if(dataList2!=null && dataList2.size()>0){
				data2 = dataList2.get(0);
			}
			if(data2!=null){
				stat2.setCruiseTimes(data2.getXhcs());
				stat2.setHxsj(data2.getTotalTime());
				stat2.setXhsj(data2.getTotalTime());
				stat2.setYhcs(data2.getYehangcs());
				stat2.setYhsj(data2.getYehangTime());
				stat2.setXhlc((int)data2.getTotalTime()*12);
				stat2.setYhlc((int)data2.getYehangTime()*12);
			
				stat2.setZyhl(data2.getZyhl());
				stat2.setMdtbq(data2.getMdtbq());
				stat2.setSgzyq(data2.getSgzyq());
				stat2.setKymt(data2.getKymt());
				stat2.setPnq(data2.getPnq());
				stat2.setDangerPort(data2.getDangerPort());
				stat2.setQbq(data2.getQbq());
				stat2.setOther(data2.getOtherArea());
				stat2.setOtherwfxw(data2.getQtwfxw());
				
				stat2.setWfpf(data2.getWfpf());
				stat2.setWfhxgd(data2.getWfhxgd());
				stat2.setWfsgzy(data2.getWfsgzy());
				stat2.setZhbzyc(data2.getZhbzyc());
				stat2.setWfyzcs(data2.getWfyzcs());
				stat2.setCyjz(data2.getCyjz());
				stat2.setJtzz(data2.getJtzz());
				stat2.setBjcb(data2.getJzcb());
				stat2.setBjry(data2.getJzry());
				
				stat2.setCdzfry(data2.getCdzfry());
				stat2.setQtry(data2.getQtry());
				stat2.setQsk(data2.getQsk());
				stat2.setPsc(data2.getPsc());
				stat2.setFfxczl(data2.getFfxczl());
				model.addAttribute("statics2", stat2);

			}
		}
		
		OaCruiseStat data = new OaCruiseStat();
		if(dataList!=null && dataList.size()>0){
			data = dataList.get(0);
		}
		
		if(data!=null){
			stat.setCruiseTimes(data.getXhcs());
			stat.setHxsj(data.getTotalTime());
			stat.setXhsj(data.getTotalTime());
			stat.setYhcs(data.getYehangcs());
			stat.setYhsj(data.getYehangTime());
			stat.setXhlc((int)data.getTotalTime()*12);
			stat.setYhlc((int)data.getYehangTime()*12);
			
			stat.setZyhl(data.getZyhl());
			stat.setMdtbq(data.getMdtbq());
			stat.setSgzyq(data.getSgzyq());
			stat.setKymt(data.getKymt());
			stat.setPnq(data.getPnq());
			stat.setDangerPort(data.getDangerPort());
			stat.setQbq(data.getQbq());
			stat.setOther(data.getOtherArea());

			
			stat.setWfpf(data.getWfpf());
			stat.setWfhxgd(data.getWfhxgd());
			stat.setWfsgzy(data.getWfsgzy());
			stat.setZhbzyc(data.getZhbzyc());
			stat.setWfyzcs(data.getWfyzcs());
			stat.setOtherwfxw(data.getQtwfxw());
			stat.setCyjz(data.getCyjz());
			stat.setJtzz(data.getJtzz());
			stat.setBjcb(data.getJzcb());
			stat.setBjry(data.getJzry());
			
			stat.setCdzfry(data.getCdzfry());
			stat.setQtry(data.getQtry());
			stat.setQsk(data.getQsk());
			stat.setPsc(data.getPsc());
			stat.setFfxczl(data.getFfxczl());
			
		}
		
		model.addAttribute("officeId", paramMap.get("office.id"));
		model.addAttribute("officeName",paramMap.get("office.name"));
		model.addAttribute("userId", paramMap.get("username"));
		model.addAttribute("userName",paramMap.get("user.name"));
		model.addAttribute("statics", stat);
		return "modules/oa/oaCruisedataSumStat";
	}
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"sumStat1"})
	public String sumStat1(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		//获取部门id
		OaCruiseStat stat = new OaCruiseStat();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stat.setBeginHappenDate(sdf.parse("2017-4-1"));
		stat.setEndHappenDate(sdf.parse("2017-5-1"));
		Map<String,Double> ms = new HashMap<String,Double>();
		List<String> roleUsers = systemService.findUserByRoleId("fc782188e19b4c16b56240e46b2c615c");
		List<User> Users = new ArrayList<User>();
		Users = systemService.findUser(new User());
		List<User> cUsers = new ArrayList<User>();
		for(User u : Users){
			if(roleUsers.contains(u.getId())){
				cUsers.add(u);
			}
		}
		for(User user : cUsers){
			stat.setUsername(user.getName());
			stat.setCreate_dept(new Office());
			List<OaCruiseStat> dataList = oaCruisedataService.getStaticsMonthly(stat);
			OaCruiseStat s1 = new OaCruiseStat();
			if(dataList!=null && dataList.size()>0){
				s1 = dataList.get(0);
				if(s1!=null){
					
					double time = s1.getTotalTime();
					ms.put(user.getName(), time);
				}
				
			}
			
		}
		ms = MapUtils.sortMapByValue(ms);
		return "modules/oa/oaCruisedataSumStat";
	}
	
	
	private  EchartsData getEcharts(List<OaCruiseStat> dataList){
		List<String> category = new ArrayList<String>();
	    List<Double> serisData=new ArrayList<Double>();
	    List<Double> serisData1 = new ArrayList<Double>();
	     for (OaCruiseStat cruisedata : dataList) {
	            category.add(cruisedata.getName());
	            serisData.add(cruisedata.getTotalTime());
	            serisData1.add(cruisedata.getYehangTime());
	        }
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总巡航时间" ,"夜航时间"}));// 数据分组
	        List<Series> series = new ArrayList<Series>();// 纵坐标
	        series.add(new Series("总巡航时间", "bar", serisData));
	        series.add(new Series("夜航时间", "bar", serisData1));

	        EchartsData data = new EchartsData(legend, category, series);
	        return data;
	}
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"stat/export"})
	public String statExport(OaCruiseStat oaCruiseStat, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "巡航数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
    		List<OaCruiseStat> dataList = oaCruisedataService.getStatics(oaCruiseStat);
    		new ExportExcel("巡航数据", OaCruiseStat.class).setDataList(dataList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出巡航数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/oa/oaIllegal/?repage";
	}
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = "form")
	public String form(OaCruisedata oaCruisedata,  Model model) {
		Office currentUserOffice = oaCruisedata.getCurrentUser().getOffice();
		List<User> officeUsers = new ArrayList<User>();
		if(currentUserOffice.getChildDeptList()==null){
			officeUsers = systemService.findUserByOfficeId(currentUserOffice.getParentId());
		}else{
			officeUsers = systemService.findUserByOfficeId(currentUserOffice.getId());
		}
		List<String> roleUsers = systemService.findUserByRoleId("fc782188e19b4c16b56240e46b2c615c");
		List<User> cUsers = new ArrayList<User>();
		for(User u : officeUsers){
			if(roleUsers.contains(u.getId())){
				cUsers.add(u);
			}
		}
	
		String cruiseArea = systemService.getOfficeAddress(currentUserOffice);
		oaCruisedata.setCruiseArea(cruiseArea);
		if(StringUtils.isBlank(oaCruisedata.getId())){ //如果是新增数据
			//取出前一天的数据
			List<OaCruisedata> lists = oaCruisedataService.getCruisedataByDate(oaCruisedata, DateUtils.getNextDay(new Date()));
			if(lists != null && lists.size()>0){
				oaCruisedata = lists.get(0);
				oaCruisedata.setId(null);
				oaCruisedata.setEditable(true);
			}else{
				oaCruisedata.setEditable(true);
			}
		}
		OaCruisedata oa = new OaCruisedata();
		//港区海事处定制需求,每天的巡航水域是一致的
		if("07da27c293064f97a23b51daf8d1ea31".equals(UserUtils.getUser().getId()) || "1ba353c450784727bca2a12ef6d06564".equals(UserUtils.getUser().getId()) ){
			oa.setCruiseArea(cruiseArea);
		}else{
			oa.setCruiseArea(oaCruisedata.getCruiseArea());

		}
		oa.setEditable(oaCruisedata.isEditable());

		model.addAttribute("oaCruisedata", oa);
		model.addAttribute("cUsers", cUsers);
		return "modules/oa/oaCruisedataForm";
	}

	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = "formSeen")
	public String formSeen(OaCruisedata oaCruisedata, HttpServletResponse response,Model model) throws IOException {
		Office currentUserOffice = oaCruisedata.getCurrentUser().getOffice();
		List<User> officeUsers = new ArrayList<User>();
		if(currentUserOffice.getChildDeptList()==null){
			officeUsers = systemService.findUserByOfficeId(currentUserOffice.getParentId());
		}else{
			officeUsers = systemService.findUserByOfficeId(currentUserOffice.getId());
		}
		List<String> roleUsers = systemService.findUserByRoleId("fc782188e19b4c16b56240e46b2c615c");
		List<User> cUsers = new ArrayList<User>();
		for(User u : officeUsers){
			if(roleUsers.contains(u.getId())){
				cUsers.add(u);
			}
		}
		List<String> officeUserName = new ArrayList<String>();
		List<User> selectedUsers =new ArrayList<User>();
		for(User u : officeUsers){
			officeUserName.add(u.getName());
		}
		String selectedUser = oaCruisedata.getCruisePeople();
		if(StringUtils.isNotBlank(selectedUser)){
			List<User> users = systemService.findUserByNames(Arrays.asList(selectedUser.split(",")));
			for(User user : users){
				if(officeUserName.contains(user.getName())){
					selectedUsers.add(user);
				}
			}
		}
		for(User u : selectedUsers){
			if(cUsers.contains(u)){
				cUsers.remove(u);
			}
		}
		
		String cruiseArea = systemService.getOfficeAddress(currentUserOffice);
		oaCruisedata.setSolveProblem(StringEscapeUtils.unescapeHtml4(oaCruisedata.getSolveProblem()));
		//oaCruisedata.setCruiseArea(cruiseArea);
		if(StringUtils.isBlank(oaCruisedata.getId())){ //如果时新增数据
			//取出前一天的数据
			List<OaCruisedata> lists = oaCruisedataService.getCruisedataByDate(oaCruisedata, DateUtils.getNextDay(new Date()));
			if(lists != null && lists.size()>0){
				oaCruisedata = lists.get(0);
				oaCruisedata.setId(null);
				oaCruisedata.setSolveProblem(StringEscapeUtils.unescapeHtml4(oaCruisedata.getSolveProblem()));
			}
		}
		oaCruisedata.setEditable(false);
		Office dep = oaCruisedata.getCreateBy().getOffice();
		Office par = officeService.getParentOffice(dep);
		model.addAttribute("depName", par.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
		String dateStr = sdf.format(oaCruisedata.getCreateDate());
		model.addAttribute("dateStr", dateStr);
		model.addAttribute("oaCruisedata", oaCruisedata);
		OaIllegal oaIllegal = new OaIllegal();
		oaIllegal.setOaCruisedataId(oaCruisedata.getId());
		List<OaIllegal> illegalList = oaIllegalService.findByOaCruisedata(oaIllegal);
		model.addAttribute("illegalList", illegalList);
		OaCruiseStat param = new OaCruiseStat();
		param.setCruiseDataId(oaCruisedata.getId());
		List<OaCruiseStat> stat = oaCruisedataService.getStaticsById(param);
		try{
			param = stat.get(0);
		}catch(Exception e){
			
		}
		model.addAttribute("timeParam", param);
		//response.sendRedirect("modules/oa/oaCruisedataReport");
		return "modules/oa/oaCruisedataReport";
	}
	
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = "formEdit")
	public String formEdit(OaCruisedata oaCruisedata, Model model,RedirectAttributes redirectAttributes) {
		//判断是否可以修改，只可以 修改当天的数据，且只在下午三点之前能够修改
		Date date = oaCruisedata.getCreateDate();
		if(!DateUtils.isToday(date)){
			addMessage(redirectAttributes, "只能在当日16时之前修改当日数据！");
			return "redirect:"+Global.getAdminPath()+"/oa/oaCruisedata/?repage";
		}
		oaCruisedata.setEditable(true);
		oaCruisedata.setSolveProblem(StringEscapeUtils.unescapeHtml4(oaCruisedata.getSolveProblem()));
		Office currentUserOffice = oaCruisedata.getCurrentUser().getOffice();
		List<User> officeUsers = new ArrayList<User>();
		if(currentUserOffice.getChildDeptList()==null){
			officeUsers = systemService.findUserByOfficeId(currentUserOffice.getParentId());
		}else{
			officeUsers = systemService.findUserByOfficeId(currentUserOffice.getId());
		}
		List<String> roleUsers = systemService.findUserByRoleId("fc782188e19b4c16b56240e46b2c615c");
		List<User> cUsers = new ArrayList<User>();
		for(User u : officeUsers){
			if(roleUsers.contains(u.getId())){
				cUsers.add(u);
			}
		}
		List<String> officeUserName = new ArrayList<String>();
		List<User> selectedUsers =new ArrayList<User>();
		for(User u : officeUsers){
			officeUserName.add(u.getName());
		}
		String selectedUser = oaCruisedata.getCruisePeople();
		if(StringUtils.isNotBlank(selectedUser)){
			List<User> users = systemService.findUserByNames(Arrays.asList(selectedUser.split(",")));
			for(User user : users){
				if(officeUserName.contains(user.getName())){
					selectedUsers.add(user);
				}
			}
		}
		for(User u : selectedUsers){
			if(cUsers.contains(u)){
				cUsers.remove(u);
			}
		}
		model.addAttribute("oaCruisedata", oaCruisedata);
		model.addAttribute("cUsers", cUsers);
		model.addAttribute("cUsersSelected", selectedUsers);

		return "modules/oa/oaCruisedataForm";
	}
	
	@RequiresPermissions("oa:oaCruisedata:edit")
	@RequestMapping(value = "save")
	public String save( HttpServletRequest request,OaCruisedata oaCruisedata, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, oaCruisedata)){
			return form(oaCruisedata, model);
		}
		String wzcb = request.getParameter("wzcblist");
		JSONArray json = JSONArray.parseArray(wzcb);
		oaCruisedataService.save(oaCruisedata);
		
		if(json.size()>0){
			  for(int i=0;i<json.size();i++){
			    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    OaIllegal illegal = new OaIllegal();
				String qtwf = job.getString("qtwf");
				if(StringUtils.isNotBlank(qtwf) && "8".equals(job.getString("illegalDetailsVal"))){//如果其它违法行为不为空，添加到字典中
						Dict dict = new Dict();
						dict.setLabel(qtwf);
						dict.setValue(IdGen.uuid());
						dict.setType("illegal_detail");
						dict.setCreateBy(oaCruisedata.getCreateBy());
						dict.setCreateDate(oaCruisedata.getCreateDate());
						dict.setDescription("违章类别，用户添加");
						dict.setSort(1000);
						dictService.save(dict);
						job.put("illegalDetailsVal",dict.getValue());
					}
			    illegal.setIllegalDetail(job.getString("illegalDetailsVal"));
			    illegal.setShipName(job.getString("shipName"));
			    illegal.setDealWay(job.getString("dealwaysVal"));
			    illegal.setIllegalType(job.getString("illegalTypeVal"));
			    illegal.setHappenLocation(job.getString("happenLocation"));
			    illegal.setBeizhu(job.getString("beizhu"));
			    illegal.setCreateBy(oaCruisedata.getCreateBy());
			    illegal.setOaCruisedataId(oaCruisedata.getId());
			    illegal.setHappenDate(new Date());
			    oaIllegalService.save(illegal);
			  }
			}
		
		String cruiseArea = request.getParameter("cruisearealist");
		JSONArray area = JSONArray.parseArray(cruiseArea);
		if(area.size()>0){
			  for(int i=0;i<area.size();i++){
			    JSONObject job = area.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    OaCruisedataArea cArea = new OaCruisedataArea();
			    cArea.setId(UUID.randomUUID().toString());
			    cArea.setOaCruiseareaId(job.getString("appIds"));
			    cArea.setOaCruisedataId(oaCruisedata.getId());
			    cArea.setTimes(job.getInteger("num"));
			    cArea.setCreateDate(new Date());
			    cArea.setCreateBy(oaCruisedata.getCreateBy().getId());
			    oaCruisedataService.save(cArea);
			  }
			}
		addMessage(redirectAttributes, "保存巡航数据成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaCruisedata/?repage";
	}
	
	@RequiresPermissions("oa:oaCruisedata:edit")
	@RequestMapping(value = "delete")
	public String delete(OaCruisedata oaCruisedata, RedirectAttributes redirectAttributes) {
		if(DateUtils.isToday(oaCruisedata.getCreateDate())){
			oaCruisedataService.delete(oaCruisedata);
			addMessage(redirectAttributes, "删除巡航数据成功");
			return "redirect:"+Global.getAdminPath()+"/oa/oaCruisedata/?repage";
		}else{
			addMessage(redirectAttributes, "只能删除当天数据");
			return "redirect:"+Global.getAdminPath()+"/oa/oaCruisedata/?repage";
		}
	}

}