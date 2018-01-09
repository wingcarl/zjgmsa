/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulShip;

/**
 * 防污染配置DAO接口
 * @author Dylan Wang
 * @version 2017-12-21
 */
@MyBatisDao
public interface AntiFoulShipDao extends CrudDao<AntiFoulShip> {

	List<AntiFoulShip> findAllList(AntiFoulShip antiFoulShip);
	
}