/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.setting.entity.WeixinTexttemplate;

/**
 * 微信文本消息DAO接口
 * @author lanbiao
 * @version 2016-04-02
 */
@MyBatisDao
public interface WeixinTexttemplateDao extends CrudDao<WeixinTexttemplate> {
	
	public WeixinTexttemplate getTextTemplate(WeixinTexttemplate weixinTexttemplate);
	
}