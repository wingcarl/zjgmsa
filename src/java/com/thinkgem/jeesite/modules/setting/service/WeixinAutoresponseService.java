/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinAutoresponse;
import com.thinkgem.jeesite.modules.setting.dao.WeixinAutoresponseDao;

/**
 * 关键字回复Service
 * @author tangchao
 * @version 2016-04-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinAutoresponseService extends CrudService<WeixinAutoresponseDao, WeixinAutoresponse> {

	public WeixinAutoresponse get(String id) {
		return super.get(id);
	}
	
	public List<WeixinAutoresponse> findList(WeixinAutoresponse weixinAutoresponse) {
		return super.findList(weixinAutoresponse);
	}
	
	public Page<WeixinAutoresponse> findPage(Page<WeixinAutoresponse> page, WeixinAutoresponse weixinAutoresponse) {
		return super.findPage(page, weixinAutoresponse);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinAutoresponse weixinAutoresponse) {
		super.save(weixinAutoresponse);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinAutoresponse weixinAutoresponse) {
		super.delete(weixinAutoresponse);
	}
	
}