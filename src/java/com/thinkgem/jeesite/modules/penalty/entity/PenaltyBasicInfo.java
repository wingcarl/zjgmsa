/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 处罚基础信息配置表Entity
 * @author dylan wang
 * @version 2017-05-09
 */
public class PenaltyBasicInfo extends DataEntity<PenaltyBasicInfo> {
	
	private static final long serialVersionUID = 1L;
	private String reason;		// 处罚类别
	private String fullReason;		// 案由
	private String violationRegulation;		// 违法条款
	private String punishBasis;		// 处罚依据
	private String punishBasisLighten;		// 从轻处罚依据
	private String punishBasisHeavy;		// 从重处罚依据
	private String illegalCode;		// 违章代码
	private String illegalPoint;		// 违法计分
	private String illegalDetail;		// 违章事实
	
	public PenaltyBasicInfo() {
		super();
	}

	public PenaltyBasicInfo(String id){
		super(id);
	}

	@Length(min=0, max=128, message="处罚类别长度必须介于 0 和 128 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=128, message="案由长度必须介于 0 和 128 之间")
	public String getFullReason() {
		return fullReason;
	}

	public void setFullReason(String fullReason) {
		this.fullReason = fullReason;
	}
	
	@Length(min=0, max=512, message="违法条款长度必须介于 0 和 512 之间")
	public String getViolationRegulation() {
		return violationRegulation;
	}

	public void setViolationRegulation(String violationRegulation) {
		this.violationRegulation = violationRegulation;
	}
	
	@Length(min=0, max=512, message="处罚依据长度必须介于 0 和 512 之间")
	public String getPunishBasis() {
		return punishBasis;
	}

	public void setPunishBasis(String punishBasis) {
		this.punishBasis = punishBasis;
	}
	
	@Length(min=0, max=512, message="从轻处罚依据长度必须介于 0 和 512 之间")
	public String getPunishBasisLighten() {
		return punishBasisLighten;
	}

	public void setPunishBasisLighten(String punishBasisLighten) {
		this.punishBasisLighten = punishBasisLighten;
	}
	
	@Length(min=0, max=512, message="从重处罚依据长度必须介于 0 和 512 之间")
	public String getPunishBasisHeavy() {
		return punishBasisHeavy;
	}

	public void setPunishBasisHeavy(String punishBasisHeavy) {
		this.punishBasisHeavy = punishBasisHeavy;
	}
	
	@Length(min=0, max=11, message="违章代码长度必须介于 0 和 11 之间")
	public String getIllegalCode() {
		return illegalCode;
	}

	public void setIllegalCode(String illegalCode) {
		this.illegalCode = illegalCode;
	}
	
	@Length(min=0, max=11, message="违法计分长度必须介于 0 和 11 之间")
	public String getIllegalPoint() {
		return illegalPoint;
	}

	public void setIllegalPoint(String illegalPoint) {
		this.illegalPoint = illegalPoint;
	}
	
	@Length(min=0, max=512, message="违章事实长度必须介于 0 和 512 之间")
	public String getIllegalDetail() {
		return illegalDetail;
	}

	public void setIllegalDetail(String illegalDetail) {
		this.illegalDetail = illegalDetail;
	}
	
}