/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;

/**
 * 每日在港船舶动态DAO接口
 * @author 王浩宇
 * @version 2017-03-09
 */
@MyBatisDao
public interface OaInportShipDao extends CrudDao<OaInportShip> {

	List<String> findAllShipNames(OaInportShip oaInportShip);

	List<OaInportShip> findByShipNames(OaInportShip oaInportShip);

	List<OaInportShip> findOneByCurrentUserOffice(OaInportShip oaInportShip);

	List<OaInportShip> findListByUserAndDate(OaInportShip ship);

	void deleteToday(OaInportShip oaInportShip);

	List<Map<String, Object>> getTodayStatics(String date);

	List<Map<String, Object>> getInportShipStatics();
	
}