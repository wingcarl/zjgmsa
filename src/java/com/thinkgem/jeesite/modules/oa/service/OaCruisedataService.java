/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FreeMarkers;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.WordGenerator;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.OaExportDaily;
import com.thinkgem.jeesite.modules.oa.entity.OaReport;
import com.thinkgem.jeesite.modules.oa.entity.OaTime;
import com.thinkgem.jeesite.modules.oa.entity.PortData;

import com.thinkgem.jeesite.modules.schedule.entity.ScheduleDetail;
import com.thinkgem.jeesite.modules.schedule.service.ScheduleDetailService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.test.dao.OaCruisetimeDao;
import com.thinkgem.jeesite.modules.test.entity.OaCruisetime;
import com.thinkgem.jeesite.modules.vts.entity.VtsWorkData;
import com.thinkgem.jeesite.modules.vts.service.VtsWorkDataService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import com.thinkgem.jeesite.modules.cruise.entity.CruiseStatics;
import com.thinkgem.jeesite.modules.msa.entity.MsaHutongProject;
import com.thinkgem.jeesite.modules.msa.entity.MsaTonghang;
import com.thinkgem.jeesite.modules.msa.service.MsaHutongProjectService;
import com.thinkgem.jeesite.modules.msa.service.MsaTonghangService;
import com.thinkgem.jeesite.modules.msa.service.TideService;
import com.thinkgem.jeesite.modules.oa.dao.OaCruisedataDao;

/**
 * 测试表Service
 * @author 王浩宇
 * @version 2017-02-23
 */
@Service
@Transactional(readOnly = true)
public class OaCruisedataService extends CrudService<OaCruisedataDao, OaCruisedata> {

	@Autowired
	private OaCruisetimeDao oaCruisetimeDao;
	@Autowired
	private OaDigitalcruiseService oaDigitalcruiseService;
	@Autowired
	private OaCruisedataDao oaCruisedataDao;
	@Autowired
	private VtsWorkDataService  vtsWorkDataService;
	@Autowired
	private ScheduleDetailService  scheduleDetailService;
	@Autowired
	private MsaHutongProjectService  msaHutongProjectService;
	@Autowired
	private MsaTonghangService  msaTonghangService;
	@Autowired
	private TideService tideService;
	@Autowired
	private OfficeService officeService;
	public OaCruisedata get(String id) {
		OaCruisedata oaCruisedata = super.get(id);
		oaCruisedata.setOaCruisetimeList(oaCruisetimeDao.findList(new OaCruisetime(oaCruisedata)));
		/*Integer yhsc = 0;
		double yhtime = 0;
		Calendar calendar_start = Calendar.getInstance();
		Calendar calendar_end = Calendar.getInstance();

		for(OaCruisetime t : oaCruisedata.getOaCruisetimeList()){
			
			calendar_start.setTime(t.getStartTime());
			calendar_end.setTime(t.getEndTime());
			if(((calendar_start.get(Calendar.HOUR_OF_DAY)<6 || calendar_start.get(Calendar.HOUR_OF_DAY)>=18) && (calendar_end.get(Calendar.HOUR_OF_DAY)<6 || calendar_end.get(Calendar.HOUR_OF_DAY)>=18))){
				yhsc++;
				double se = calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis();
				yhtime += (se/(3600*100));
			}
			else if((calendar_start.get(Calendar.HOUR_OF_DAY)<6 || calendar_start.get(Calendar.HOUR_OF_DAY)>=18)){
				calendar_end.set(Calendar.HOUR_OF_DAY, 6);
				yhsc++;
				double se =  calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis();
				yhtime += (se/(3600*1000));
				calendar_end.setTime(t.getEndTime());
			}else if((calendar_end.get(Calendar.HOUR_OF_DAY)<6 || calendar_end.get(Calendar.HOUR_OF_DAY)>=18)){
				calendar_start.set(Calendar.HOUR_OF_DAY, 18);
				yhsc++;
				double se =  calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis();
				yhtime += (se/(3600*1000));
				calendar_start.setTime(t.getStartTime());
			}else if((calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis())/(3600*1000) >= 12){
				yhsc++;
				yhtime += 12;
			}	
			}
		oaCruisedata.setYhsc(yhsc);
		oaCruisedata.setYhtime(yhtime);*/
		return  oaCruisedata;
	}
	
	public List<OaCruisedata> findList(OaCruisedata oaCruisedata) {
		return super.findList(oaCruisedata);
	}
	
	public Page<OaCruisedata> findPage(Page<OaCruisedata> page, OaCruisedata oaCruisedata) {
		oaCruisedata.getSqlMap().put("dsf", dataScopeFilter(oaCruisedata.getCurrentUser(), "c", "u"));
		return super.findPage(page, oaCruisedata);
	}
	
	@Transactional(readOnly = false)
	public void save(OaCruisedata oaCruisedata) {
		String qtry = oaCruisedata.getQtry();
		String cruisePeople = oaCruisedata.getCruisePeople();
		int cruisePeopleNumber = 0;
		int qtryNumber = 0;
		if(StringUtils.isNotEmpty(cruisePeople)){
			cruisePeopleNumber = StringUtils.split(cruisePeople,",").length;
		}else{
			cruisePeople = "";
		}
		if(StringUtils.isNotEmpty(qtry) ){
			qtryNumber = StringUtils.split(qtry,", 、，.").length;
			cruisePeople = cruisePeople + "," + qtry;
		}
		
		oaCruisedata.setCruisePeople(cruisePeople);
		oaCruisedata.setQtrycs(qtryNumber);
		int cs = oaCruisedata.getCdzfrycs();
		if(cs == 0){
			oaCruisedata.setCdzfrycs(cruisePeopleNumber);
		}
		
		super.save(oaCruisedata);
		for (OaCruisetime oaCruisetime : oaCruisedata.getOaCruisetimeList()){
			if (oaCruisetime.getId() == null){
				continue;
			}
			if (OaCruisetime.DEL_FLAG_NORMAL.equals(oaCruisetime.getDelFlag())){
				if (StringUtils.isBlank(oaCruisetime.getId())){
					oaCruisetime.setOaCruisedata(oaCruisedata);
					oaCruisetime.preInsert();
					oaCruisetimeDao.insert(oaCruisetime);
				}else{
					oaCruisetime.preUpdate();
					oaCruisetimeDao.update(oaCruisetime);
				}
			}else{
				oaCruisetimeDao.delete(oaCruisetime);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(OaCruisedata oaCruisedata) {
		super.delete(oaCruisedata);
		oaCruisetimeDao.delete(new OaCruisetime(oaCruisedata));
	}

	public List<OaCruiseStat> getStatics(OaCruiseStat oaCruiseStat) {
		return oaCruisedataDao.getStatics(oaCruiseStat);
	}
	
	public List<OaCruisedata> getCruisedataByDate(OaCruisedata oaCruisedata,Date date, Integer i) {
		oaCruisedata.setCreateDate(date);
		return oaCruisedataDao.getCruisedataByDate(oaCruisedata);
	}
	public List<OaCruisedata> getCruisedataByDate(OaCruisedata oaCruisedata,Date date) {
		oaCruisedata.setCreateDate(date);
		oaCruisedata.setCreateBy(UserUtils.getUser());
		return oaCruisedataDao.getCruisedataByDate(oaCruisedata);
	}
	public List<Map<String,Object>> getCruiseTimeByDataId(String dataId) {
		return oaCruisedataDao.getCruiseTimeByDataId(dataId);
	}
	public List<OaExportDaily> getReport(OaReport oaReport) {
		String type = oaReport.getReportType();
		OaCruisedata oaCruisedata = new OaCruisedata();
		List<OaExportDaily> exportDaily = new ArrayList<OaExportDaily>();
		List<OaCruisedata> dataList = new ArrayList<OaCruisedata>();
		if(StringUtils.equals("1", type) || StringUtils.equals("4", type) || StringUtils.equals("6", type)){
			Date date = oaReport.getCreateDate();
			dataList = this.getCruisedataByDate1(oaCruisedata, date,1);
		}
		int i = 1;
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		for(OaCruisedata data:dataList){
			String xhrq = "";
			String xhsj = "";
			boolean first = true;
			List<Map<String,Object>> cruisetime = getCruiseTimeByDataId(data.getId());
			for(Map<String,Object> tm : cruisetime){
				if(!first){
					if(xhrq.equals(sdf1.format(tm.get("start_time")))){
						xhsj = xhsj + "\r\n" + sdf.format(tm.get("start_time")).toString() + "-" + sdf.format(tm.get("end_time")).toString();
					}
					else{
						xhsj = xhsj + "\r\n" + sdf1.format(tm.get("start_time")) + "\r\n" + sdf.format(tm.get("start_time")).toString() + "-" + sdf.format(tm.get("end_time")).toString();
						xhrq = sdf1.format(tm.get("start_time"));
					}
				}else{
					xhrq = sdf1.format(tm.get("start_time"));
					xhsj = xhrq + "\r\n" + sdf.format(tm.get("start_time")).toString() + "-" + sdf.format(tm.get("end_time")).toString();
					first = false;
				}
				
			}
			OaExportDaily daily = new OaExportDaily();
			daily.setNumber(i++);
			daily.setXhsj(xhsj);
			daily.setHxt(data.getCreateBy().getOffice().getName());
			daily.setZfry(data.getCruisePeople());
			daily.setFxjjwt(data.getSolveProblem());
			daily.setBeizhu(data.getRemarks());
			daily.setXhsy(data.getCruiseArea());
			if(data.getCdzfrycs()!=null){
				daily.setZfrysl(data.getCdzfrycs());
			}else{
				daily.setZfrysl(0);
			}
			
			daily.setQsk(data.getQsk());
			daily.setWzsl(data.getFxwz());
			daily.setDataId(data.getId());
			exportDaily.add(daily);
		}
		return exportDaily;
		// TODO Auto-generated method stub
		
	}

	private List<OaCruisedata> getCruisedataByDate1(OaCruisedata oaCruisedata, Date date, int i) {
		oaCruisedata.setCreateDate(date);
		return oaCruisedataDao.getCruisedataByDate1(oaCruisedata);
	}

	public List<OaCruiseStat> getStaticsById(OaCruiseStat stat) {
		List<OaCruiseStat> statList =  oaCruisedataDao.getStaticsById(stat);
		for(OaCruiseStat st : statList){
			OaCruisedata data = this.get(st.getCruiseDataId());
			data = this.yehangCount(data);
			st.setYehangcs(data.getYhsc());
			st.setYehangTime(data.getYhtime());
		}
		return statList;
	}

	public List<OaCruiseStat> getStaticsMonthly(OaCruiseStat oaCruiseStat){ 
		oaCruiseStat.getSqlMap().put("dsf", dataScopeFilter(oaCruiseStat.getCurrentUser(), "c", "u"));
		return oaCruisedataDao.getStaticsMonthly(oaCruiseStat);
	}

	@Transactional(readOnly = false)
	public void save(OaCruisedataArea cArea) {
		oaCruisedataDao.saveArea(cArea);
		
	}

	public File generateDailyReportByFreeMarker(OaReport oaReport) throws IOException {
		List<OaExportDaily> dailyData = this.getReport(oaReport);
		for(OaExportDaily daily : dailyData){
			OaCruiseStat stat = new OaCruiseStat();
			stat.setCruiseDataId(daily.getDataId());
			List<OaCruiseStat> dataList = this.getStaticsById(stat);
			if(dataList != null && dataList.size()>0){
				stat = dataList.get(0);
    			daily.setXhsc(stat.getTotalTime());
	    		daily.setXhlc(stat.getTotalTime()*12);
	    		daily.setXhcs(stat.getXhcs());
			}
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int i = 1;
		double xhlc = 0;
		int xhcs = 0;
		double xh_total_time = 0;
		int xhwz = 0;
		for(OaExportDaily cruise : dailyData){
			   HashMap<String,Object> keyMap = new HashMap<String,Object>();
			   keyMap.put("number",i++);
			   keyMap.put("hxt", cruise.getHxt());
			   keyMap.put("cruisePeople",cruise.getZfry());
			   keyMap.put("cruiseTime", cruise.getXhsj());
			   keyMap.put("cruiseTotalTime",cruise.getXhsc());
			   keyMap.put("cruiseArea", cruise.getXhsy());
			   keyMap.put("solveProblem",StringEscapeUtils.unescapeHtml4(cruise.getFxjjwt()));
			   keyMap.put("remark", cruise.getBeizhu());
			   xhlc += cruise.getXhlc();
			   xhcs += cruise.getXhcs();
			   xh_total_time += cruise.getXhsc();
			   xhwz += cruise.getWzsl();
			   list.add(keyMap);
		}
		Map<String,Object> key = new HashMap<String,Object>();
		key.put("sequence", list);
		OaDigitalcruise oaDigitalcruise = new OaDigitalcruise();
        oaDigitalcruise.setBeginHappenDate(oaReport.getCreateDate());
        List<OaDigitalcruise> digitalCruiseList = oaDigitalcruiseService.findList(oaDigitalcruise);
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        i = 1;
        int dzxhcs = 0;
        int dzxhwz = 0;
        for(OaDigitalcruise digital : digitalCruiseList){
			  HashMap<String,Object> keyMap = new HashMap<String,Object>();
			  keyMap.put("number", i++);
			  keyMap.put("depName", digital.getCreateBy().getOffice().getName());
			  keyMap.put("cruisePeople",StringEscapeUtils.unescapeHtml4(digital.getCruisePeople()));
			  keyMap.put("cruiseTime", StringEscapeUtils.unescapeHtml4(digital.getCruiseTime()));
			  keyMap.put("cruiseArea", digital.getCruiseArea());
			  keyMap.put("solveProblem", StringEscapeUtils.unescapeHtml4(digital.getSolveProblem()));
			  keyMap.put("remark", StringEscapeUtils.unescapeHtml4(digital.getRemarks()));
			  dzxhcs += digital.getCruiseTimes();
			  dzxhwz += digital.getIllegalCount();
			  list1.add(keyMap);
        }
          key.put("digitallist", list1);
          
          //设置渡口数据
          PortData portData = new PortData();
          portData.setBeginHappenDate(oaReport.getCreateDate());
          portData.setEndHappenDate(oaReport.getCreateDate());
          PortData dataList = oaDigitalcruiseService.getPortStatics(portData);
          key.put("dzxhcs", dzxhcs);
          key.put("dzxhwz", dzxhwz);
          key.put("kdyc", dataList.getDkyc());
          key.put("yscc", dataList.getYscc());
          key.put("ysrc", dataList.getYsrc());
          key.put("xhlc", xhlc);
          key.put("xhcs", xhcs);
          key.put("xh_total_time", xh_total_time);
          key.put("xhwz", xhwz);
          //设置日期，期数
          Date today = new Date();
          Calendar calendar = Calendar.getInstance();
  		  calendar.setTime(oaReport.getCreateDate());
  		  
          key.put("Myear", String.valueOf(calendar.get(Calendar.YEAR)));

          key.put("Mnum", String.valueOf(calendar.get(Calendar.DAY_OF_YEAR)));

          key.put("Mnum", String.valueOf(calendar.get(Calendar.DAY_OF_YEAR)-1));

          SimpleDateFormat sdf = new SimpleDateFormat("M月d日1630时");
          String dayOfToday = sdf.format(calendar.getTime());
          calendar.add(Calendar.DAY_OF_MONTH, -1);
          String dayOfYesterday = sdf.format(calendar.getTime());
          String details = dayOfYesterday +"至"+ dayOfToday;
          key.put("MdayDetails", details);
          //设置vts数据
          VtsWorkData vts = new VtsWorkData();
          vts.setBeginCreateDate(oaReport.getCreateDate());
          vts.setEndCreateDate(oaReport.getCreateDate());
          List<VtsWorkData> vtsList =vtsWorkDataService.findList(vts);
          
          if(vtsList!=null && vtsList.size()>0){
        	  vts = vtsWorkDataService.getVtsSum(vtsList);
          }
          if(vts != null){
        	  key.put("vtsInport", vts.getInportLimitShip());
        	  key.put("vtsTransit", vts.getTransitLimitShip());
        	  key.put("vtsImportant",vts.getImportantShip());
        	  key.put("vtsReport", vts.getPositionReport());
        	  key.put("vtsWork", StringEscapeUtils.unescapeHtml4(vts.getToDoList()));
          }else{
        	  key.put("vtsInport", "0");
        	  key.put("vtsTransit", "0");
        	  key.put("vtsImportant","0");
        	  key.put("vtsReport", "0");
        	  key.put("vtsWork", "值班动态");
          }
          Calendar ca = Calendar.getInstance();
          ca.setTime(oaReport.getCreateDate());
          //设置天气和水文情况
          SimpleDateFormat sdf1 = new SimpleDateFormat("气象台yyyy年M月d日16时发布的天气预报：");
          SimpleDateFormat sdf2 = new SimpleDateFormat("M月d日");
          String util = sdf1.format(ca.getTime());
          ca.add(Calendar.DAY_OF_MONTH, 1);
          String chaoxi = sdf2.format(ca.getTime());
          String chaoxiDetail = tideService.getDailyTide(ca.getTime());
          key.put("chaoxidetail", chaoxiDetail);
          key.put("qxt", util);
          key.put("chaoxi", chaoxi);
          //设置值班情况
          List<Map<String, Object>> scheduleList = scheduleDetailService.findSchedule(oaReport.getCreateDate());
          key.put("scheduleList", scheduleList);
          //设置沪通大桥情况
          List<MsaHutongProject> hutongList = msaHutongProjectService.findList(new MsaHutongProject());
          if(hutongList.get(0)!=null && hutongList.get(0).getContent()!=null){
        	  key.put("hutong", hutongList.get(0).getContent());
          }else{
        	  key.put("hutong","");
          }
          //设置通航数据
          List<MsaTonghang> tonghangList = msaTonghangService.findList(new MsaTonghang());
          if(tonghangList.get(0)!=null && tonghangList.get(0).getContent()!=null){
        	  key.put("tonghang", tonghangList.get(0).getContent());
          }else{
        	  key.put("tonghang","");
          }
		//FreeMarkers freemarker = new FreeMarkers();	   
		//freemarker.createWord(list);
		 // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
        File file = null;  
        InputStream fin = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = WordGenerator.createDoc(key, "wordModel4");  
            return file;
          
        } finally {  
            if(fin != null) fin.close();  

        } 
    
	}

	public List<Double> getCruiseTimeByDate(CruiseStatics cruiseStatics) {
		List<Map<String, Object>> ms = oaCruisedataDao.getCruiseTimeByDate(cruiseStatics.getBeginHappenDate(),cruiseStatics.getEndHappenDate());
		List<Double> lds = new ArrayList<Double>();
		for(int i=ms.size()-1;i>=0;i--){
			lds.add(Double.parseDouble(ms.get(i).get("totalTime").toString()));
		}
		return lds;
	}

	public List<Double> getCruiseYehangByDate(CruiseStatics cruiseStatics) {
		List<Map<String, Object>> ms = oaCruisedataDao.getCruiseYehangByDate(cruiseStatics.getBeginHappenDate(),cruiseStatics.getEndHappenDate());
		List<Double> lds = new ArrayList<Double>();
		for(int i=ms.size()-1;i>=0;i--){
			lds.add(Double.parseDouble(ms.get(i).get("totalTime").toString()));
		}
		return lds;
	}

	public List<String> getTodayStatics(String date) {
		List<Map<String, Object>> ms = oaCruisedataDao.getTodayStatics(date);
		List<String> lds = new ArrayList<String>();
		for(Map<String, Object> m : ms){
			lds.add(m.get("cname").toString());
		}
		return lds;
	}

	private OaCruisedata yehangCount(OaCruisedata oaCruisedata){
		Integer yhsc = 0;
		double yhtime = 0;
		Calendar calendar_start = Calendar.getInstance();
		Calendar calendar_end = Calendar.getInstance();

		for(OaCruisetime t : oaCruisedata.getOaCruisetimeList()){
			
			calendar_start.setTime(t.getStartTime());
			calendar_end.setTime(t.getEndTime());
			if(((calendar_start.get(Calendar.HOUR_OF_DAY)<6 || calendar_start.get(Calendar.HOUR_OF_DAY)>=18) && (calendar_end.get(Calendar.HOUR_OF_DAY)<6 || calendar_end.get(Calendar.HOUR_OF_DAY)>=18))){
				yhsc++;
				double se = calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis();
				yhtime += (se/(3600*1000));
			}
			else if((calendar_start.get(Calendar.HOUR_OF_DAY)<6 || calendar_start.get(Calendar.HOUR_OF_DAY)>=18)){
				calendar_end.set(Calendar.HOUR_OF_DAY, 6);
				yhsc++;
				double se =  calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis();
				yhtime += (se/(3600*1000));
				calendar_end.setTime(t.getEndTime());
			}else if((calendar_end.get(Calendar.HOUR_OF_DAY)<6 || calendar_end.get(Calendar.HOUR_OF_DAY)>=18)){
				calendar_start.set(Calendar.HOUR_OF_DAY, 18);
				yhsc++;
				double se =  calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis();
				yhtime += (se/(3600*1000));
				calendar_start.setTime(t.getStartTime());
			}else if((calendar_end.getTimeInMillis()-calendar_start.getTimeInMillis())/(3600*1000) >= 12){
				yhsc++;
				yhtime += 12;
			}	
			}
		oaCruisedata.setYhsc(yhsc);
		oaCruisedata.setYhtime(yhtime);
		return oaCruisedata;
	}

	public List<OaCruiseStat> getStaticsByDate(OaCruiseStat oaCruiseStat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if(cal.get(Calendar.HOUR_OF_DAY)<=12){
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.add(Calendar.DAY_OF_MONTH, -5);
		oaCruiseStat.setBeginHappenDate(cal.getTime());
		oaCruiseStat.setEndHappenDate(cal.getTime());
		return this.getStatics(oaCruiseStat);
	}

	public OaCruisedata getDataByOfficeIdAndDate(Office office,Date date) {
		OaCruisedata cruise = new OaCruisedata();
		cruise.setCreateDate(date);
		Office c = new Office();
		c.setParent(office);
		List<Office> officeList = officeService.findByParentId(c);
		List<String> officeIdList = new ArrayList<String>();
		for(Office o : officeList){
			officeIdList.add(o.getId());
		}
		cruise.setOfficeList(officeIdList);
		
		cruise = oaCruisedataDao.getDataByOfficeAndDate(cruise);
		return cruise;
	}
}