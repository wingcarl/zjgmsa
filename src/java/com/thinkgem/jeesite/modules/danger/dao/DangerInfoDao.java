/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfo;

/**
 * 隐患信息表DAO接口
 * @author Dylan Wang
 * @version 2018-01-08
 */
@MyBatisDao
public interface DangerInfoDao extends CrudDao<DangerInfo> {
	
}