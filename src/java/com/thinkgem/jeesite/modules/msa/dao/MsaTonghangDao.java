/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.msa.entity.MsaTonghang;

/**
 * 通航环境表DAO接口
 * @author Dylan Wang
 * @version 2018-01-12
 */
@MyBatisDao
public interface MsaTonghangDao extends CrudDao<MsaTonghang> {
	
}