/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.dao.port;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dict.entity.port.DictRegistryPort;

/**
 * 船籍港字典信息维护DAO接口
 * @author dylan wang
 * @version 2017-05-23
 */
@MyBatisDao
public interface DictRegistryPortDao extends CrudDao<DictRegistryPort> {
	
}