package com.thinkgem.jeesite.modules.cruise.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.MapUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStatics;
import com.thinkgem.jeesite.modules.oa.entity.EchartsData;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseSumStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.oa.entity.Series;
import com.thinkgem.jeesite.modules.oa.service.OaCruiseareaService;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;
import com.thinkgem.jeesite.modules.oa.service.OaIllegalService;
import com.thinkgem.jeesite.modules.oa.service.OaInportShipService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "${adminPath}/cruise/statics")
public class ChartsController extends BaseController{
	@Autowired 
	OaCruisedataService oaCruisedataService;
	@Autowired 
	OaCruiseareaService oaCruiseareaService;
	@Autowired
	SystemService systemService;
	@Autowired
	OaIllegalService oaIllegalService;
	@Autowired
	OaInportShipService oaInportShipService;
	@RequiresPermissions("oa:oaCruisedata:view")
	@RequestMapping(value = {"paintEcharts"})
	public String paintEcharts(HttpServletRequest request, HttpServletResponse response,CruiseStatics cruiseStatics,Model model) throws IOException, ParseException {
		if(cruiseStatics.getBeginHappenDate()==null || cruiseStatics.getEndHappenDate()==null){
			cruiseStatics.setBeginHappenDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
			cruiseStatics.setEndHappenDate(DateUtils.addMonths(cruiseStatics.getBeginHappenDate(), 1));
		}
		List<OaCruiseStat> dataList = oaCruisedataService.getStatics(cruiseStatics.getOaCruiseStat());
		EchartsData echartsData = getEcharts(dataList);
		EchartsData echartsCount = getEchartsCounts(dataList);
		EchartsData echartsPeople = getEchartsPeople(cruiseStatics);
		EchartsData echartsPeopleNum = getEchartsPeopleNum(cruiseStatics);
		EchartsData cruiseArea = getCruiseArea(new OaCruisedataArea());
		EchartsData illegal = getEchartsIllegal(cruiseStatics);
		model.addAttribute("statics", dataList);
		model.addAttribute("echartsData",JSON.toJSON(echartsData));
		model.addAttribute("echartsCount",JSON.toJSON(echartsCount));
		model.addAttribute("echartsPeople",JSON.toJSON(echartsPeople));
		model.addAttribute("echartsPeopleNum",JSON.toJSON(echartsPeopleNum));
		model.addAttribute("cruiseArea",JSON.toJSON(cruiseArea));
		model.addAttribute("illegal",JSON.toJSON(illegal));
		List<String> arr = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cruiseStatics.getBeginHappenDate());
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		String end = sdf.format(cruiseStatics.getEndHappenDate());
		while(!end.equals(sdf.format(cal.getTime()))){
			arr.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1); //日期加1天  
		}
		List<Double> cruiseTotalTime = oaCruisedataService.getCruiseTimeByDate(cruiseStatics);
		List<Double> cruiseYehangTime = oaCruisedataService.getCruiseYehangByDate(cruiseStatics);
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
		model.addAttribute("echartsLegend",JSON.toJSON(arr));
		model.addAttribute("cruiseTotalTime",JSON.toJSON(cruiseTotalTime));
		model.addAttribute("cruiseYehangTime",JSON.toJSON(cruiseYehangTime));
		model.addAttribute("pie",JSON.toJSON(pie));
		Map<String,Object> mo = new HashMap<String,Object>();
		
		mo = oaInportShipService.getInportShipStatics();
		model.addAttribute("mo",JSON.toJSON(mo));

		return "modules/cruise/paintEcharts";
	}

	private EchartsData getEchartsCounts(List<OaCruiseStat> dataList){
		List<String> category = new ArrayList<String>();
	    List<Integer> serisData=new ArrayList<Integer>();
	    List<Integer> serisData1 = new ArrayList<Integer>();
	     for (OaCruiseStat cruisedata : dataList) {
	            category.add(cruisedata.getName());
	            serisData.add(cruisedata.getXhcs());
	            serisData1.add(cruisedata.getYehangcs());
	        }
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总巡航次数" ,"夜航次数"}));// 数据分组
	        List<Series> series = new ArrayList<Series>();// 纵坐标
	        series.add(new Series("总巡航次数", "bar", serisData));
	        series.add(new Series("夜航次数", "bar", serisData1));
	        
	        EchartsData data = new EchartsData(legend, category, series);
	        return data;
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
	
	private  EchartsData getEchartsIllegal(CruiseStatics cruiseStatics){
		List<String> category = new ArrayList<String>();
	    List<Double> serisData=new ArrayList<Double>();
	    List<Double> serisData1 = new ArrayList<Double>();
		List<OaCruiseStat> dataList = oaCruisedataService.getStatics(cruiseStatics.getOaCruiseStat());
		OaIllegal oaIllegal = new OaIllegal();
		oaIllegal.setBeginHappenDate(cruiseStatics.getBeginHappenDate());
		oaIllegal.setEndHappenDate(cruiseStatics.getEndHappenDate());
	    Map<String,Double> ms = oaIllegalService.getIllegalCount(oaIllegal);
	    ms = MapUtils.sortMapByValue(ms);
	     for (OaCruiseStat cruisedata : dataList) {
	            category.add(cruisedata.getName());
	            serisData.add(cruisedata.getWfxwTotal().doubleValue());
	            serisData1.add(ms.get(cruisedata.getName()));
	        }
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "查处违章数量","登记违章数量" }));// 数据分组
	        List<Series> series = new ArrayList<Series>();// 纵坐标
	        series.add(new Series("查处违章数量", "bar", serisData));
	        series.add(new Series("登记违章数量", "bar", serisData1));
	        EchartsData data = new EchartsData(legend, category, series);
	        return data;
	}
	private  EchartsData getCruiseArea(OaCruisedataArea area){
			List<String> category = new ArrayList<String>();
		    List<Double> serisData=new ArrayList<Double>();
		    List<Double> serisData1 = new ArrayList<Double>();
		    Map<String,Double> ms = new HashMap<String,Double>();
		    ms = oaCruiseareaService.getCruiseAreaCount(area);
		    ms = MapUtils.sortMapByValue(ms);
		    int i = 0;
			for (Entry<String,Double> entry : ms.entrySet()) {
				 if(i>10) break;
				 category.add(entry.getKey());
		         serisData.add(entry.getValue());
		         i++;
		        
			}
		    List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总巡航次数"}));// 数据分组
		    List<Series> series = new ArrayList<Series>();// 纵坐标
		    series.add(new Series("总巡航次数", "bar", serisData));

	        EchartsData data = new EchartsData(legend, category, series);
	        return data;
	}
	private  EchartsData getEchartsPeople(CruiseStatics cruiseStatics) throws ParseException{
		List<String> category = new ArrayList<String>();
	    List<Double> serisData=new ArrayList<Double>();
	    List<Double> serisData1 = new ArrayList<Double>();
	    OaCruiseStat stat = cruiseStatics.getOaCruiseStat();
		List<OaCruiseStat> dataList = new ArrayList<OaCruiseStat>();
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
			dataList = oaCruisedataService.getStaticsMonthly(stat);
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
		int i = 0;
		for (Entry<String,Double> entry : ms.entrySet()) {
			 if(i>10) break;
			 category.add(entry.getKey());
	         serisData.add(entry.getValue());
	         i++;
	        
		}
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总巡航时间" }));// 数据分组
	        List<Series> series = new ArrayList<Series>();// 纵坐标
	        series.add(new Series("总巡航时间", "bar", serisData));

	        EchartsData data = new EchartsData(legend, category, series);
	        return data;
	}
	
	private  EchartsData getEchartsPeopleNum(CruiseStatics cruiseStatics) {
		List<String> category = new ArrayList<String>();
	    List<Double> serisData=new ArrayList<Double>();
	    OaCruiseStat stat = cruiseStatics.getOaCruiseStat();
		List<OaCruiseStat> dataList = new ArrayList<OaCruiseStat>();
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
			dataList = oaCruisedataService.getStaticsMonthly(stat);
			OaCruiseStat s1 = new OaCruiseStat();
			if(dataList!=null && dataList.size()>0){
				s1 = dataList.get(0);
				if(s1!=null){
					
					double time = (int)(s1.getXhcs()*0.8);
					ms.put(user.getName(), time);
				}
				
			}
			
		}
		ms = MapUtils.sortMapByValue(ms);
		int i = 0;
		for (Entry<String,Double> entry : ms.entrySet()) {
			 if(i>10) break;
			 category.add(entry.getKey());
	         serisData.add(entry.getValue());
	         i++;
	        
		}
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总巡航次数"}));// 数据分组
	        List<Series> series = new ArrayList<Series>();// 纵坐标
	        series.add(new Series("总巡航次数", "bar", serisData));

	        EchartsData data = new EchartsData(legend, category, series);
	        return data;
	}
	
	private  EchartsData getEchartsPie(CruiseStatics cruiseStatics) {
		List<String> category = new ArrayList<String>();
	    List<Double> serisData=new ArrayList<Double>();
	    OaCruiseStat stat = cruiseStatics.getOaCruiseStat();
		List<OaCruiseStat> dataList = oaCruisedataService.getStatics(cruiseStatics.getOaCruiseStat());

	   
	    List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总巡航次数"}));// 数据分组
	    List<Series> series = new ArrayList<Series>();// 纵坐标
	    series.add(new Series("总巡航次数", "bar", serisData));

	    EchartsData data = new EchartsData(legend, category, series);
	    return data;
	}
}
