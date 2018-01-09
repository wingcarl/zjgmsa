/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.dao.city;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dict.entity.city.DictCityPos;

/**
 * 城市经纬度字典管理DAO接口
 * @author dylan wang
 * @version 2017-05-23
 */
@MyBatisDao
public interface DictCityPosDao extends CrudDao<DictCityPos> {
	
}