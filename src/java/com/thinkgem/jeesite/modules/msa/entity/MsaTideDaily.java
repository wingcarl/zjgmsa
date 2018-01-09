package com.thinkgem.jeesite.modules.msa.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class MsaTideDaily extends DataEntity<MsaTideDaily>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Date tideDate;
	private int dailyNo;
	private String tideTime;
	private String tideHigh;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTideDate() {
		return tideDate;
	}
	public void setTideDate(Date tideDate) {
		this.tideDate = tideDate;
	}
	public int getDailyNo() {
		return dailyNo;
	}
	public void setDailyNo(int dailyNo) {
		this.dailyNo = dailyNo;
	}
	public String getTideTime() {
		return tideTime;
	}
	public void setTideTime(String tideTime) {
		this.tideTime = tideTime;
	}
	public String getTideHigh() {
		return tideHigh;
	}
	public void setTideHigh(String tideHigh) {
		this.tideHigh = tideHigh;
	}
	
}
