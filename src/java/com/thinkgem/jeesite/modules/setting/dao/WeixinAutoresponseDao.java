/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.setting.entity.WeixinAutoresponse;

/**
 * 关键字回复DAO接口
 * @author tangchao
 * @version 2016-04-02
 */
@MyBatisDao
public interface WeixinAutoresponseDao extends CrudDao<WeixinAutoresponse> {
	
}