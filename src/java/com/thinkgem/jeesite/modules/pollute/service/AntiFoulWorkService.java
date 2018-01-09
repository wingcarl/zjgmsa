/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulCompany;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulShip;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulWork;
import com.thinkgem.jeesite.modules.pollute.dao.AntiFoulShipDao;
import com.thinkgem.jeesite.modules.pollute.dao.AntiFoulWorkDao;

/**
 * 防污染作业Service
 * @author Dylan Wang
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class AntiFoulWorkService extends CrudService<AntiFoulWorkDao, AntiFoulWork> {
	@Autowired
	private AntiFoulCompanyService antiFoulCompanyService;
	@Autowired
	private AntiFoulShipDao antiFoulShipDao;
	public AntiFoulWork get(String id) {
		return super.get(id);
	}
	
	public List<AntiFoulWork> findList(AntiFoulWork antiFoulWork) {
		List<AntiFoulWork> workList = super.findList(antiFoulWork);
		try{
			for(AntiFoulWork work : workList){
				AntiFoulCompany company = antiFoulCompanyService.get1(work.getWorkDep());
				if(company!=null)
					work.setWorkDepName(company.getName());
				AntiFoulShip ship = antiFoulShipDao.get(work.getShipName());
				if(ship!=null)
					work.setShipNameName(ship.getShipName());
			}
			}catch(NullPointerException e){
				
			}
		return workList;
	}
	
	public Page<AntiFoulWork> findPage(Page<AntiFoulWork> page, AntiFoulWork antiFoulWork) {
		antiFoulWork.getSqlMap().put("dsf", dataScopeFilter(antiFoulWork.getCurrentUser(), "c", "u"));
		Page<AntiFoulWork> afw = super.findPage(page, antiFoulWork);
		List<AntiFoulWork> workList = afw.getList();
		try{
		for(AntiFoulWork work : workList){
			AntiFoulCompany company = antiFoulCompanyService.get1(work.getWorkDep());
			if(company!=null)
				work.setWorkDepName(company.getName());
			AntiFoulShip ship = antiFoulShipDao.get(work.getShipName());
			if(ship!=null)
				work.setShipNameName(ship.getShipName());
		}
		}catch(NullPointerException e){
			
		}
		afw.setList(workList);
		return afw;
	}
	
	@Transactional(readOnly = false)
	public void save(AntiFoulWork antiFoulWork) {
		super.save(antiFoulWork);
	}
	
	@Transactional(readOnly = false)
	public void delete(AntiFoulWork antiFoulWork) {
		super.delete(antiFoulWork);
	}
	
}