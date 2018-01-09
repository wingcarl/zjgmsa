/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestDetail;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestStat;

/**
 * 隐患详情DAO接口
 * @author Dylan Wang
 * @version 2017-08-31
 */
@MyBatisDao
public interface DangerInvestDetailDao extends CrudDao<DangerInvestDetail> {

	List<DangerInvestStat> findDetailList(DangerInvest dangerInvest);

	
	
}