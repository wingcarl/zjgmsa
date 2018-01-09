/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinMenuentity;
import com.thinkgem.jeesite.modules.setting.dao.WeixinMenuentityDao;

/**
 * 自定义菜单Service
 * @author tangchao
 * @version 2016-04-03
 */
@Service
@Transactional(readOnly = true)
public class WeixinMenuentityService extends CrudService<WeixinMenuentityDao, WeixinMenuentity> {

	public WeixinMenuentity get(String id) {
		return super.get(id);
	}
	
	public List<WeixinMenuentity> findList(WeixinMenuentity weixinMenuentity) {
		return super.findList(weixinMenuentity);
	}
	
	public Page<WeixinMenuentity> findPage(Page<WeixinMenuentity> page, WeixinMenuentity weixinMenuentity) {
		return super.findPage(page, weixinMenuentity);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinMenuentity weixinMenuentity) {
		super.save(weixinMenuentity);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinMenuentity weixinMenuentity) {
		super.delete(weixinMenuentity);
	}
	
	public WeixinMenuentity getMenuByKey(String key) {
		WeixinMenuentity paramMenuentity=new WeixinMenuentity();
		paramMenuentity.setMenukey(key);
		return dao.getMenuByKey(paramMenuentity);
	}
	
}