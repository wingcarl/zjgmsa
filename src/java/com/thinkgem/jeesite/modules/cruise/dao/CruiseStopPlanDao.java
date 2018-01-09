/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStopPlan;

/**
 * 停航检修计划DAO接口
 * @author Dylan Wang
 * @version 2017-06-26
 */
@MyBatisDao
public interface CruiseStopPlanDao extends CrudDao<CruiseStopPlan> {

	List<CruiseStopPlan> findByDate(CruiseStopPlan cruiseStopPlan);
	
}