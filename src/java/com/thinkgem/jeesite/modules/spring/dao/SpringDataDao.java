/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.spring.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.spring.entity.SpringData;

/**
 * 春运专项数据DAO接口
 * @author Dylan Wang
 * @version 2018-01-26
 */
@MyBatisDao
public interface SpringDataDao extends CrudDao<SpringData> {
	
}