/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinNewstemplate;
import com.thinkgem.jeesite.modules.setting.dao.WeixinNewstemplateDao;

/**
 * 图文消息Service
 * @author tangchao
 * @version 2016-04-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinNewstemplateService extends CrudService<WeixinNewstemplateDao, WeixinNewstemplate> {

	public WeixinNewstemplate get(String id) {
		return super.get(id);
	}
	
	public List<WeixinNewstemplate> findList(WeixinNewstemplate weixinNewstemplate) {
		return super.findList(weixinNewstemplate);
	}
	
	public Page<WeixinNewstemplate> findPage(Page<WeixinNewstemplate> page, WeixinNewstemplate weixinNewstemplate) {
		return super.findPage(page, weixinNewstemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinNewstemplate weixinNewstemplate) {
		super.save(weixinNewstemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinNewstemplate weixinNewstemplate) {
		super.delete(weixinNewstemplate);
	}
	
}