/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestSpy;
import com.thinkgem.jeesite.modules.danger.dao.DangerInvestSpyDao;

/**
 * danger_spyService
 * @author dylan wang
 * @version 2017-09-04
 */
@Service
@Transactional(readOnly = true)
public class DangerInvestSpyService extends CrudService<DangerInvestSpyDao, DangerInvestSpy> {

	public DangerInvestSpy get(String id) {
		return super.get(id);
	}
	
	public List<DangerInvestSpy> findList(DangerInvestSpy dangerInvestSpy) {
		return super.findList(dangerInvestSpy);
	}
	
	public Page<DangerInvestSpy> findPage(Page<DangerInvestSpy> page, DangerInvestSpy dangerInvestSpy) {
		return super.findPage(page, dangerInvestSpy);
	}
	
	@Transactional(readOnly = false)
	public void save(DangerInvestSpy dangerInvestSpy) {
		super.save(dangerInvestSpy);
	}
	
	@Transactional(readOnly = false)
	public void delete(DangerInvestSpy dangerInvestSpy) {
		super.delete(dangerInvestSpy);
	}
	
}