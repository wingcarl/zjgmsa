/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestStat;

/**
 * 隐患排查DAO接口
 * @author Dylan Wang
 * @version 2017-08-31
 */
@MyBatisDao
public interface DangerInvestDao extends CrudDao<DangerInvest> {

	void confirm(DangerInvest d);

	List<DangerInvestStat> getSumData(DangerInvest dangerInvest);
	
}