/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.danger.entity.DangerCompany;
import com.thinkgem.jeesite.modules.danger.dao.DangerCompanyDao;

/**
 * 隐患排查对象信息Service
 * @author Dylan Wang
 * @version 2018-01-08
 */
@Service
@Transactional(readOnly = true)
public class DangerCompanyService extends CrudService<DangerCompanyDao, DangerCompany> {

	public DangerCompany get(String id) {
		return super.get(id);
	}
	
	public List<DangerCompany> findList(DangerCompany dangerCompany) {
		dangerCompany.getSqlMap().put("dsf", dataScopeFilter(dangerCompany.getCurrentUser(), "c", "u"));
		return super.findList(dangerCompany);
	}
	
	public Page<DangerCompany> findPage(Page<DangerCompany> page, DangerCompany dangerCompany) {
		dangerCompany.getSqlMap().put("dsf", dataScopeFilter(dangerCompany.getCurrentUser(), "c", "u"));

		return super.findPage(page, dangerCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(DangerCompany dangerCompany) {
		super.save(dangerCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(DangerCompany dangerCompany) {
		super.delete(dangerCompany);
	}
	
}