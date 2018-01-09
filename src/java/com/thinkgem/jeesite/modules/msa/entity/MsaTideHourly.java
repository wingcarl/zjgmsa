package com.thinkgem.jeesite.modules.msa.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class MsaTideHourly extends DataEntity<MsaTideHourly>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date tideTime;
	private String tideHigh;
	private Date tideBeginTime;
	private Date tideEndTime;
	
	public Date getTideTime() {
		return tideTime;
	}
	public void setTideTime(Date tideTime) {
		this.tideTime = tideTime;
	}
	public String getTideHigh() {
		return tideHigh;
	}
	public void setTideHigh(String tideHigh) {
		this.tideHigh = tideHigh;
	}
	public Date getTideBeginTime() {
		return tideBeginTime;
	}
	public void setTideBeginTime(Date tideBeginTime) {
		this.tideBeginTime = tideBeginTime;
	}
	public Date getTideEndTime() {
		return tideEndTime;
	}
	public void setTideEndTime(Date tideEndTime) {
		this.tideEndTime = tideEndTime;
	}
	
}
