/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.ReportMonthly;

/**
 * 海事业务月度通报DAO接口
 * @author Dylan Wang
 * @version 2017-07-24
 */
@MyBatisDao
public interface ReportMonthlyDao extends CrudDao<ReportMonthly> {
	
}