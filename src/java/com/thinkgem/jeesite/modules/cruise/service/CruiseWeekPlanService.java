/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.ZJGMSAUtils;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseWeekPlan;
import com.thinkgem.jeesite.modules.cruise.dao.CruiseWeekPlanDao;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseWeekPlanDetails;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisearea;
import com.thinkgem.jeesite.modules.cruise.dao.CruiseWeekPlanDetailsDao;

/**
 * 对周巡航计划的增删改查Service
 * @author 王浩宇
 * @version 2017-04-01
 */
@Service
@Transactional(readOnly = true)
public class CruiseWeekPlanService extends CrudService<CruiseWeekPlanDao, CruiseWeekPlan> {

	@Autowired
	private CruiseWeekPlanDetailsDao cruiseWeekPlanDetailsDao;
	@Autowired
	private CruiseWeekPlanDao cruiseWeekPlanDao;
	public CruiseWeekPlan get(String id) {
		CruiseWeekPlan cruiseWeekPlan = super.get(id);
		try{
			cruiseWeekPlan.setCruiseWeekPlanDetailsList(cruiseWeekPlanDetailsDao.findList(new CruiseWeekPlanDetails(cruiseWeekPlan)));
		}catch(Exception e){
			
		}
		return cruiseWeekPlan;
	}
	
	public List<CruiseWeekPlan> findList(CruiseWeekPlan cruiseWeekPlan) {

		return super.findList(cruiseWeekPlan);
	}
	public List<CruiseWeekPlan> findListFetch(CruiseWeekPlan cruiseWeekPlan) {
		List<CruiseWeekPlan> cwpList = super.findList(cruiseWeekPlan);
		for(CruiseWeekPlan cwp : cwpList){
			CruiseWeekPlanDetails details = new CruiseWeekPlanDetails();
			details.setWeeklyPlanId(cwp);
			List<CruiseWeekPlanDetails> dets = cruiseWeekPlanDetailsDao.findList(details);
			cwp.setCruiseWeekPlanDetailsList(dets);
		}
		return cwpList;
	}
	public Page<CruiseWeekPlan> findPage(Page<CruiseWeekPlan> page, CruiseWeekPlan cruiseWeekPlan) {
		cruiseWeekPlan.getSqlMap().put("dsf", dataScopeFilter(cruiseWeekPlan.getCurrentUser(), "c", "u"));
		return super.findPage(page, cruiseWeekPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(CruiseWeekPlan cruiseWeekPlan) {
		super.save(cruiseWeekPlan);
		for (CruiseWeekPlanDetails cruiseWeekPlanDetails : cruiseWeekPlan.getCruiseWeekPlanDetailsList()){
			if (cruiseWeekPlanDetails.getId() == null){
				continue;
			}
			if (CruiseWeekPlanDetails.DEL_FLAG_NORMAL.equals(cruiseWeekPlanDetails.getDelFlag())){
				if (StringUtils.isBlank(cruiseWeekPlanDetails.getId())){
					cruiseWeekPlanDetails.setWeeklyPlanId(cruiseWeekPlan);
					cruiseWeekPlanDetails.preInsert();
					cruiseWeekPlanDetailsDao.insert(cruiseWeekPlanDetails);
				}else{
					cruiseWeekPlanDetails.preUpdate();
					cruiseWeekPlanDetailsDao.update(cruiseWeekPlanDetails);
				}
			}else{
				cruiseWeekPlanDetailsDao.delete(cruiseWeekPlanDetails);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CruiseWeekPlan cruiseWeekPlan) {
		super.delete(cruiseWeekPlan);
		cruiseWeekPlanDetailsDao.delete(new CruiseWeekPlanDetails(cruiseWeekPlan));
	}

	public JSONObject findCruiseWeekPlanListByOfficeId(CruiseWeekPlan cruiseWeekPlan) {
		List<CruiseWeekPlan> lists = cruiseWeekPlanDao.findCruiseWeekPlanListByOfficeId(cruiseWeekPlan);
		Collections.sort(lists,new Comparator<CruiseWeekPlan>(){

			@Override
			public int compare(CruiseWeekPlan arg0, CruiseWeekPlan arg1) {
				
				return arg1.getCreateDate().compareTo(arg0.getCreateDate());
			}
			
		});
		CruiseWeekPlan cwp = lists.get(0);
		JSONObject json = new JSONObject();
		json.put("grid", cwp.getCruiseGrid());
		json.put("time", cwp.getCruiseTime());
		json.put("content", cwp.getImportantContent());
		json.put("pos", cwp.getImportantPos());
		return json;
	}

	public Map<String,Object> findListByDate(Date date) {
		CruiseWeekPlanDetails cwpd = new CruiseWeekPlanDetails();
		cwpd.setArrangeDate(date);
		List<CruiseWeekPlanDetails> detailList =cruiseWeekPlanDetailsDao.findListByArrangeDate(cwpd);
		Map<String,Object> ms = new HashMap<String,Object>();
		for(CruiseWeekPlanDetails cw : detailList){
			String officeName = cw.getWeeklyPlanId().getOffice().getName();
			ms.put(officeName, cw.getCruiseCaptain());
		}
		return ms;
	}

	
	
}