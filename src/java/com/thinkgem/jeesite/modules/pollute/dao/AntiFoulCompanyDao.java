/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulCompany;

/**
 * 防污染配置DAO接口
 * @author Dylan Wang
 * @version 2017-12-21
 */
@MyBatisDao
public interface AntiFoulCompanyDao extends CrudDao<AntiFoulCompany> {
	
}