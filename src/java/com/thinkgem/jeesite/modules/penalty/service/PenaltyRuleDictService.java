/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyRuleDict;
import com.thinkgem.jeesite.modules.penalty.dao.PenaltyRuleDictDao;

/**
 * 违章字典Service
 * @author dylan wang
 * @version 2017-06-02
 */
@Service
@Transactional(readOnly = true)
public class PenaltyRuleDictService extends CrudService<PenaltyRuleDictDao, PenaltyRuleDict> {

	public PenaltyRuleDict get(String id) {
		return super.get(id);
	}
	
	public List<PenaltyRuleDict> findList(PenaltyRuleDict penaltyRuleDict) {
		return super.findList(penaltyRuleDict);
	}
	
	public Page<PenaltyRuleDict> findPage(Page<PenaltyRuleDict> page, PenaltyRuleDict penaltyRuleDict) {
		return super.findPage(page, penaltyRuleDict);
	}
	
	@Transactional(readOnly = false)
	public void save(PenaltyRuleDict penaltyRuleDict) {
		super.save(penaltyRuleDict);
	}
	
	@Transactional(readOnly = false)
	public void delete(PenaltyRuleDict penaltyRuleDict) {
		super.delete(penaltyRuleDict);
	}
	
}