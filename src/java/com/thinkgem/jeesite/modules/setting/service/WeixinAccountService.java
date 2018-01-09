/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinAccount;
import com.thinkgem.jeesite.modules.setting.dao.WeixinAccountDao;

/**
 * 微信公众号设置Service
 * @author tangchao
 * @version 2016-04-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinAccountService extends CrudService<WeixinAccountDao, WeixinAccount> {

	public WeixinAccount get(String id) {
		return super.get(id);
	}
	
	public List<WeixinAccount> findList(WeixinAccount weixinAccount) {
		return super.findList(weixinAccount);
	}
	
	public Page<WeixinAccount> findPage(Page<WeixinAccount> page, WeixinAccount weixinAccount) {
		return super.findPage(page, weixinAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinAccount weixinAccount) {
		super.save(weixinAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinAccount weixinAccount) {
		super.delete(weixinAccount);
	}
	
	public WeixinAccount findByToUsername(String weixinAccountid) {
		return dao.findByToUsername(weixinAccountid);
	}
}