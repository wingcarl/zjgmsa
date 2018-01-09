/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyRegist;
import com.thinkgem.jeesite.modules.penalty.dao.PenaltyRegistDao;

/**
 * 处罚初始信息收集Service
 * @author dylan wang
 * @version 2017-05-08
 */
@Service
@Transactional(readOnly = true)
public class PenaltyRegistService extends CrudService<PenaltyRegistDao, PenaltyRegist> {

	public PenaltyRegist get(String id) {
		return super.get(id);
	}
	
	public List<PenaltyRegist> findList(PenaltyRegist penaltyRegist) {
		return super.findList(penaltyRegist);
	}
	
	public Page<PenaltyRegist> findPage(Page<PenaltyRegist> page, PenaltyRegist penaltyRegist) {
		return super.findPage(page, penaltyRegist);
	}
	
	@Transactional(readOnly = false)
	public void save(PenaltyRegist penaltyRegist) {
		super.save(penaltyRegist);
	}
	
	@Transactional(readOnly = false)
	public void delete(PenaltyRegist penaltyRegist) {
		super.delete(penaltyRegist);
	}
	
}