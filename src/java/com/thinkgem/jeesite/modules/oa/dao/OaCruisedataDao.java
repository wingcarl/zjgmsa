/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStatics;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;

/**
 * 测试表DAO接口
 * @author 王浩宇
 * @version 2017-02-23
 */
@MyBatisDao
public interface OaCruisedataDao extends CrudDao<OaCruisedata> {

	List<OaCruiseStat> getStatics(OaCruiseStat oaCruiseStat);

	List<OaCruisedata> getCruisedataByDate(OaCruisedata oaCruisedata);

	List<Map<String,Object>> getCruiseTimeByDataId(String dataId);

	List<OaCruiseStat> getStaticsById(OaCruiseStat stat);

	List<OaCruiseStat> getStaticsMonthly(OaCruiseStat oaCruiseStat);

	List<OaCruisedata> getCruisedataByDate1(OaCruisedata oaCruisedata);

	void saveArea(OaCruisedataArea cArea);

	List<Map<String, Object>> getCruiseTimeByDate(Date beginHappenDate, Date endHappenDate);

	List<Map<String, Object>> getCruiseYehangByDate(Date beginHappenDate, Date endHappenDate);

	List<Map<String, Object>> getTodayStatics(String date);

	OaCruisedata getDataByOfficeAndDate(OaCruisedata cruise);
	
}