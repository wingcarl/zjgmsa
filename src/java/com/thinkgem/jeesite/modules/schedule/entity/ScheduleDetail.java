/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.schedule.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 生成全局排班表Entity
 * @author dylan wang
 * @version 2017-05-21
 */
public class ScheduleDetail extends DataEntity<ScheduleDetail> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 部门
	private Date scheduleDate;		// 排班日期
	private String userList1; // 白班
	private String userList2; // 中班
	private String userList3; // 夜班
	private String userList4; // 领导班
	private String confirm;
	private String confirmBy;
	private Date beginHappenDate;
	private Date endHappenDate;
	public ScheduleDetail() {
		super();
	}

	public ScheduleDetail(String id){
		super(id);
	}

	@ExcelField(title="部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="日期", type=0, align=1, sort=90)
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	@ExcelField(title="白班", align=2, sort=100)
	public String getUserList1() {
		return userList1;
	}

	public void setUserList1(String userList1) {
		this.userList1 = userList1;
	}

	@ExcelField(title="中班", align=2, sort=110)
	public String getUserList2() {
		return userList2;
	}

	public void setUserList2(String userList2) {
		this.userList2 = userList2;
	}

	@ExcelField(title="夜班", align=2, sort=120)
	public String getUserList3() {
		return userList3;
	}

	public void setUserList3(String userList3) {
		this.userList3 = userList3;
	}

	@ExcelField(title="领导班", align=2, sort=140)
	public String getUserList4() {
		return userList4;
	}

	public void setUserList4(String userList4) {
		this.userList4 = userList4;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
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