/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.flow.entity.FlowMeasureType;

/**
 * 流量观测按船种分DAO接口
 * @author Dylan
 * @version 2017-12-15
 */
@MyBatisDao
public interface FlowMeasureTypeDao extends CrudDao<FlowMeasureType> {

	List<FlowMeasureType> findListBySection(FlowMeasureType flowMeasureType);
	
}