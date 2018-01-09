/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.PortData;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.oa.dao.OaCruisedataDao;
import com.thinkgem.jeesite.modules.oa.dao.OaDigitalcruiseDao;

/**
 * 电子巡查相关表Service
 * @author 王浩宇
 * @version 2017-03-09
 */
@Service
@Transactional(readOnly = true)
public class OaDigitalcruiseService extends CrudService<OaDigitalcruiseDao, OaDigitalcruise> {
	@Autowired
	private OaDigitalcruiseDao oaDigitalcruiseDao;
	public OaDigitalcruise get(String id) {
		return super.get(id);
	}
	
	public List<OaDigitalcruise> findList(OaDigitalcruise oaDigitalcruise) {
		return super.findList(oaDigitalcruise);
	}
	
	public Page<OaDigitalcruise> findPage(Page<OaDigitalcruise> page, OaDigitalcruise oaDigitalcruise) {
		oaDigitalcruise.getSqlMap().put("dsf", dataScopeFilter(oaDigitalcruise.getCurrentUser(), "c", "u"));
		return super.findPage(page, oaDigitalcruise);
	}
	
	@Transactional(readOnly = false)
	public void save(OaDigitalcruise oaDigitalcruise) {
		super.save(oaDigitalcruise);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaDigitalcruise oaDigitalcruise) {
		super.delete(oaDigitalcruise);
	}

	public List<OaDigitalcruise> getDigitalcruiseDataByDate(OaDigitalcruise oaDigitalcruise, Date nextDay) {
		oaDigitalcruise.setCreateDate(nextDay);
		oaDigitalcruise.setCreateBy(UserUtils.getUser());
		return oaDigitalcruiseDao.getDigitalcruiseDataByDate(oaDigitalcruise);
	}

	public PortData getPortStatics(PortData portData) {
		OaDigitalcruise oaDigitalcruise = new OaDigitalcruise();
		oaDigitalcruise.setBeginHappenDate(portData.getBeginHappenDate());
		oaDigitalcruise.setEndHappenDate(portData.getEndHappenDate());
		oaDigitalcruise.setOfficeId(portData.getOfficeId());
		oaDigitalcruise.getSqlMap().put("dsf", dataScopeFilter(oaDigitalcruise.getCurrentUser(), "c", "u"));
		List<OaDigitalcruise> list =  oaDigitalcruiseDao.getPortStatics(oaDigitalcruise);
		try{
				OaDigitalcruise oa =  list.get(0);
				portData.setDkyc(oa.getDkyc());
				portData.setYscc(oa.getYscc());
				portData.setYsrc(oa.getYsrc());
				return portData;
			
		}catch(Exception e){
			return new PortData();
	    }
	}

	public List<String> getTodayStatics(String date) {
		List<Map<String, Object>> ms = oaDigitalcruiseDao.getTodayStatics(date);
		List<String> lds = new ArrayList<String>();
		for(Map<String, Object> m : ms){
			lds.add(m.get("cname").toString());
		}
		return lds;
	}
}