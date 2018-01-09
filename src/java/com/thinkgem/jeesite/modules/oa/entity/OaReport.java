package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

public class OaReport {

	private Date createDate;
	private String reportType;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
