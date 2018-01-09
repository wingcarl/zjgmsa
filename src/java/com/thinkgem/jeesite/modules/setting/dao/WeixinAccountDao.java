/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.setting.entity.WeixinAccount;

/**
 * 微信公众号设置DAO接口
 * @author tangchao
 * @version 2016-04-02
 */
@MyBatisDao
public interface WeixinAccountDao extends CrudDao<WeixinAccount> {
	
	public WeixinAccount findByToUsername(String weixinAccountid);
	
}