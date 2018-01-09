package com.thinkgem.jeesite.modules.msa.entity;

import java.util.Date;


public class MsaIndexStat {

	private String name;
	private String totalTime;

	private String totalIllegalCount; 
	private String total;
	private Date beginTime;
	private Date endTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getTotalIllegalCount() {
		return totalIllegalCount;
	}
	public void setTotalIllegalCount(String totalIllegalCount) {
		this.totalIllegalCount = totalIllegalCount;
	}

	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
}
