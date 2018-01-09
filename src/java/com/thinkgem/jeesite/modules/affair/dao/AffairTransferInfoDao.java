/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;

/**
 * 水水中转货物核销登记DAO接口
 * @author Dylan Wang
 * @version 2017-07-02
 */
@MyBatisDao
public interface AffairTransferInfoDao extends CrudDao<AffairTransferInfo> {
	
}