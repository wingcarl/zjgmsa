package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

public class OaCruisedataArea {
	private String id;
	private String oaCruisedataId;
	private String oaCruiseareaId;
	private Integer times;
	private String createBy;
	private Date createDate;
	private String remark;
	private Date beginHappenDate;
	private Date endHappenDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOaCruisedataId() {
		return oaCruisedataId;
	}
	public void setOaCruisedataId(String oaCruisedataId) {
		this.oaCruisedataId = oaCruisedataId;
	}
	public String getOaCruiseareaId() {
		return oaCruiseareaId;
	}
	public void setOaCruiseareaId(String oaCruiseareaId) {
		this.oaCruiseareaId = oaCruiseareaId;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	
	
}
