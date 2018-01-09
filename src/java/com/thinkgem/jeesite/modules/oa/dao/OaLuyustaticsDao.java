/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaLuyustatics;

/**
 * 陆域巡查数据DAO接口
 * @author dylan wang
 * @version 2017-03-07
 */
@MyBatisDao
public interface OaLuyustaticsDao extends CrudDao<OaLuyustatics> {
	
}