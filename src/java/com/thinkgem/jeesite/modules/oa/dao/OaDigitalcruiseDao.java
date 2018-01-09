/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.PortData;

/**
 * 电子巡查相关表DAO接口
 * @author 王浩宇
 * @version 2017-03-09
 */
@MyBatisDao
public interface OaDigitalcruiseDao extends CrudDao<OaDigitalcruise> {

	List<OaDigitalcruise> getDigitalcruiseDataByDate(OaDigitalcruise oaDigitalcruise);

	List<OaDigitalcruise> getPortStatics(OaDigitalcruise oaDigitalcruise);

	List<Map<String, Object>> getTodayStatics(String date);
	
}