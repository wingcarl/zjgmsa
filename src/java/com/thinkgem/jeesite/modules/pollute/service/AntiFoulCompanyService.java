/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulCompany;
import com.thinkgem.jeesite.modules.pollute.dao.AntiFoulCompanyDao;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulShip;
import com.thinkgem.jeesite.modules.pollute.dao.AntiFoulShipDao;

/**
 * 防污染配置Service
 * @author Dylan Wang
 * @version 2017-12-21
 */
@Service
@Transactional(readOnly = true)
public class AntiFoulCompanyService extends CrudService<AntiFoulCompanyDao, AntiFoulCompany> {

	@Autowired
	private AntiFoulShipDao antiFoulShipDao;
	
	public AntiFoulCompany get(String id) {
		AntiFoulCompany antiFoulCompany = super.get(id);
		antiFoulCompany.setAntiFoulShipList(antiFoulShipDao.findList(new AntiFoulShip(antiFoulCompany)));
		return antiFoulCompany;
	}
	
	public List<AntiFoulCompany> findList(AntiFoulCompany antiFoulCompany) {
		return super.findList(antiFoulCompany);
	}
	
	public Page<AntiFoulCompany> findPage(Page<AntiFoulCompany> page, AntiFoulCompany antiFoulCompany) {
		return super.findPage(page, antiFoulCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(AntiFoulCompany antiFoulCompany) {
		super.save(antiFoulCompany);
		List<String> ships = antiFoulCompany.getShipLists();
		List<AntiFoulShip> shipList = new ArrayList<AntiFoulShip>();
		if(ships!=null){
			for(String ship :ships){
				AntiFoulShip s = new AntiFoulShip();
				s.setShipName(ship);
				s.setId("");
				s.setCreateBy(antiFoulCompany.getCreateBy());
				s.setCreateDate(antiFoulCompany.getCreateDate());
				s.setUpdateBy(antiFoulCompany.getUpdateBy());
				s.setUpdateDate(antiFoulCompany.getUpdateDate());
				s.setDelFlag(antiFoulCompany.getDelFlag());
				shipList.add(s);
			}
		}
		antiFoulCompany.setAntiFoulShipList(shipList);
		if(antiFoulCompany.getAntiFoulShipList()!=null){
			for (AntiFoulShip antiFoulShip : antiFoulCompany.getAntiFoulShipList()){
				if (antiFoulShip.getId() == null){
					continue;
				}
				if (AntiFoulShip.DEL_FLAG_NORMAL.equals(antiFoulShip.getDelFlag())){
					if (StringUtils.isBlank(antiFoulShip.getId())){
						antiFoulShip.setCompany(antiFoulCompany);
						antiFoulShip.preInsert();
						antiFoulShipDao.insert(antiFoulShip);
					}else{
						antiFoulShip.preUpdate();
						antiFoulShipDao.update(antiFoulShip);
					}
				}else{
					antiFoulShipDao.delete(antiFoulShip);
				}
			}
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(AntiFoulCompany antiFoulCompany) {
		super.delete(antiFoulCompany);
		antiFoulShipDao.delete(new AntiFoulShip(antiFoulCompany));
	}

	public List<AntiFoulShip> findAllShip() {
		
		return antiFoulShipDao.findAllList(new AntiFoulShip());
	}

	public AntiFoulCompany get1(String id) {
		AntiFoulCompany antiFoulCompany = super.get(id);
		return antiFoulCompany;
	}
	
}