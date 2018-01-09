package com.thinkgem.jeesite.modules.msa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.msa.entity.MsaTideDaily;
import com.thinkgem.jeesite.modules.msa.entity.MsaTideHourly;

@MyBatisDao
public interface TideDao {

	List<MsaTideDaily> getDailyTideList(MsaTideDaily td);

	List<MsaTideHourly> findTideHourlyList(MsaTideHourly hour);

}
