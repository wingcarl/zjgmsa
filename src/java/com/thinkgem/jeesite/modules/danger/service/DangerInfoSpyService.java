/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfoSpy;
import com.thinkgem.jeesite.modules.danger.dao.DangerInfoSpyDao;

/**
 * danger_spyService
 * @author dylan wang
 * @version 2017-09-04
 */
@Service
@Transactional(readOnly = true)
public class DangerInfoSpyService extends CrudService<DangerInfoSpyDao, DangerInfoSpy> {

	public DangerInfoSpy get(String id) {
		return super.get(id);
	}
	
	public List<DangerInfoSpy> findList(DangerInfoSpy dangerInfoSpy) {
		return super.findList(dangerInfoSpy);
	}
	
	public Page<DangerInfoSpy> findPage(Page<DangerInfoSpy> page, DangerInfoSpy dangerInfoSpy) {
		return super.findPage(page, dangerInfoSpy);
	}
	
	@Transactional(readOnly = false)
	public void save(DangerInfoSpy dangerInfoSpy) {
		super.save(dangerInfoSpy);
	}
	
	@Transactional(readOnly = false)
	public void delete(DangerInfoSpy dangerInfoSpy) {
		super.delete(dangerInfoSpy);
	}
	
}