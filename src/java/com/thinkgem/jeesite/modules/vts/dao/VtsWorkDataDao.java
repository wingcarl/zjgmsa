/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vts.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.vts.entity.VtsWorkData;

/**
 * VTS工作数据DAO接口
 * @author dylan wang
 * @version 2017-05-05
 */
@MyBatisDao
public interface VtsWorkDataDao extends CrudDao<VtsWorkData> {

	List<VtsWorkData> getSumData(VtsWorkData vtsWorkData);
	
}