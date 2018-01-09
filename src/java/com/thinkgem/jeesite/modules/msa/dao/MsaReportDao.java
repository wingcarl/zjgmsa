/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.msa.entity.MsaReport;

/**
 * 政务信息爬虫获取DAO接口
 * @author dylan wang
 * @version 2017-05-26
 */
@MyBatisDao
public interface MsaReportDao extends CrudDao<MsaReport> {

	List<MsaReport> findDeptStatics(MsaReport msaReport);

	void updateByTitle(String title, String type);
	
}