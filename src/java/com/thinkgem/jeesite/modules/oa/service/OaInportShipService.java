/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;
import com.thinkgem.jeesite.modules.cruise.entity.CruiseStatics;
import com.thinkgem.jeesite.modules.dict.entity.city.DictCityPos;
import com.thinkgem.jeesite.modules.dict.entity.port.DictRegistryPort;
import com.thinkgem.jeesite.modules.dict.service.city.DictCityPosService;
import com.thinkgem.jeesite.modules.dict.service.port.DictRegistryPortService;
import com.thinkgem.jeesite.modules.oa.dao.OaInportShipDao;

/**
 * 每日在港船舶动态Service
 * @author 王浩宇
 * @version 2017-03-09
 */
@Service
@Transactional(readOnly = true)
public class OaInportShipService extends CrudService<OaInportShipDao, OaInportShip> {

	@Autowired
	private OaInportShipDao oaInportShipDao;
	@Autowired
	private DictRegistryPortService dictRegistryPortService;
	@Autowired
	private DictCityPosService dictCityPosService;
	public OaInportShip get(String id) {
		return super.get(id);
	}
	
	public List<OaInportShip> findList(OaInportShip oaInportShip) {
		return super.findList(oaInportShip);
	}
	
	public Page<OaInportShip> findPage(Page<OaInportShip> page, OaInportShip oaInportShip) {
		oaInportShip.getSqlMap().put("dsf", dataScopeFilter(oaInportShip.getCurrentUser(), "c", "u"));
		return super.findPage(page, oaInportShip);
	}
	
	@Transactional(readOnly = false)
	public void save(OaInportShip oaInportShip) {
		super.save(oaInportShip);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaInportShip oaInportShip) {
		super.delete(oaInportShip);
	}

	public List<OaInportShip> findByShipName(OaInportShip oaInportShip) {
		return oaInportShipDao.findByShipNames(oaInportShip);
	}

	public List<String> findAllShipNames(OaInportShip oaInportShip) {
		return oaInportShipDao.findAllShipNames(oaInportShip);
	}

	public List<OaInportShip> getOfficeDefaultMessage(OaInportShip oaInportShip) {
		return oaInportShipDao.findOneByCurrentUserOffice(oaInportShip);
	}

	public List<OaInportShip> findListByUserAndDate(OaInportShip ship) {
		String officeId = ship.getCurrentUser().getOffice().getId();
		if(!"004b65a32fc348e1a4677f1db1397bbb".equals(officeId) && !"6bd316732c9b433ca3fa81b6bcc668e6".equals(officeId)){
			ship.setCreateBy(ship.getCurrentUser());
		}
		return oaInportShipDao.findListByUserAndDate(ship);
	}

	@Transactional(readOnly = false)
	public void deleteToday(OaInportShip oaInportShip) {
		oaInportShipDao.deleteToday(oaInportShip);
		
	}

	public List<String> getTodayStatics(String date) {
		List<Map<String, Object>> ms = oaInportShipDao.getTodayStatics(date);
		List<String> lds = new ArrayList<String>();
		for(Map<String, Object> m : ms){
			lds.add(m.get("cname").toString());
		}
		return lds;
	}

	public Map<String, Object> getInportShipStatics() {
		List<Map<String, Object>> ms = oaInportShipDao.getInportShipStatics();
		List<DictRegistryPort> portList = dictRegistryPortService.findList(new DictRegistryPort());
		List<DictCityPos> cityList = dictCityPosService.findList(new DictCityPos());
		Set<String> portName = new HashSet<String>();
		for(DictRegistryPort port : portList){
			portName.add(port.getName());
		}
		Map<String, Object> cjg = new HashMap<String,Object>();
		Set<Map<String, Object>> lds = new HashSet<Map<String, Object>>();
		for(Map<String, Object> m : ms){
			
			String name = m.get("cjg").toString();
			for(DictCityPos pos : cityList){
				if(pos.getCounty().equals(name) && portName.contains(name)){
					List<String> lat = new ArrayList<String>();
					lat.add(pos.getLongitude());
					lat.add(pos.getLatitude());
					cjg.put(pos.getCounty(), lat);
					Map<String, Object> mo = new HashMap<String,Object>();
					mo.put("name",name);
					mo.put("value", m.get("counter").toString());
					lds.add(mo);
				}
			}
		}
		Map<String, Object> so = new HashMap<String,Object>();
		so.put("geoPos", cjg);
		so.put("geoName", lds);
		return so;
	}
	
}