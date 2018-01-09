/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.service;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStopPlan;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.cruise.dao.CruiseStopPlanDao;

/**
 * 停航检修计划Service
 * @author Dylan Wang
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class CruiseStopPlanService extends CrudService<CruiseStopPlanDao, CruiseStopPlan> {

	
	@Autowired
	CruiseStopPlanDao cruiseStopPlanDao;
	@Autowired
	OfficeService officeService;
	public CruiseStopPlan get(String id) {
		return super.get(id);
	}
	
	public List<CruiseStopPlan> findList(CruiseStopPlan cruiseStopPlan) {
		return super.findList(cruiseStopPlan);
	}
	
	public Page<CruiseStopPlan> findPage(Page<CruiseStopPlan> page, CruiseStopPlan cruiseStopPlan) {
		return super.findPage(page, cruiseStopPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(CruiseStopPlan cruiseStopPlan) {
		super.save(cruiseStopPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(CruiseStopPlan cruiseStopPlan) {
		super.delete(cruiseStopPlan);
	}

	public boolean isUnique(CruiseStopPlan cruiseStopPlan) {
		Date day = cruiseStopPlan.getStopDate();
		String officeId = cruiseStopPlan.getOffice().getId();
		Office parentOffice = officeService.getParent(new Office(officeId));
		List<String> jfAndBsq = new ArrayList<String>();
		List<String> dqAndNf = new ArrayList<String>();
		jfAndBsq.add("d6766740d95d479a9a282fe35b120b3a");
		jfAndBsq.add("72830d3bcc8e4a52afb1f90d2fc7d755");
		dqAndNf.add("dbf2de4f15b84cfa80e10c49a2712101");
		dqAndNf.add("fcde8a1044684e3791a3df602d255c1a");
		cruiseStopPlan.setBeginStopDate(DateUtils.setDays(day, 1));
		cruiseStopPlan.setEndStopDate(DateUtils.addMonths(cruiseStopPlan.getBeginStopDate(), 1));
		List<CruiseStopPlan> planList = cruiseStopPlanDao.findByDate(cruiseStopPlan);
		for(CruiseStopPlan plan : planList){
			if(parentOffice.getParent().getId().equals(plan.getParentOffice().getId()) 
					&& day.equals(plan.getStopDate())){//同一个部门两条艇不能同一天检修！
				return false;
			}else if(jfAndBsq.contains(parentOffice.getParent().getId()) && jfAndBsq.contains(plan.getParentOffice().getId()) 
					&& day.equals(plan.getStopDate())){//锦丰和保税区不能同一天检修
				 return false;
			}else if(dqAndNf.contains(parentOffice.getParent().getId()) && dqAndNf.contains(plan.getParentOffice().getId()) 
					&& day.equals(plan.getStopDate())){//南丰和大桥不能同一天检修
				 return false;
			}else if(officeId.equals(plan.getOffice().getId()) && cruiseStopPlan.getIsNewRecord())
				return false;
			
		}
		return true;
	}

	
	
}