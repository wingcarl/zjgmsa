/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.schedule.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.schedule.entity.ScheduleDetail;
import com.thinkgem.jeesite.modules.schedule.entity.ScheduleUser;

/**
 * 生成全局排班表DAO接口
 * @author dylan wang
 * @version 2017-05-21
 */
@MyBatisDao
public interface ScheduleDetailDao extends CrudDao<ScheduleDetail> {

	void save(ScheduleDetail scheduleDetail);

	void saveDetailUser(ScheduleUser su);

	List<ScheduleDetail> getByDate(String date);

	Object confirm(ScheduleDetail scheduleDetail);

	List<ScheduleDetail> findListByUsers(ScheduleDetail schedule);

	List<ScheduleDetail> findListByUsersDay(ScheduleDetail schedule);
	
}