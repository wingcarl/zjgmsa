/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * danger_spyEntity
 * @author dylan wang
 * @version 2017-09-04
 */
public class DangerInvestSpy extends DataEntity<DangerInvestSpy> {
	
	private static final long serialVersionUID = 1L;
	private Date spyTime;		// 跟踪检查时间
	private String spyNote;		// 检查情况
	private Date beginSpyTime;		// 开始 跟踪检查时间
	private Date endSpyTime;		// 结束 跟踪检查时间
	private DangerInvestDetail dangerInvestDetail;
	public DangerInvestSpy() {
		super();
	}

	public DangerInvestSpy(String id){
		super(id);
	}
	public DangerInvestSpy(DangerInvestDetail dangerInvestDetail){
		this.dangerInvestDetail = dangerInvestDetail;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpyTime() {
		return spyTime;
	}

	public void setSpyTime(Date spyTime) {
		this.spyTime = spyTime;
	}
	
	@Length(min=0, max=255, message="检查情况长度必须介于 0 和 255 之间")
	public String getSpyNote() {
		return spyNote;
	}

	public void setSpyNote(String spyNote) {
		this.spyNote = spyNote;
	}
	
	

	public DangerInvestDetail getDangerInvestDetail() {
		return dangerInvestDetail;
	}

	public void setDangerInvestDetail(DangerInvestDetail dangerInvestDetail) {
		this.dangerInvestDetail = dangerInvestDetail;
	}

	public Date getBeginSpyTime() {
		return beginSpyTime;
	}

	public void setBeginSpyTime(Date beginSpyTime) {
		this.beginSpyTime = beginSpyTime;
	}
	
	public Date getEndSpyTime() {
		return endSpyTime;
	}

	public void setEndSpyTime(Date endSpyTime) {
		this.endSpyTime = endSpyTime;
	}
		
}