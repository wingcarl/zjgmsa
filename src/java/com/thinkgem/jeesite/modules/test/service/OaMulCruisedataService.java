/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.OaMulCruisedata;
import com.thinkgem.jeesite.modules.test.dao.OaMulCruisedataDao;
import com.thinkgem.jeesite.modules.test.entity.OaCruisetime;
import com.thinkgem.jeesite.modules.test.dao.OaCruisetimeDao;

/**
 * 巡航数据带字表Service
 * @author dylan wang
 * @version 2017-02-26
 */
@Service
@Transactional(readOnly = true)
public class OaMulCruisedataService extends CrudService<OaMulCruisedataDao, OaMulCruisedata> {

	@Autowired
	private OaCruisetimeDao oaCruisetimeDao;
	
	public OaMulCruisedata get(String id) {
		OaMulCruisedata oaMulCruisedata = super.get(id);
		return oaMulCruisedata;
	}
	
	public List<OaMulCruisedata> findList(OaMulCruisedata oaMulCruisedata) {
		return super.findList(oaMulCruisedata);
	}
	
	public Page<OaMulCruisedata> findPage(Page<OaMulCruisedata> page, OaMulCruisedata oaMulCruisedata) {
		return super.findPage(page, oaMulCruisedata);
	}
	
	@Transactional(readOnly = false)
	public void save(OaMulCruisedata oaMulCruisedata) {
		super.save(oaMulCruisedata);
		for (OaCruisetime oaCruisetime : oaMulCruisedata.getOaCruisetimeList()){
			if (oaCruisetime.getId() == null){
				continue;
			}
			if (OaCruisetime.DEL_FLAG_NORMAL.equals(oaCruisetime.getDelFlag())){
				if (StringUtils.isBlank(oaCruisetime.getId())){
					oaCruisetime.preInsert();
					oaCruisetimeDao.insert(oaCruisetime);
				}else{
					oaCruisetime.preUpdate();
					oaCruisetimeDao.update(oaCruisetime);
				}
			}else{
				oaCruisetimeDao.delete(oaCruisetime);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(OaMulCruisedata oaMulCruisedata) {
		super.delete(oaMulCruisedata);
	}
	
}