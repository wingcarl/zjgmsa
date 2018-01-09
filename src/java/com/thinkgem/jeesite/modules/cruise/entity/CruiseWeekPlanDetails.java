/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 对周巡航计划的增删改查Entity
 * @author 王浩宇
 * @version 2017-04-01
 */
public class CruiseWeekPlanDetails extends DataEntity<CruiseWeekPlanDetails> {
	
	private static final long serialVersionUID = 1L;
	private CruiseWeekPlan weeklyPlanId;		// 周计划表 父类
	private Date arrangeDate;		// 计划日期
	private String dayCruisePeople;		// 白天随艇执法人员
	private String cruiseCaptain;		// 海巡艇当班驾驶员
	private String isNightCruise;		// 是否夜航艇
	private String nightCruiseGrid;		// 夜航网格
	private String nightCruisePeople;		// 夜航随艇执法人员
	
	public CruiseWeekPlanDetails() {
		super();
	}

	public CruiseWeekPlanDetails(String id){
		super(id);
	}

	public CruiseWeekPlanDetails(CruiseWeekPlan weeklyPlanId){
		this.weeklyPlanId = weeklyPlanId;
	}

	@Length(min=0, max=64, message="周计划表长度必须介于 0 和 64 之间")
	public CruiseWeekPlan getWeeklyPlanId() {
		return weeklyPlanId;
	}

	public void setWeeklyPlanId(CruiseWeekPlan weeklyPlanId) {
		this.weeklyPlanId = weeklyPlanId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(Date arrangeDate) {
		this.arrangeDate = arrangeDate;
	}
	
	@Length(min=0, max=255, message="白天随艇执法人员长度必须介于 0 和 255 之间")
	public String getDayCruisePeople() {
		return dayCruisePeople;
	}

	public void setDayCruisePeople(String dayCruisePeople) {
		this.dayCruisePeople = dayCruisePeople;
	}
	
	@Length(min=0, max=255, message="海巡艇当班驾驶员长度必须介于 0 和 255 之间")
	public String getCruiseCaptain() {
		return cruiseCaptain;
	}

	public void setCruiseCaptain(String cruiseCaptain) {
		this.cruiseCaptain = cruiseCaptain;
	}
	
	@Length(min=0, max=11, message="是否夜航艇长度必须介于 0 和 11 之间")
	public String getIsNightCruise() {
		return isNightCruise;
	}

	public void setIsNightCruise(String isNightCruise) {
		this.isNightCruise = isNightCruise;
	}
	
	@Length(min=0, max=255, message="夜航网格长度必须介于 0 和 255 之间")
	public String getNightCruiseGrid() {
		return nightCruiseGrid;
	}

	public void setNightCruiseGrid(String nightCruiseGrid) {
		this.nightCruiseGrid = nightCruiseGrid;
	}
	
	@Length(min=0, max=255, message="夜航随艇执法人员长度必须介于 0 和 255 之间")
	public String getNightCruisePeople() {
		return nightCruisePeople;
	}

	public void setNightCruisePeople(String nightCruisePeople) {
		this.nightCruisePeople = nightCruisePeople;
	}
	
}