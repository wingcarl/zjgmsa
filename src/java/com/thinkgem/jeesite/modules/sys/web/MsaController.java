package com.thinkgem.jeesite.modules.sys.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStatics;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseWeekPlan;
import com.thinkgem.jeesite.modules.cruise.service.CruiseWeekPlanService;
import com.thinkgem.jeesite.modules.msa.entity.MsaIndexStat;
import com.thinkgem.jeesite.modules.msa.entity.MsaTideHourly;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;
import com.thinkgem.jeesite.modules.oa.service.OaDigitalcruiseService;
import com.thinkgem.jeesite.modules.oa.service.OaInportShipService;
import com.thinkgem.jeesite.modules.schedule.entity.ScheduleDetail;
import com.thinkgem.jeesite.modules.schedule.service.ScheduleDetailService;
import com.thinkgem.jeesite.modules.sys.service.MsaService;


@Controller
@RequestMapping(value = "${adminPath}/sys/msa/index")
public class MsaController {
	@Autowired
	private OaCruisedataService oaCruisedataService;
	@Autowired
	private MsaService msaService;
	@Autowired
	private OaDigitalcruiseService oaDigitalcruiseService;
	@Autowired
	private OaInportShipService oaInportShipService;
	@Autowired
	private ScheduleDetailService scheduleDetailService;
	@Autowired
	private CruiseWeekPlanService cruiseWeekPlanService;
	@RequestMapping(value = {""})
	public String index(Model model) {
		CruiseStatics cs = new CruiseStatics();
		
		List<String> arr = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		cs.setBeginHappenDate(cal.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		String end = sdf.format(DateUtils.addMonths(cal.getTime(), 1));
		while(!end.equals(sdf.format(cal.getTime()))){
			arr.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1); //日期加1天  
		}
		
		cs.setEndHappenDate(cal.getTime());
		
		List<Double> cruiseTotalTime = oaCruisedataService.getCruiseTimeByDate(cs);
		List<Double> cruiseYehangTime = oaCruisedataService.getCruiseYehangByDate(cs);
		List<String> cruiseStatics = oaCruisedataService.getTodayStatics(DateUtils.getDate());
		List<String> digitalStatics = oaDigitalcruiseService.getTodayStatics(DateUtils.getDate());
		List<String> inportStatics = oaInportShipService.getTodayStatics(DateUtils.getDate());
		Map<String,Object> today = new HashMap<String,Object>();
		today = getStatics(cruiseStatics,digitalStatics,inportStatics);
		Map<String,Object> pie = new HashMap<String,Object>();
		Double sumCruise = 0.0;
		Double sumYehang = 0.0;
		for(Double d : cruiseTotalTime){
			sumCruise += d;
		}
		for(Double d : cruiseYehangTime){
			sumYehang += d;
		}
		pie.put("sumCruise", sumCruise);
		pie.put("sumYehang", sumYehang);
		model.addAttribute("today", JSON.toJSON(today));
		model.addAttribute("echartsLegend",JSON.toJSON(arr));
		model.addAttribute("cruiseTotalTime",JSON.toJSON(cruiseTotalTime));
		model.addAttribute("cruiseYehangTime",JSON.toJSON(cruiseYehangTime));
		model.addAttribute("pie",JSON.toJSON(pie));
		List<ScheduleDetail> sds = scheduleDetailService.getByDate(DateUtils.getDate());
		Map<String,Object> mo = new HashMap<String,Object>();
		Map<String,Object> weekPlan = new HashMap<String,Object>();
		weekPlan = cruiseWeekPlanService.findListByDate(new Date());
		model.addAttribute("weekPlan",JSON.toJSON(weekPlan));
		for(ScheduleDetail s : sds){
			if("船舶交通管理中心".equals(s.getOffice().getName())){
				Calendar cal1 = Calendar.getInstance();
				int hour = cal1.get(Calendar.HOUR_OF_DAY);
				mo.put("vtsld", s.getUserList4());
				if(hour>=8 && hour<16){
					mo.put("vts", s.getUserList2());
				}else if(hour>=16 && hour<24){
					mo.put("vts", s.getUserList3());
				}else if(hour>=0 && hour<8){
					mo.put("vts", s.getUserList1());
				}
			}else if("指挥中心".equals(s.getOffice().getName())){
				mo.put("zhzxbb", s.getUserList1());
				mo.put("zhzxyb", s.getUserList3());
				mo.put("zbzc", s.getUserList4());
				mo.put("zbld", s.getUserList2());
				
			}else if("海巡执法支队".equals(s.getOffice().getName())){
				mo.put("zfzdbb", s.getUserList1());
				mo.put("fjsyb", s.getUserList3());
				mo.put("zfzdyb", s.getUserList4());
			}else if("港区海事处".equals(s.getOffice().getName())){
				mo.put("gqc1", s.getUserList1());
				mo.put("gqc2", s.getUserList2());
				mo.put("gqcyb", s.getUserList3());
				mo.put("gqcss", s.getUserList4());
				
			}else if("锦丰海事处".equals(s.getOffice().getName())){
				mo.put("jfc1", s.getUserList1());
				mo.put("jfc2", s.getUserList2());
				mo.put("jfcyb", s.getUserList3());
				mo.put("jfcss", s.getUserList4());
				
			}else if("沪通大桥办事处".equals(s.getOffice().getName())){
				mo.put("dqbb", s.getUserList1());
				mo.put("dqyb", s.getUserList4());
				
				
			}else if("南丰办事处".equals(s.getOffice().getName())){
				mo.put("nfcbb", s.getUserList1());
				mo.put("nfcyb", s.getUserList4());
				
			}else if("保税区办事处".equals(s.getOffice().getName())){
				mo.put("bsqbb", s.getUserList1());
				mo.put("bsqyb", s.getUserList4());
			}else if("张家港海事局".equals(s.getOffice().getName())){
				mo.put("jld", s.getUserList4());
			}
			model.addAttribute("mo",JSON.toJSON(mo));
		}
		
		Map<String,Object> md = new HashMap<String,Object>();
		Map<String,Object> scheduleMap = getSchedule();
		model.addAttribute("scheduleMap", JSON.toJSON(scheduleMap.get("calList")));
		md = oaInportShipService.getInportShipStatics();
		model.addAttribute("md",JSON.toJSON(md));
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM");
		model.addAttribute("sdfd",sdfd.format(new Date()));
		return "modules/sys/msa/index";
	}
	
	@RequestMapping(value = "index2")
	public String index2(Model model) {
		return "modules/sys/msa/index2";
	}
	
	 @RequestMapping(value = "findCruiseAndIllegal", method=RequestMethod.POST)
	    public String findTideHourly(HttpServletResponse resp) throws IOException{
		    List<MsaIndexStat> dataList = msaService.getStatics();
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
		        
		     String json = mapper.writeValueAsString(dataList);    //将list中的对象转换为Json格式的数组
		        
		     System.out.println(json);
			 resp.setContentType("text/html; charset=utf-8");
		     resp.getWriter().write(json);    
			 return null;
		 }

	 
	 @RequestMapping(value = "findDangerStat", method=RequestMethod.POST)
	    public String findDangerStat(HttpServletResponse resp) throws IOException{
		    List<MsaIndexStat> dataList = msaService.getDangerStatics();
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
		        
		     String json = mapper.writeValueAsString(dataList);    //将list中的对象转换为Json格式的数组
		        
		     System.out.println(json);
			 resp.setContentType("text/html; charset=utf-8");
		     resp.getWriter().write(json);    
			 return null;
		 }
	 	
	 	@RequestMapping(value = "findIllegalStat", method=RequestMethod.POST)
	    public String findIllegalStat(HttpServletResponse resp) throws IOException{
		    List<List<MsaIndexStat>> dataList = msaService.getIllegalStatics();
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
		        
		     String json = mapper.writeValueAsString(dataList);    //将list中的对象转换为Json格式的数组
		        
		     System.out.println(json);
			 resp.setContentType("text/html; charset=utf-8");
		     resp.getWriter().write(json);    
			 return null;
		 }
	 @RequestMapping(value = "findDangerPie", method=RequestMethod.POST)
	    public String findDangerPie(HttpServletResponse resp) throws IOException{
		    List<MsaIndexStat> dataList = msaService.getDangerPie();
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
		        
		     String json = mapper.writeValueAsString(dataList);    //将list中的对象转换为Json格式的数组
		        
		     System.out.println(json);
			 resp.setContentType("text/html; charset=utf-8");
		     resp.getWriter().write(json);    
			 return null;
		 }
	 @RequestMapping(value = "findDangerPie2", method=RequestMethod.POST)
	    public String findDangerPie2(HttpServletResponse resp) throws IOException{
		    List<MsaIndexStat> dataList = msaService.getDangerPie2();
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
		        
		     String json = mapper.writeValueAsString(dataList);    //将list中的对象转换为Json格式的数组
		        
		     System.out.println(json);
			 resp.setContentType("text/html; charset=utf-8");
		     resp.getWriter().write(json);    
			 return null;
		 }
	 @RequestMapping(value = "findDangerByMonth", method=RequestMethod.POST)
	    public String findDangerByMonth(HttpServletResponse resp) throws IOException{
		    List<MsaIndexStat> dataList = msaService.getDangerStaticsByMonth();
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
		        
		     String json = mapper.writeValueAsString(dataList);    //将list中的对象转换为Json格式的数组
		        
		     System.out.println(json);
			 resp.setContentType("text/html; charset=utf-8");
		     resp.getWriter().write(json);    
			 return null;
		 }

	private Map<String,Object> getSchedule(){
		Map<String,Object> scheduleMap = new HashMap<String,Object>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		ScheduleDetail schedule = new ScheduleDetail();
		schedule.setBeginHappenDate(cal.getTime());
		schedule.setEndHappenDate(DateUtils.addMonths(cal.getTime(), 1));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		List<List<String>> calList = scheduleDetailService.findListByUser(schedule);
		scheduleMap.put("calList",calList);
		return scheduleMap;
	}
	
	
	private Map<String,Object> getStatics(List<String> cruiseStatics,List<String> digitalStatics,List<String> inportStatics){
		Map<String,Object> ms = new HashMap<String,Object>();
		int count = 0;
		List<String> ss = new ArrayList<String>();
		ss.add("海巡06806");
		ss.add("海巡06808");
		ss.add("海巡06809");
		ss.add("海巡06810");
		ss.add("海巡06811");
		ss.add("海巡06812");
		ss.add("海巡06813");
		ss.add("海巡06816");
		for(String s : cruiseStatics){
			ss.remove(s);
			count++;
		}
		List<String> sd = new ArrayList<String>();
		sd.add("海巡执法支队");
		sd.add("港区海事处");
		sd.add("锦丰海事处");
		sd.add("保税区办事处");
		sd.add("南丰办事处");
		sd.add("沪通大桥办事处");
		sd.add("指挥中心");
		for(String s : digitalStatics){
			sd.remove(s);
			count++;
		}
		List<String> si = new ArrayList<String>();
		si.add("海巡执法支队");
		si.add("港区海事处");
		si.add("锦丰海事处");
		si.add("保税区办事处");
		si.add("南丰办事处");
		si.add("沪通大桥办事处");
		for(String s : inportStatics){
			si.remove(s);
			count++;
		}
		ms.put("count", count);
		ms.put("ss", ss);
		ms.put("sd", sd);
		ms.put("si", si);
		return ms;
	}
}
