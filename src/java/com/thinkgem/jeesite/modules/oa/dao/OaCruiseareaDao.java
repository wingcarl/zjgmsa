/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisearea;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;

/**
 * 巡航水域增删改查DAO接口
 * @author 王浩宇
 * @version 2017-03-16
 */
@MyBatisDao
public interface OaCruiseareaDao extends CrudDao<OaCruisearea> {

	List<OaCruisearea> findCruiseareaListByOfficeId(OaCruisearea oaCruisearea);

	List<Map<String, Object>> getCruiseAreaCount(OaCruisedataArea area);
	
}