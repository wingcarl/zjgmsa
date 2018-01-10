/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.danger.entity.DangerInfo;
import com.thinkgem.jeesite.modules.danger.dao.DangerInfoDao;

/**
 * 隐患信息表Service
 * @author Dylan Wang
 * @version 2018-01-08
 */
@Service
@Transactional(readOnly = true)
public class DangerInfoService extends CrudService<DangerInfoDao, DangerInfo> {

	public DangerInfo get(String id) {
		return super.get(id);
	}
	
	public List<DangerInfo> findList(DangerInfo dangerInfo) {
		return super.findList(dangerInfo);
	}
	
	public Page<DangerInfo> findPage(Page<DangerInfo> page, DangerInfo dangerInfo) {
		return super.findPage(page, dangerInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(DangerInfo dangerInfo) {
		super.save(dangerInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(DangerInfo dangerInfo) {
		super.delete(dangerInfo);
	}
	
}