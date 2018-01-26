/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.spring.entity.SpringData;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;
import com.thinkgem.jeesite.modules.spring.dao.SpringDataDao;

/**
 * 春运专项数据Service
 * @author Dylan Wang
 * @version 2018-01-26
 */
@Service
@Transactional(readOnly = true)
public class SpringDataService extends CrudService<SpringDataDao, SpringData> {
	@Autowired
	private OaCruisedataService oaCruisedataService;
	public SpringData get(String id) {
		return super.get(id);
	}
	
	public List<SpringData> findList(SpringData springData) {
		return super.findList(springData);
	}
	
	public Page<SpringData> findPage(Page<SpringData> page, SpringData springData) {
		return super.findPage(page, springData);
	}
	
	@Transactional(readOnly = false)
	public void save(SpringData springData) {
		super.save(springData);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpringData springData) {
		super.delete(springData);
	}

	public SpringData setCruisedata(SpringData springData) {
		Office office = UserUtils.getUser().getOffice();
		OaCruisedata cruiseData = oaCruisedataService.getDataByOfficeIdAndDate(office,new Date());
		springData.setZfryNum(Integer.toString(cruiseData.getCdzfrycs()));
		springData.setFbzl(Integer.toString(cruiseData.getFfxczl()));
		springData.setIllegalNum(Integer.toString(cruiseData.getFxwz()));
		if(cruiseData !=null && cruiseData.getId()!=null){
			OaCruiseStat stat = new OaCruiseStat();
			stat.setCruiseDataId(cruiseData.getId());
			List<OaCruiseStat> list = new ArrayList<OaCruiseStat>();
			list = oaCruisedataService.getStaticsById(stat);
			if(list!=null && list.size()>0){
				stat = list.get(0);
			}
			springData.setZfshipNum(Integer.toString(stat.getXhcs()));
			springData.setXhlc(Integer.toString(stat.getXhlc()));
			springData.setXhsj(Double.toString(stat.getTotalTime()));
		}
		return springData;
	}
	
}