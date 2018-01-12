/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.msa.entity.MsaHutongProject;
import com.thinkgem.jeesite.modules.msa.dao.MsaHutongProjectDao;

/**
 * 沪通大桥水工动态表Service
 * @author Dylan Wang
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class MsaHutongProjectService extends CrudService<MsaHutongProjectDao, MsaHutongProject> {

	public MsaHutongProject get(String id) {
		return super.get(id);
	}
	
	public List<MsaHutongProject> findList(MsaHutongProject msaHutongProject) {
		return super.findList(msaHutongProject);
	}
	
	public Page<MsaHutongProject> findPage(Page<MsaHutongProject> page, MsaHutongProject msaHutongProject) {
		return super.findPage(page, msaHutongProject);
	}
	
	@Transactional(readOnly = false)
	public void save(MsaHutongProject msaHutongProject) {
		super.save(msaHutongProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsaHutongProject msaHutongProject) {
		super.delete(msaHutongProject);
	}
	
}