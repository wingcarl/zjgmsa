/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferDetail;

/**
 * 二程船详细信息表DAO接口
 * @author Dylan Wang
 * @version 2017-07-04
 */
@MyBatisDao
public interface AffairTransferDetailDao extends CrudDao<AffairTransferDetail> {
	
}