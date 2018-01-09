/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.msa.entity.MsaIndexStat;
import com.thinkgem.jeesite.modules.oa.dao.OaIllegalDao;

/**
 * 违法行为增删改查Service
 * @author dylanwang
 * @version 2017-02-23
 */
@Service
@Transactional(readOnly = true)
public class OaIllegalService extends CrudService<OaIllegalDao, OaIllegal> {

	@Autowired
	OaIllegalDao oaIllegalDao;
	
	public OaIllegal get(String id) {
		return super.get(id);
	}
	
	public List<OaIllegal> findList(OaIllegal oaIllegal) {
		return super.findList(oaIllegal);
	}
	
	public Page<OaIllegal> findPage(Page<OaIllegal> page, OaIllegal oaIllegal) {
		oaIllegal.getSqlMap().put("dsf", dataScopeFilter(oaIllegal.getCurrentUser(), "c", "u"));
		return super.findPage(page, oaIllegal);
	}
	
	@Transactional(readOnly = false)
	public void save(OaIllegal oaIllegal) {
		super.save(oaIllegal);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaIllegal oaIllegal) {
		super.delete(oaIllegal);
	}

	public List<OaIllegal> findList1(OaIllegal oaIllegal) {
		oaIllegal.getSqlMap().put("dsf", dataScopeFilter(oaIllegal.getCurrentUser(), "c", "u"));
		return oaIllegalDao.findList1(oaIllegal);
	}

	public List<OaIllegal> findByOaCruisedata(OaIllegal oaIllegal) {
		return oaIllegalDao.findByOaCruisedata(oaIllegal);
		
	}

	public Map<String, Double> getIllegalCount(OaIllegal oaIllegal) {
		List<Map<String,Object>> ms = oaIllegalDao.getIllegalCount(oaIllegal);
		Map<String,Double> mi = new HashMap<String,Double>();
		for(Map<String,Object> m : ms){
			mi.put(m.get("cname").toString(), Double.parseDouble(m.get("counter").toString()));
		}
		
		return mi;
	}

	public List<MsaIndexStat> getIllegalSum(OaIllegal di) {
		
		return oaIllegalDao.getIllegalSum(di);
	}

	public List<MsaIndexStat> getIllegalCountByMonth(OaIllegal di) {
		
		return oaIllegalDao.getIllegalCountByMonth(di);
	}

	public List<MsaIndexStat> getIllegalByType(OaIllegal di) {
		return oaIllegalDao.getIllegalByType(di);
	}

	public List<MsaIndexStat> getIllegalByResult(OaIllegal di) {
		return oaIllegalDao.getIllegalByResult(di);
	}

	public List<MsaIndexStat> getIllegalByLocation(OaIllegal di) {
		return oaIllegalDao.getIllegalByLocation(di);
	}
	
}