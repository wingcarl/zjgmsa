/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyBasicInfo;

/**
 * 处罚基础信息配置表DAO接口
 * @author dylan wang
 * @version 2017-05-09
 */
@MyBatisDao
public interface PenaltyBasicInfoDao extends CrudDao<PenaltyBasicInfo> {
	
}