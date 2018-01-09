package com.thinkgem.jeesite.modules.cruise.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;

public class CruiseStatics extends DataEntity<CruiseStatics>  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	private OaCruiseStat oaCruiseStat = new OaCruiseStat();
	public Date getBeginHappenDate() {
		return beginHappenDate;
	}
	public void setBeginHappenDate(Date beginHappenDate) {
		this.beginHappenDate = beginHappenDate;
	}
	public Date getEndHappenDate() {
		return endHappenDate;
	}
	public void setEndHappenDate(Date endHappenDate) {
		this.endHappenDate = endHappenDate;
	}
	public OaCruiseStat getOaCruiseStat() {
		oaCruiseStat.setBeginHappenDate(beginHappenDate);
		oaCruiseStat.setEndHappenDate(endHappenDate);
		return oaCruiseStat;
	}
	public void setOaCruiseStat(OaCruiseStat oaCruiseStat) {
		this.oaCruiseStat = oaCruiseStat;
	}
	
}
