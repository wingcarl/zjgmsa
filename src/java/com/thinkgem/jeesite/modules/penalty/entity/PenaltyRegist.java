/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 处罚初始信息收集Entity
 * @author dylan wang
 * @version 2017-05-08
 */
public class PenaltyRegist extends DataEntity<PenaltyRegist> {
	
	private static final long serialVersionUID = 1L;
	private String shipName;		// 船名
	private String illegalDetail;		// 违法行为情况
	private String officer;		// 发现人员
	private Date findDate;		// 发现日期
	private String certificateGetDetail;		// 文书调阅情况
	private String findLoc;
	private String telephone;		// 联系方式
	private Date beginFindDate;		// 开始 发现日期
	private Date endFindDate;		// 结束 发现日期
	
	public PenaltyRegist() {
		super();
	}

	public PenaltyRegist(String id){
		super(id);
	}

	@Length(min=0, max=64, message="船名长度必须介于 0 和 64 之间")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@Length(min=0, max=64, message="违法行为情况长度必须介于 0 和 64 之间")
	public String getIllegalDetail() {
		return illegalDetail;
	}

	public void setIllegalDetail(String illegalDetail) {
		this.illegalDetail = illegalDetail;
	}
	
	@Length(min=0, max=64, message="发现人员长度必须介于 0 和 64 之间")
	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFindDate() {
		return findDate;
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}
	
	@Length(min=0, max=128, message="文书调阅情况长度必须介于 0 和 128 之间")
	public String getCertificateGetDetail() {
		return certificateGetDetail;
	}

	public void setCertificateGetDetail(String certificateGetDetail) {
		this.certificateGetDetail = certificateGetDetail;
	}
	
	@Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Date getBeginFindDate() {
		return beginFindDate;
	}

	public void setBeginFindDate(Date beginFindDate) {
		this.beginFindDate = beginFindDate;
	}
	
	public Date getEndFindDate() {
		return endFindDate;
	}

	public void setEndFindDate(Date endFindDate) {
		this.endFindDate = endFindDate;
	}

	public String getFindLoc() {
		return findLoc;
	}

	public void setFindLoc(String findLoc) {
		this.findLoc = findLoc;
	}
		
}