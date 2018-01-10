/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfo;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfoSpy;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestSpy;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestStat;

/**
 * danger_spyDAO接口
 * @author dylan wang
 * @version 2017-09-04
 */
@MyBatisDao
public interface DangerInfoSpyDao extends CrudDao<DangerInfoSpy> {

	//List<DangerInfoStat> findSpyList(DangerInfo dangerInfo);
	
}