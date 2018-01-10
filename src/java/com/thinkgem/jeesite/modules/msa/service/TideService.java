package com.thinkgem.jeesite.modules.msa.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.msa.dao.TideDao;
import com.thinkgem.jeesite.modules.msa.entity.MsaTideDaily;
import com.thinkgem.jeesite.modules.msa.entity.MsaTideHourly;

@Service
@Transactional(readOnly = true)
public class TideService {

	@Autowired
	TideDao tideDao;
	public String getDailyTide(Date d){
		String dailyTide = "";
		MsaTideDaily td = new MsaTideDaily();
		td.setTideDate(d);
		List<MsaTideDaily> dailyList = tideDao.getDailyTideList(td);
		int i = 1;
		for(MsaTideDaily da : dailyList){
			
			if(i==1){
				dailyTide += da.getTideTime();
				dailyTide +=("，"+da.getTideHigh()+"；");
			}
			else if(i==2){
				dailyTide += da.getTideTime();
				dailyTide +=("，"+da.getTideHigh()+"。");
				dailyTide += "\r\n";
			}else if(i==3){
				dailyTide += ("               "+da.getTideTime());
				if(dailyList.size()==4){
					dailyTide +=("，"+da.getTideHigh()+"；");
				}else if(dailyList.size()==3){
					dailyTide +=("，"+da.getTideHigh()+"。");
				}
			}
			else if(i==4) {
				dailyTide += da.getTideTime();
				dailyTide +=("，"+da.getTideHigh()+"	。");
			}
			i++;
		}
		return dailyTide;
	}
	public List<MsaTideHourly> findTideHourly(MsaTideHourly hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, -12);
		hour.setTideBeginTime(cal.getTime());
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 12);
		hour.setTideEndTime(cal.getTime());
		List<MsaTideHourly> hourList = tideDao.findTideHourlyList(hour);
		return hourList;
	}
}
