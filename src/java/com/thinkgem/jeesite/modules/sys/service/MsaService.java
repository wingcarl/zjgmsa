package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.danger.dao.DangerInvestDetailDao;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestDetail;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestStat;
import com.thinkgem.jeesite.modules.danger.service.DangerInvestService;
import com.thinkgem.jeesite.modules.msa.entity.MsaIndexStat;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.oa.service.OaCruisedataService;
import com.thinkgem.jeesite.modules.oa.service.OaIllegalService;

@Service
@Transactional(readOnly = true)
public class MsaService {
	@Autowired
	private OaCruisedataService oaCruisedataService;
	@Autowired
	private DangerInvestDetailDao dangerInvestDetailDao;
	@Autowired
	private OaIllegalService oaIllegalService;
	@Autowired
	private DangerInvestService dangerInvestService;
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
	public List<MsaIndexStat> getDangerStatics() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DangerInvest di = new DangerInvest();
		di.setEndFindTime(cal.getTime());
		cal.add(Calendar.YEAR, -1);
		di.setBeginFindTime(cal.getTime());
		List<DangerInvestStat> statList = dangerInvestService.getSumData(di);
		List<MsaIndexStat> list = new ArrayList<MsaIndexStat>();
		for(DangerInvestStat dis : statList){
			MsaIndexStat msa = new MsaIndexStat();
			msa.setName(dis.getOffice().getName());
			msa.setTotalTime(Integer.toString(dis.getRecifySum()));
			msa.setTotalIllegalCount((Integer.toString(dis.getNoRecifySum())));
			msa.setTotal(Integer.toString(dis.getDangerSum()));
			list.add(msa);
		}
		return list;
	}
	public List<MsaIndexStat> getDangerStaticsByMonth() {
		Calendar cal = Calendar.getInstance();
		MsaIndexStat stat = new MsaIndexStat();
		cal.setTime(new Date());
		stat.setEndTime(cal.getTime());
		cal.add(Calendar.YEAR, -1);
		List<MsaIndexStat> list = new ArrayList<MsaIndexStat>();
		stat.setBeginTime(cal.getTime());
		list = dangerInvestService.findDataByMonth(stat);
		/*for(int i=0;i<12;i++){
			DangerInvest di = new DangerInvest();
			
			di.setBeginFindTime(DateUtils.setDays(DateUtils.parseDate(cal.getTime()), 1));
			di.setEndFindTime(DateUtils.addMonths(di.getBeginFindTime(), 1));
			List<DangerInvest> diList = dangerInvestService.findList(di);
			MsaIndexStat msa = new MsaIndexStat();
			msa.setName(cal.get(Calendar.MONTH)+"月");
			String count = getCount(diList);
			msa.setTotalTime(count);
			list.add(msa);
			cal.add(Calendar.MONTH, 1);
		}*/
		return list;
	}
	private String getCount(List<DangerInvest> diList) {
		int count = 0;
		for(DangerInvest di : diList){
			count += di.getDangerInvestDetailList().size();
		}
		return Integer.toString(count);
	}
	public List<MsaIndexStat> getDangerPie() {
		Calendar cal = Calendar.getInstance();
		MsaIndexStat stat = new MsaIndexStat();
		cal.setTime(new Date());
		stat.setEndTime(cal.getTime());
		cal.add(Calendar.YEAR, -1);
		List<MsaIndexStat> list = new ArrayList<MsaIndexStat>();
		stat.setBeginTime(cal.getTime());
		list = dangerInvestService.findDataByType(stat);
		return list;
	}
	public List<MsaIndexStat> getDangerPie2() {
		Calendar cal = Calendar.getInstance();
		MsaIndexStat stat = new MsaIndexStat();
		cal.setTime(new Date());
		stat.setEndTime(cal.getTime());
		cal.add(Calendar.YEAR, -1);
		List<MsaIndexStat> list = new ArrayList<MsaIndexStat>();
		stat.setBeginTime(cal.getTime());
		list = dangerInvestService.findDataByRecify(stat);
		return list;
	}
	public List<List<MsaIndexStat>>  getIllegalStatics() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		OaIllegal di = new OaIllegal();
		di.setEndHappenDate((cal.getTime()));
		cal.add(Calendar.YEAR, -1);
		di.setBeginHappenDate(cal.getTime());
		List<MsaIndexStat> list = oaIllegalService.getIllegalSum(di);
		List<MsaIndexStat> list1 = new ArrayList<MsaIndexStat>();
		for(int i = 0;i<8;i++){
			list1.add(list.get(i));
		}
		list = oaIllegalService.getIllegalCountByMonth(di);
		List<List<MsaIndexStat>> stat = new ArrayList<List<MsaIndexStat>>();
		stat.add(list1);
		stat.add(list);
		list = oaIllegalService.getIllegalByType(di);
		List<MsaIndexStat> list3 =new ArrayList<MsaIndexStat>();
		List<String> collect = new ArrayList<String>();
		for(int i=0;i<5;i++){
			if(!collect.contains(list.get(i).getName())){
				collect.add(list.get(i).getName());
				list3.add(list.get(i));
			}else{
				for(MsaIndexStat st : list3){
					if(list.get(i).getName().equals(st.getName())){
						st.setTotalTime(Integer.toString(Integer.parseInt(st.getTotalTime())+Integer.parseInt(list.get(i).getTotalTime())));
					}
				}
			}
		}
		MsaIndexStat msaStat = new MsaIndexStat();
		msaStat.setName("其它");
		int count = 0;
		for(int i=5;i<list.size();i++){
			count += Integer.parseInt(list.get(i).getTotalTime()); 
		}
		
		msaStat.setTotalTime(Integer.toString(count));
		list3.add(msaStat);
		stat.add(list3);
		list = oaIllegalService.getIllegalByResult(di);
		stat.add(list);
		list = oaIllegalService.getIllegalByLocation(di);
		List<MsaIndexStat> list2 = new ArrayList<MsaIndexStat>();
		for(int i=0;i<10;i++){
			list2.add(list.get(i));
		}
		stat.add(list2);
		return stat;
	}

}
