/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.service.port;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dict.entity.port.DictRegistryPort;
import com.thinkgem.jeesite.modules.dict.dao.port.DictRegistryPortDao;

/**
 * 船籍港字典信息维护Service
 * @author dylan wang
 * @version 2017-05-23
 */
@Service
@Transactional(readOnly = true)
public class DictRegistryPortService extends CrudService<DictRegistryPortDao, DictRegistryPort> {

	public DictRegistryPort get(String id) {
		return super.get(id);
	}
	
	public List<DictRegistryPort> findList(DictRegistryPort dictRegistryPort) {
		return super.findList(dictRegistryPort);
	}
	
	public Page<DictRegistryPort> findPage(Page<DictRegistryPort> page, DictRegistryPort dictRegistryPort) {
		return super.findPage(page, dictRegistryPort);
	}
	
	@Transactional(readOnly = false)
	public void save(DictRegistryPort dictRegistryPort) {
		super.save(dictRegistryPort);
	}
	
	@Transactional(readOnly = false)
	public void delete(DictRegistryPort dictRegistryPort) {
		super.delete(dictRegistryPort);
	}
	
}