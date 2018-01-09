/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaLuyustatics;
import com.thinkgem.jeesite.modules.oa.dao.OaLuyustaticsDao;

/**
 * 陆域巡查数据Service
 * @author dylan wang
 * @version 2017-03-07
 */
@Service
@Transactional(readOnly = true)
public class OaLuyustaticsService extends CrudService<OaLuyustaticsDao, OaLuyustatics> {

	public OaLuyustatics get(String id) {
		return super.get(id);
	}
	
	public List<OaLuyustatics> findList(OaLuyustatics oaLuyustatics) {
		return super.findList(oaLuyustatics);
	}
	
	public Page<OaLuyustatics> findPage(Page<OaLuyustatics> page, OaLuyustatics oaLuyustatics) {
		return super.findPage(page, oaLuyustatics);
	}
	
	@Transactional(readOnly = false)
	public void save(OaLuyustatics oaLuyustatics) {
		super.save(oaLuyustatics);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaLuyustatics oaLuyustatics) {
		super.delete(oaLuyustatics);
	}
	
}