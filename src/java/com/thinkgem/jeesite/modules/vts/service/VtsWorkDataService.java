/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.vts.entity.VtsWorkData;
import com.thinkgem.jeesite.modules.vts.dao.VtsWorkDataDao;

/**
 * VTS工作数据Service
 * @author dylan wang
 * @version 2017-05-05
 */
@Service
@Transactional(readOnly = true)
public class VtsWorkDataService extends CrudService<VtsWorkDataDao, VtsWorkData> {

	@Autowired
	VtsWorkDataDao vtsWorkDataDao;
	public VtsWorkData get(String id) {
		return super.get(id);
	}
	
	public List<VtsWorkData> findList(VtsWorkData vtsWorkData) {
		return super.findList(vtsWorkData);
	}
	
	public Page<VtsWorkData> findPage(Page<VtsWorkData> page, VtsWorkData vtsWorkData) {
		return super.findPage(page, vtsWorkData);
	}
	
	@Transactional(readOnly = false)
	public void save(VtsWorkData vtsWorkData) {
		super.save(vtsWorkData);
	}
	
	@Transactional(readOnly = false)
	public void delete(VtsWorkData vtsWorkData) {
		super.delete(vtsWorkData);
	}

	public List<VtsWorkData> getSumData(VtsWorkData vtsWorkData) {
		
		return vtsWorkDataDao.getSumData(vtsWorkData);
	}
	private String addData(String a , String b){
		int sumData = Integer.parseInt(a)+Integer.parseInt(b);
		return String.valueOf(sumData);
	}
	
	public VtsWorkData getVtsSum(List<VtsWorkData> list){
		VtsWorkData v = new VtsWorkData();
		v.setToDoList("");
		for(VtsWorkData vts : list){
			v.setInportLimitShip(addData(v.getInportLimitShip(),vts.getInportLimitShip()));
			v.setTransitLimitShip(addData(v.getTransitLimitShip(),vts.getTransitLimitShip()));
			v.setPositionReport(addData(v.getPositionReport(),vts.getPositionReport()));
			v.setImportantShip(addData(v.getImportantShip(),vts. getImportantShip()));
			v.setShipFollow(addData(v.getShipFollow(),vts.getShipFollow()));
			v.setGiantShip(addData(v.getGiantShip(),vts.getGiantShip()));
			v.setFourPassenger(addData(v.getFourPassenger(),vts.getFourPassenger()));
			v.setInformationService(addData(v.getInformationService(),vts.getInformationService()));
			v.setTrafficOperation(addData(v.getTrafficOperation(),vts.getTrafficOperation()));
			v.setNavaid(addData(v.getNavaid(),vts. getNavaid()));
			v.setAvoidDanger(addData(v.getAvoidDanger(),vts.getAvoidDanger()));
			v.setEnforceDanger(addData(v.getEnforceUnion(),vts.getEnforceUnion()));
			v.setMooringSpying(addData(v.getMooringSpying(),vts.getMooringSpying()));
			v.setIllegal(addData(v.getIllegal(),vts.getIllegal()));
			if("".equals(v.getToDoList()))
				v.setToDoList(vts.getToDoList());
			else
				v.setToDoList(v.getToDoList()+"\r\n"+vts.getToDoList());
			
		}
		return v;
	}
}