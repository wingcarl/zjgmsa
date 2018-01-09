/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.intercept.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.intercept.entity.GridManaIntercept;
import com.thinkgem.jeesite.modules.intercept.dao.GridManaInterceptDao;

/**
 * 拦截船舶情况Service
 * @author Dylan Wang
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class GridManaInterceptService extends CrudService<GridManaInterceptDao, GridManaIntercept> {

	@Autowired
	GridManaInterceptDao gridManaInterceptDao;
	
	public GridManaIntercept get(String id) {
		return super.get(id);
	}
	
	public List<GridManaIntercept> findList(GridManaIntercept gridManaIntercept) {
		return super.findList(gridManaIntercept);
	}
	
	public Page<GridManaIntercept> findPage(Page<GridManaIntercept> page, GridManaIntercept gridManaIntercept) {
		return super.findPage(page, gridManaIntercept);
	}
	
	@Transactional(readOnly = false)
	public void save(GridManaIntercept gridManaIntercept) {
		super.save(gridManaIntercept);
	}
	
	@Transactional(readOnly = false)
	public void delete(GridManaIntercept gridManaIntercept) {
		super.delete(gridManaIntercept);
	}

	public Integer getCount(GridManaIntercept gridManaIntercept) {
		Map<String,Long> counter = gridManaInterceptDao.getCount(gridManaIntercept);
		String c = counter.get("counter").toString();
		return Integer.parseInt(c);
	}
	
}