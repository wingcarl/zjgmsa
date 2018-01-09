/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyBasicInfo;
import com.thinkgem.jeesite.modules.penalty.dao.PenaltyBasicInfoDao;

/**
 * 处罚基础信息配置表Service
 * @author dylan wang
 * @version 2017-05-09
 */
@Service
@Transactional(readOnly = true)
public class PenaltyBasicInfoService extends CrudService<PenaltyBasicInfoDao, PenaltyBasicInfo> {

	public PenaltyBasicInfo get(String id) {
		return super.get(id);
	}
	
	public List<PenaltyBasicInfo> findList(PenaltyBasicInfo penaltyBasicInfo) {
		return super.findList(penaltyBasicInfo);
	}
	
	public Page<PenaltyBasicInfo> findPage(Page<PenaltyBasicInfo> page, PenaltyBasicInfo penaltyBasicInfo) {
		return super.findPage(page, penaltyBasicInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PenaltyBasicInfo penaltyBasicInfo) {
		super.save(penaltyBasicInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PenaltyBasicInfo penaltyBasicInfo) {
		super.delete(penaltyBasicInfo);
	}

	/*public JSONObject findPenaltyBasicInfo(PenaltyBasicInfo penaltyBasicInfo) {
		PenaltyBasicInfo pbi = super.get(penaltyBasicInfo.getId());
		return JSON.pbi
	}*/
	
}