/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinSubscribe;
import com.thinkgem.jeesite.modules.setting.dao.WeixinSubscribeDao;

/**
 * 欢迎语Service
 * @author tangchao
 * @version 2016-04-03
 */
@Service
@Transactional(readOnly = true)
public class WeixinSubscribeService extends CrudService<WeixinSubscribeDao, WeixinSubscribe> {

	public WeixinSubscribe get(String id) {
		return super.get(id);
	}
	
	public List<WeixinSubscribe> findList(WeixinSubscribe weixinSubscribe) {
		return super.findList(weixinSubscribe);
	}
	
	public Page<WeixinSubscribe> findPage(Page<WeixinSubscribe> page, WeixinSubscribe weixinSubscribe) {
		return super.findPage(page, weixinSubscribe);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinSubscribe weixinSubscribe) {
		super.save(weixinSubscribe);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinSubscribe weixinSubscribe) {
		super.delete(weixinSubscribe);
	}
	
	public List<WeixinSubscribe> findListByAccountId(String accountid) {
		WeixinSubscribe weixinSubscribe=new WeixinSubscribe();
		weixinSubscribe.setAccountid(accountid);
		return super.findList(weixinSubscribe);
	}
	
}