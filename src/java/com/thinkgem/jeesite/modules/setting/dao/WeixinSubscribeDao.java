/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.setting.entity.WeixinSubscribe;

/**
 * 欢迎语DAO接口
 * @author tangchao
 * @version 2016-04-03
 */
@MyBatisDao
public interface WeixinSubscribeDao extends CrudDao<WeixinSubscribe> {
	
	public List<WeixinSubscribe> findListByAccountId(WeixinSubscribe weixinSubscribe);
	
}