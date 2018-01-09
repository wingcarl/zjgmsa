/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.OaMulCruisedata;

/**
 * 巡航数据带字表DAO接口
 * @author dylan wang
 * @version 2017-02-26
 */
@MyBatisDao
public interface OaMulCruisedataDao extends CrudDao<OaMulCruisedata> {
	
}