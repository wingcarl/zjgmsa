package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

/**
 * 渡口数据DTO
 * @author Administrator
 *
 */
public class PortData {
	private Integer dkyc = 0;
	private Integer yscc = 0;
	private Integer ysrc = 0;
	private Date beginHappenDate;
	private Date endHappenDate;
	private Date beginHappenDate1;
	private Date endHappenDate1;
	private String officeId;
	public Integer getDkyc() {
		return dkyc;
	}
	public void setDkyc(Integer dkyc) {
		this.dkyc = dkyc;
	}
	public Integer getYscc() {
		return yscc;
	}
	public void setYscc(Integer yscc) {
		this.yscc = yscc;
	}
	
	public Integer getYsrc() {
		return ysrc;
	}
	public void setYsrc(Integer ysrc) {
		this.ysrc = ysrc;
	}
	public Date getBeginHappenDate() {
		return beginHappenDate;
	}
	public void setBeginHappenDate(Date beginHappenDate) {
		this.beginHappenDate = beginHappenDate;
	}
	public Date getBeginEndDate() {
		return endHappenDate;
	}
	public void setBeginEndDate(Date endHappenDate) {
		this.endHappenDate = endHappenDate;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public Date getEndHappenDate() {
		return endHappenDate;
	}
	public void setEndHappenDate(Date endHappenDate) {
		this.endHappenDate = endHappenDate;
	}
	public Date getBeginHappenDate1() {
		return beginHappenDate1;
	}
	public void setBeginHappenDate1(Date beginHappenDate1) {
		this.beginHappenDate1 = beginHappenDate1;
	}
	public Date getEndHappenDate1() {
		return endHappenDate1;
	}
	public void setEndHappenDate1(Date endHappenDate1) {
		this.endHappenDate1 = endHappenDate1;
	}
	
	
}
