/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cruise.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 停航检修计划Entity
 * @author Dylan Wang
 * @version 2017-06-26
 */
public class CruiseStopPlan extends DataEntity<CruiseStopPlan> {
	
	private static final long serialVersionUID = 1L;
	private Date stopDate;		// 停航检修日期
	private Office office;		// 停航海巡艇
	private Date beginStopDate;		// 开始 停航检修日期
	private Date endStopDate;		// 结束 停航检修日期
	private Office parentOffice;
	public CruiseStopPlan() {
		super();
	}

	public CruiseStopPlan(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Date getBeginStopDate() {
		return beginStopDate;
	}

	public void setBeginStopDate(Date beginStopDate) {
		this.beginStopDate = beginStopDate;
	}
	
	public Date getEndStopDate() {
		return endStopDate;
	}

	public void setEndStopDate(Date endStopDate) {
		this.endStopDate = endStopDate;
	}

	public Office getParentOffice() {
		return parentOffice;
	}

	public void setParentOffice(Office parentOffice) {
		this.parentOffice = parentOffice;
	}
		
}