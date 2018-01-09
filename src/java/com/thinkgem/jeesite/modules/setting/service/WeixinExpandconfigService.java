/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinExpandconfig;
import com.thinkgem.jeesite.modules.setting.dao.WeixinExpandconfigDao;

/**
 * 扩展接口Service
 * @author tangchao
 * @version 2016-04-03
 */
@Service
@Transactional(readOnly = true)
public class WeixinExpandconfigService extends CrudService<WeixinExpandconfigDao, WeixinExpandconfig> {

	public WeixinExpandconfig get(String id) {
		return super.get(id);
	}
	
	public List<WeixinExpandconfig> findList(WeixinExpandconfig weixinExpandconfig) {
		return super.findList(weixinExpandconfig);
	}
	
	public Page<WeixinExpandconfig> findPage(Page<WeixinExpandconfig> page, WeixinExpandconfig weixinExpandconfig) {
		return super.findPage(page, weixinExpandconfig);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinExpandconfig weixinExpandconfig) {
		super.save(weixinExpandconfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinExpandconfig weixinExpandconfig) {
		super.delete(weixinExpandconfig);
	}
	
}