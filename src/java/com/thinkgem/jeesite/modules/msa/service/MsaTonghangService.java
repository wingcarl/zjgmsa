/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.msa.entity.MsaTonghang;
import com.thinkgem.jeesite.modules.msa.dao.MsaTonghangDao;

/**
 * 通航环境表Service
 * @author Dylan Wang
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class MsaTonghangService extends CrudService<MsaTonghangDao, MsaTonghang> {

	public MsaTonghang get(String id) {
		return super.get(id);
	}
	
	public List<MsaTonghang> findList(MsaTonghang msaTonghang) {
		return super.findList(msaTonghang);
	}
	
	public Page<MsaTonghang> findPage(Page<MsaTonghang> page, MsaTonghang msaTonghang) {
		return super.findPage(page, msaTonghang);
	}
	
	@Transactional(readOnly = false)
	public void save(MsaTonghang msaTonghang) {
		super.save(msaTonghang);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsaTonghang msaTonghang) {
		super.delete(msaTonghang);
	}
	
}