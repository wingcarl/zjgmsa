/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 电子巡查相关表Entity
 * @author 王浩宇
 * @version 2017-03-09
 */
public class OaDigitalcruise extends DataEntity<OaDigitalcruise> {
	
	private static final long serialVersionUID = 1L;
	private String cruisePeople="";		// 巡航人员
	private String cruiseTime="";		// 巡航起止时间
	private String cruiseArea="";		// 巡航区域
	private String solveProblem="";		// 发现解决的问题
	private Integer cruiseTimes=0;		// 巡航次数
	private double cruiseTotalTime=0.0;		// 巡航时间（小时）十进制
	private Integer illegalCount=0;		// 发现违章数量
	private String equipProblem="";
	private Integer dkyc = 0;
	private Integer yscc = 0;
	private Integer ysrc = 0;
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	private boolean editable = false;
	private String officeId = "";
	
	public OaDigitalcruise() {
		super();
	}

	public OaDigitalcruise(String id){
		super(id);
	}

	@Length(min=0, max=255, message="巡航人员长度必须介于 0 和 255 之间")
	public String getCruisePeople() {
		return cruisePeople;
	}

	public void setCruisePeople(String cruisePeople) {
		this.cruisePeople = cruisePeople;
	}
	
	@Length(min=0, max=255, message="巡航起止时间长度必须介于 0 和 255 之间")
	public String getCruiseTime() {
		return cruiseTime;
	}

	public void setCruiseTime(String cruiseTime) {
		this.cruiseTime = cruiseTime;
	}
	
	@Length(min=0, max=1024, message="巡航区域长度必须介于 0 和 1024 之间")
	public String getCruiseArea() {
		return cruiseArea;
	}

	public void setCruiseArea(String cruiseArea) {
		this.cruiseArea = cruiseArea;
	}
	
	@Length(min=0, max=1024, message="发现解决的问题长度必须介于 0 和 1024 之间")
	public String getSolveProblem() {
		return solveProblem;
	}

	public void setSolveProblem(String solveProblem) {
		this.solveProblem = solveProblem;
	}
	
	

	public Integer getCruiseTimes() {
		return cruiseTimes;
	}

	public void setCruiseTimes(Integer cruiseTimes) {
		this.cruiseTimes = cruiseTimes;
	}

	public double getCruiseTotalTime() {
		return cruiseTotalTime;
	}

	public void setCruiseTotalTime(double cruiseTotalTime) {
		this.cruiseTotalTime = cruiseTotalTime;
	}

	public Integer getIllegalCount() {
		return illegalCount;
	}

	public void setIllegalCount(Integer illegalCount) {
		this.illegalCount = illegalCount;
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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getEquipProblem() {
		return equipProblem;
	}

	public void setEquipProblem(String equipProblem) {
		this.equipProblem = equipProblem;
	}

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

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
}