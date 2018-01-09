package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.msa.entity.MsaIndexStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;

@Service
@Transactional(readOnly = true)
public class MsaService {
	@Autowired
	private OaCruisedataService oaCruisedataService;
	public List<MsaIndexStat> getStatics() {
		List<OaCruiseStat> dataList = oaCruisedataService.getStaticsByDate(new OaCruiseStat());
		List<MsaIndexStat> list = new ArrayList<MsaIndexStat>();
		for(OaCruiseStat stat : dataList){
			MsaIndexStat msa = new MsaIndexStat();
			msa.setTotalTime(Double.toString(stat.getTotalTime()));
			msa.setName(stat.getName());
			msa.setTotalIllegalCount(Double.toString(stat.getFxwz()));
			list.add(msa);
		}
		return list;
	}

}
