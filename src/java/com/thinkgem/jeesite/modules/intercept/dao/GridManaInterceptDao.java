/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.intercept.dao;

import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.intercept.entity.GridManaIntercept;

/**
 * 拦截船舶情况DAO接口
 * @author Dylan Wang
 * @version 2017-06-16
 */
@MyBatisDao
public interface GridManaInterceptDao extends CrudDao<GridManaIntercept> {

	Map<String, Long> getCount(GridManaIntercept gridManaIntercept);
	
}