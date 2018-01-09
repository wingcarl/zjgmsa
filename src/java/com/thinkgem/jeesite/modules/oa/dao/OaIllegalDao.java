/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;

/**
 * 违法行为增删改查DAO接口
 * @author dylanwang
 * @version 2017-02-23
 */
@MyBatisDao
public interface OaIllegalDao extends CrudDao<OaIllegal> {


	List<OaIllegal> findList1(OaIllegal oaIllegal);

	List<OaIllegal> findByOaCruisedata(OaIllegal oaIllegal);

	List<Map<String, Object>> getIllegalCount(OaIllegal oaIllegal);
	
}