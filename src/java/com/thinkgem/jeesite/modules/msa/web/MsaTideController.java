/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.MapUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.msa.entity.MsaReport;
import com.thinkgem.jeesite.modules.msa.entity.MsaReportStat;
import com.thinkgem.jeesite.modules.msa.entity.MsaTideHourly;
import com.thinkgem.jeesite.modules.msa.service.MsaReportService;
import com.thinkgem.jeesite.modules.msa.service.TideService;
import com.thinkgem.jeesite.modules.oa.entity.EchartsData;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;
import com.thinkgem.jeesite.modules.oa.entity.Series;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 政务信息爬虫获取Controller
 * @author dylan wang
 * @version 2017-05-26
 */
@Controller
@RequestMapping(value = "${adminPath}/msa/msaTide")
public class MsaTideController extends BaseController {

	@Autowired
	TideService tideService;
	 @RequestMapping(value = "findTideHourly", method=RequestMethod.POST)
    public String findTideHourly(HttpServletResponse resp) throws IOException{
		 MsaTideHourly hour = new MsaTideHourly();
		 List<MsaTideHourly> tideList = tideService.findTideHourly(hour);
	     ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
	        
	     String json = mapper.writeValueAsString(tideList);    //将list中的对象转换为Json格式的数组
	        
	     System.out.println(json);
		 resp.setContentType("text/html; charset=utf-8");
	     resp.getWriter().write(json);    
		 return null;
	 }
	/*@RequestMapping(value = {"list", ""})
	public String list(MsaReport msaReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsaReport> page = msaReportService.findPage(new Page<MsaReport>(request, response), msaReport); 
		model.addAttribute("page", page);
		try{
			if(msaReport.getDepid()!=null){
				model.addAttribute("depName",officeService.get(msaReport.getDepid()).getName());

			}
		}catch(Exception e){
			
		}
		
		return "modules/msa/msaReportList";
	}
	/**
	 * 导入每日在港船舶数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
/*	@RequiresPermissions("msa:msaReport:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/msa/msaReport/list?repage";
		}
		try {
			//excel模板路径  
            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());  
            //读取excel模板  
            HSSFWorkbook wb = new HSSFWorkbook(fs);  
            //读取了模板内所有sheet内容  
            HSSFSheet sheet = wb.getSheetAt(0);  
            int i = 2;
            String title = sheet.getRow(2).getCell(1).getStringCellValue();
            List<MsaReport> msaReportList = new ArrayList<MsaReport>();
            while(!"".equals(title) && title!=null){
            	try{
	            	title = sheet.getRow(i).getCell(1).getStringCellValue();
	            	title = title.split(" ")[1];
	            	if(!"".equals(title) && title!=null){
		            	MsaReport msaReport = new MsaReport();
		            	Map<String,Double> ms = new HashMap<String,Double>();
		            	
		            		ms.put("bjnw", sheet.getRow(i).getCell(4).getNumericCellValue());
			            	ms.put("bjww", sheet.getRow(i).getCell(5).getNumericCellValue());
			            	ms.put("jswz", sheet.getRow(i).getCell(6).getNumericCellValue());
			            	ms.put("jstp", sheet.getRow(i).getCell(7).getNumericCellValue());
			            	ms.put("jsww", sheet.getRow(i).getCell(8).getNumericCellValue());
			            	ms.put("jszt", sheet.getRow(i).getCell(10).getNumericCellValue());
			            	String type = this.getType(ms);
			            	msaReport.setTitle(title);
			            	
			            	if(title.contains("（专题）") || title.contains("（调研）") || title.contains("(专题)") || title.contains("(调研)")){
			            		if(!"".equals(type) && type!=null){
			            			type += ",2";
			            		}else{
			            			type += "2";
			            		}
			            			
			            	}else{
			            		if(!"".equals(type) && type!=null){
			            			type += ",1";
			            		}else{
			            			type += "1";
			            		}
			            		
			            	}
			            	msaReport.setType(type);
			            	msaReportList.add(msaReport);
		            	
		            	
	            	}
            	}catch(Exception e){
            		System.out.println(i+sheet.getRow(i).getCell(1).getStringCellValue()+"导入出错");
            	}
            	i++;
            }
            
            msaReportService.updateMsaReportTypes(msaReportList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + adminPath + "/oa/oaInportShip/list?repage";
    }
	
	private String getType(Map<String,Double> ms){
		List<String> types = new ArrayList<String>();
		if(ms.get("bjnw")==1.0){
			types.add("7");
		}
		if(ms.get("bjww")==1.0){
			types.add("8");
		}
		if(ms.get("jswz")==1.0){
			types.add("3");
		}
		if(ms.get("jstp")==1.0){
			types.add("4");
		}
		if(ms.get("jsww")==1.0){
			types.add("5");
		}
		if(ms.get("jszt")==1.0){
			types.add("6");
		}
		Collections.sort(types);
		String type = StringUtils.join(types.toArray(),",");
		return type;
	}
	@RequiresPermissions("msa:msaReport:view")
	@RequestMapping(value = {"stat"})
	public String stat(MsaReportStat msaReportStat, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<MsaReportStat> list = msaReportService.findMsaReportStatics(msaReportStat); 
		model.addAttribute("list", list);
		try{
			if(msaReportStat.getDepid()!=null){
				model.addAttribute("depName",officeService.get(msaReportStat.getDepid()).getName());
			}
			
		}catch(Exception e){
			
		}
		EchartsData echartsData = this.getCount(list);
		model.addAttribute("echartsData",JSON.toJSON(echartsData));
		return "modules/msa/msaReportStat";
	}
	
	
	private  EchartsData getCount(List<MsaReportStat> list){
		List<String> category = new ArrayList<String>();
	    List<Double> serisData=new ArrayList<Double>();
	    Map<String,Double> ms = new HashMap<String,Double>();
	    Collections.sort(list, new Comparator<MsaReportStat>(){

			@Override
			public int compare(MsaReportStat m1, MsaReportStat m2) {
				return m2.getCount()-m1.getCount();
			}
	    });
	    int length = list.size()<10?list.size():10;
	    for(int i=0;i<length;i++){
	    	category.add(list.get(i).getName());
	    	serisData.add(Double.parseDouble(list.get(i).getCount().toString()));
	    }
	 
	    List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "发表政务信息数量"}));// 数据分组
	    List<Series> series = new ArrayList<Series>();// 纵坐标
	    series.add(new Series("发表政务信息数量", "bar", serisData));

        EchartsData data = new EchartsData(legend, category, series);
        return data;
	}
	
	
	@RequiresPermissions("msa:msaReport:view")
	@RequestMapping(value = "form")
	public String form(MsaReport msaReport, Model model) {
		if(msaReport.getType()!=null){
			List<String> strList = CollectionUtils.arrayToList(msaReport.getType().split(","));
			List<Dict> dictList = DictUtils.getDictList("msa_report_type");
			List<Dict> typeList  = new ArrayList<Dict>();
			for(Dict d : dictList){
				if(strList.contains(d.getValue())){
					typeList.add(d);
				}
			}
			msaReport.setTypeList(typeList);
		}
		
		model.addAttribute("msaReport", msaReport);
		return "modules/msa/msaReportForm";
	}

	@RequiresPermissions("msa:msaReport:edit")
	@RequestMapping(value = "save")
	public String save(MsaReport msaReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msaReport)){
			return form(msaReport, model);
		}
		List<Dict> typeList = msaReport.getTypeList();
		List<String> valueList = new ArrayList<String>();
		for(Dict t : typeList){
			valueList.add(t.getId());
		}
		msaReport.setType(StringUtils.join(valueList.toArray(),","));
		msaReportService.save(msaReport);
		addMessage(redirectAttributes, "保存政务信息成功");
		return "redirect:"+Global.getAdminPath()+"/msa/msaReport/?repage";
	}
	
	@RequiresPermissions("msa:msaReport:edit")
	@RequestMapping(value = "delete")
	public String delete(MsaReport msaReport, RedirectAttributes redirectAttributes) {
		msaReportService.delete(msaReport);
		addMessage(redirectAttributes, "删除政务信息成功");
		return "redirect:"+Global.getAdminPath()+"/msa/msaReport/?repage";
	}*/

}