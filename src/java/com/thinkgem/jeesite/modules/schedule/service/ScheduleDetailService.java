/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.schedule.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;

import com.thinkgem.jeesite.common.utils.ZJGMSAUtils;

import com.thinkgem.jeesite.modules.schedule.entity.ScheduleDetail;
import com.thinkgem.jeesite.modules.schedule.entity.ScheduleUser;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.schedule.dao.ScheduleDetailDao;

/**
 * 生成全局排班表Service
 * @author dylan wang
 * @version 2017-05-21
 */
@Service
@Transactional(readOnly = true)
public class ScheduleDetailService extends CrudService<ScheduleDetailDao, ScheduleDetail> {
	@Autowired
	ScheduleDetailDao scheduleDetailDao;
	public ScheduleDetail get(String id) {
		return super.get(id);
	}
	
	public List<ScheduleDetail> findList(ScheduleDetail scheduleDetail) {

		scheduleDetail.getSqlMap().put("dsf", dataScopeFilter(scheduleDetail.getCurrentUser(), "c", "u"));

		return super.findList(scheduleDetail);
	}
	public List<Map<String, Object>> findSchedule(Date date){
		 ScheduleDetail sd = new ScheduleDetail();
         sd.setScheduleDate(date);
         List<ScheduleDetail> sdList = this.findList(sd);
         List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
         ZJGMSAUtils util = new ZJGMSAUtils();
         List<String> officenameList = util.getJiCeng();
         for(ScheduleDetail d : sdList){
        	  String officename = d.getOffice().getName();
        	 if(officenameList.contains(officename)){
        		 HashMap<String,Object> keyMap = new HashMap<String,Object>();
	   			  keyMap.put("officename", d.getOffice().getName());
	   			  keyMap.put("username", d.getUserList4());
	   			  if("1".equals(d.getConfirm())){
	   				  keyMap.put("confirm", "");
	   			  }else{
	   				  keyMap.put("confirm", "（未确认）");
	   			  }				 
	   			  list1.add(keyMap);
        	 }
			  
         }
		return list1;
		
	}

		return super.findList(scheduleDetail);
	}
	
	public Page<ScheduleDetail> findPage(Page<ScheduleDetail> page, ScheduleDetail scheduleDetail) {
		scheduleDetail.getSqlMap().put("dsf", dataScopeFilter(scheduleDetail.getCurrentUser(), "c", "u"));

		return super.findPage(page, scheduleDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(ScheduleDetail scheduleDetail) {
		
		super.save(scheduleDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScheduleDetail scheduleDetail) {
		super.delete(scheduleDetail);
	}

	public List<ScheduleDetail> getByDate(String date) {
		return scheduleDetailDao.getByDate(date);
		
		
	}

	@Transactional(readOnly = false)
	public boolean confirm(ScheduleDetail scheduleDetail) {
		try{
			scheduleDetailDao.confirm(scheduleDetail);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	public List<List<String>> findListByUser(ScheduleDetail schedule) {
		List<ScheduleDetail> lists = scheduleDetailDao.findListByUsers(schedule);
		String username = UserUtils.getUser().getName().replace(" ", "");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		Map<String,String> calendar = new HashMap<String,String>();
		for(ScheduleDetail detail : lists){
			
			String d = sdf.format(detail.getScheduleDate());
			if(detail.getUserList1().replace(" ", "").contains(username) || detail.getUserList2().replace(" ", "").contains(username) ){
				calendar.put(d,"白班");
				
			}
			if(detail.getUserList3().replace(" ","").contains(username) || detail.getUserList4().replace(" ", "").contains(username) ){
				if("白班".equals(calendar.get(d))){
					calendar.put(d,"白班,夜班");
				}else {
					calendar.put(d,"夜班");
				}
			}

			
		}
		
		List<String> arr = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		String end = sdf.format(DateUtils.addMonths(cal.getTime(), 1));
		while(!end.equals(sdf.format(cal.getTime()))){
			arr.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1); //日期加1天  
		}
		List<List<String>> list = new ArrayList<List<String>>();
		for(int i=0;i<arr.size();i++){
			if(calendar.get(arr.get(i))!=null){
				List<String> li = new ArrayList<String>();
				li.add(arr.get(i));
				li.add(calendar.get(arr.get(i)));
				list.add(li);
			}else{
				List<String> li = new ArrayList<String>();
				li.add(arr.get(i));
				li.add("");
				list.add(li);
			}
		}
		return list;
	}
	
}