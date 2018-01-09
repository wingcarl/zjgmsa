/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinNewsitem;
import com.thinkgem.jeesite.modules.setting.dao.WeixinNewsitemDao;

/**
 * 图文消息内容列Service
 * @author tangchao
 * @version 2016-04-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinNewsitemService extends CrudService<WeixinNewsitemDao, WeixinNewsitem> {

	public WeixinNewsitem get(String id) {
		return super.get(id);
	}
	
	public List<WeixinNewsitem> findList(WeixinNewsitem weixinNewsitem) {
		return super.findList(weixinNewsitem);
	}
	
	public Page<WeixinNewsitem> findPage(Page<WeixinNewsitem> page, WeixinNewsitem weixinNewsitem) {
		return super.findPage(page, weixinNewsitem);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinNewsitem weixinNewsitem) {
		super.save(weixinNewsitem);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinNewsitem weixinNewsitem) {
		super.delete(weixinNewsitem);
	}
	
	public List<WeixinNewsitem> findListByTempleId(String templateid) {
		WeixinNewsitem weixinNewsitem=new WeixinNewsitem();
		weixinNewsitem.setTemplateid(templateid);
		return dao.findListByTempleId(weixinNewsitem);
	}
}