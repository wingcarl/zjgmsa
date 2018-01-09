/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinReceivetext;
import com.thinkgem.jeesite.modules.setting.dao.WeixinReceivetextDao;

/**
 * 微信接受消息Service
 * @author tangchao
 * @version 2016-04-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinReceivetextService extends CrudService<WeixinReceivetextDao, WeixinReceivetext> {

	public WeixinReceivetext get(String id) {
		return super.get(id);
	}
	
	public List<WeixinReceivetext> findList(WeixinReceivetext weixinReceivetext) {
		return super.findList(weixinReceivetext);
	}
	
	public Page<WeixinReceivetext> findPage(Page<WeixinReceivetext> page, WeixinReceivetext weixinReceivetext) {
		return super.findPage(page, weixinReceivetext);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinReceivetext weixinReceivetext) {
		super.save(weixinReceivetext);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinReceivetext weixinReceivetext) {
		super.delete(weixinReceivetext);
	}
	
}