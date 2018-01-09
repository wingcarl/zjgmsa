/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.flow.entity.FlowMeasureScale;

/**
 * 流量观测数据尺度DAO接口
 * @author Dylan Wang
 * @version 2017-10-24
 */
@MyBatisDao
public interface FlowMeasureScaleDao extends CrudDao<FlowMeasureScale> {

	List<FlowMeasureScale> findListBySection(FlowMeasureScale flowMeasureScale);
	
}