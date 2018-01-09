/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyInfo;
import com.thinkgem.jeesite.modules.penalty.dao.PenaltyInfoDao;

/**
 * 处罚数据登记表Service
 * @author dylan wang
 * @version 2017-05-08
 */
@Service
@Transactional(readOnly = true)
public class PenaltyInfoService extends CrudService<PenaltyInfoDao, PenaltyInfo> {

	public PenaltyInfo get(String id) {
		return super.get(id);
	}
	
	public List<PenaltyInfo> findList(PenaltyInfo penaltyInfo) {
		return super.findList(penaltyInfo);
	}
	
	public Page<PenaltyInfo> findPage(Page<PenaltyInfo> page, PenaltyInfo penaltyInfo) {
		return super.findPage(page, penaltyInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PenaltyInfo penaltyInfo) {
		super.save(penaltyInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PenaltyInfo penaltyInfo) {
		super.delete(penaltyInfo);
	}
	
}