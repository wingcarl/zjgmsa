/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 对周巡航计划的增删改查Entity
 * @author 王浩宇
 * @version 2017-04-01
 */
public class CruiseWeekPlan extends DataEntity<CruiseWeekPlan> {
	
	private static final long serialVersionUID = 1L;
	private Date startDate;		// 开始日期
	private Date endDate;		// 结束日期
	private Office office;		// 所属部门
	private String hxtId;		// 海巡艇
	private String cruiseGrid;		// 巡航网格
	private String cruiseTime;		// 巡航时间
	private String importantPos;		// 重点部件
	private String importantContent;		// 巡航工作重点
	private Date HappenDate;
	private List<CruiseWeekPlanDetails> cruiseWeekPlanDetailsList = Lists.newArrayList();		// 子表列表
	
	public CruiseWeekPlan() {
		super();
	}

	public CruiseWeekPlan(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=64, message="海巡艇长度必须介于 0 和 64 之间")
	public String getHxtId() {
		return hxtId;
	}

	public void setHxtId(String hxtId) {
		this.hxtId = hxtId;
	}
	
	@Length(min=0, max=64, message="巡航网格长度必须介于 0 和 64 之间")
	public String getCruiseGrid() {
		return cruiseGrid;
	}

	public void setCruiseGrid(String cruiseGrid) {
		this.cruiseGrid = cruiseGrid;
	}
	
	@Length(min=0, max=255, message="巡航时间长度必须介于 0 和 255 之间")
	public String getCruiseTime() {
		return cruiseTime;
	}

	public void setCruiseTime(String cruiseTime) {
		this.cruiseTime = cruiseTime;
	}
	
	@Length(min=0, max=512, message="重点部件长度必须介于 0 和 512 之间")
	public String getImportantPos() {
		return importantPos;
	}

	public void setImportantPos(String importantPos) {
		this.importantPos = importantPos;
	}
	
	@Length(min=0, max=1024, message="巡航工作重点长度必须介于 0 和 1024 之间")
	public String getImportantContent() {
		return importantContent;
	}

	public void setImportantContent(String importantContent) {
		this.importantContent = importantContent;
	}
	
	public List<CruiseWeekPlanDetails> getCruiseWeekPlanDetailsList() {
		return cruiseWeekPlanDetailsList;
	}

	public void setCruiseWeekPlanDetailsList(List<CruiseWeekPlanDetails> cruiseWeekPlanDetailsList) {
		this.cruiseWeekPlanDetailsList = cruiseWeekPlanDetailsList;
	}

	public Date getHappenDate() {
		return HappenDate;
	}

	public void setHappenDate(Date happenDate) {
		HappenDate = happenDate;
	}

	
}