/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.setting.entity.WeixinTexttemplate;
import com.thinkgem.jeesite.modules.setting.dao.WeixinTexttemplateDao;

/**
 * 微信文本消息Service
 * @author lanbiao
 * @version 2016-04-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinTexttemplateService extends CrudService<WeixinTexttemplateDao, WeixinTexttemplate> {

	public WeixinTexttemplate get(String id) {
		return super.get(id);
	}
	
	public List<WeixinTexttemplate> findList(WeixinTexttemplate weixinTexttemplate) {
		return super.findList(weixinTexttemplate);
	}
	
	public Page<WeixinTexttemplate> findPage(Page<WeixinTexttemplate> page, WeixinTexttemplate weixinTexttemplate) {
		return super.findPage(page, weixinTexttemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinTexttemplate weixinTexttemplate) {
		super.save(weixinTexttemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinTexttemplate weixinTexttemplate) {
		super.delete(weixinTexttemplate);
	}
	
	public WeixinTexttemplate getTextTemplateByName(String accountid,String templatename) {
		WeixinTexttemplate weixinTexttemplate=new WeixinTexttemplate();
		weixinTexttemplate.setAccountid(accountid);
		weixinTexttemplate.setTemplatename(templatename);
		return dao.getTextTemplate(weixinTexttemplate);
	}
	
	public WeixinTexttemplate getTextTemplateById(String accountid,String id) {
		WeixinTexttemplate weixinTexttemplate=new WeixinTexttemplate();
		weixinTexttemplate.setAccountid(accountid);
		weixinTexttemplate.setId(id);
		return dao.getTextTemplate(weixinTexttemplate);
	}
	
}