/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseWeekPlanDetails;

/**
 * 对周巡航计划的增删改查DAO接口
 * @author 王浩宇
 * @version 2017-04-01
 */
@MyBatisDao
public interface CruiseWeekPlanDetailsDao extends CrudDao<CruiseWeekPlanDetails> {

	List<CruiseWeekPlanDetails> findListByArrangeDate(CruiseWeekPlanDetails cwpd);
	
}