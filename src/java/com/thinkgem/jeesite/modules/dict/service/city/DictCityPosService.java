/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.service.city;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dict.entity.city.DictCityPos;
import com.thinkgem.jeesite.modules.dict.dao.city.DictCityPosDao;

/**
 * 城市经纬度字典管理Service
 * @author dylan wang
 * @version 2017-05-23
 */
@Service
@Transactional(readOnly = true)
public class DictCityPosService extends CrudService<DictCityPosDao, DictCityPos> {

	public DictCityPos get(String id) {
		return super.get(id);
	}
	
	public List<DictCityPos> findList(DictCityPos dictCityPos) {
		return super.findList(dictCityPos);
	}
	
	public Page<DictCityPos> findPage(Page<DictCityPos> page, DictCityPos dictCityPos) {
		return super.findPage(page, dictCityPos);
	}
	
	@Transactional(readOnly = false)
	public void save(DictCityPos dictCityPos) {
		super.save(dictCityPos);
	}
	
	@Transactional(readOnly = false)
	public void delete(DictCityPos dictCityPos) {
		super.delete(dictCityPos);
	}
	
}