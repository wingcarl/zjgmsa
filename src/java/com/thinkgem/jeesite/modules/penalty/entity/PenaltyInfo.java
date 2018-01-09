/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 处罚数据登记表Entity
 * @author dylan wang
 * @version 2017-05-08
 */
public class PenaltyInfo extends DataEntity<PenaltyInfo> {
	
	private static final long serialVersionUID = 1L;
	private String shipName;		// 船名
	private String shipPort;		// 船籍港
	private String shipType;		// 船舶种类
	private String shipLoad;		// 船舶总吨
	private String cs;
	private String shipPower;		// 主机功率
	private String shipLength;		// 船舶长度
	private String shipRegist;		// 船舶登记号码
	private String shipOwner;		// 船舶所有人
	private String shipTransactor;		// 船舶经营人
	private String shipOwnerLoc;		// 船舶所有人地址
	private String shipTransactorLoc;		// 船舶经营人地址
	private String departurePort;		// 始发港
	private String destinationPort;		// 目的港
	private Date illegalHappenDate;		// 违法行为发生日期
	private String illegalHappenLoc;		// 违法行为发生地点
	private String dealerName;		// 处理人
	private String idcardNum;		// 身份证号
	private String contactAddress;		// 联系地址
	private String contactTelephone;		// 联系电话
	private String officer1;		// 执法人员1
	private String officer2;		// 执法人员2
	private Date fileCreateDate;		// 案卷创建时间
	private String fileNum;		// 案号
	private String penaltySeverity;		// 处罚轻重
	private String penaltyReason;		// 案由种类
	private String penaltyMoney;		// 处罚金额
	private String fullReason;		// 完整案由
	private String violationRegulation;		// 违反条例
	private String punishBasis;		// 处罚依据
	private String retrogradeWay;		// 逆行方式
	private String retroBeginPos;		// 逆行开始位置
	private String retroEndPos;		// 逆行结束位置
	private String realFreeboard;		// 实际干舷
	private String aFreeboard;		// A级航区核定干舷
	private String illegalCode;		// 违法处置代码
	private String illegalPoint;		// 违法计分
	private String shipArea;		// 内河/海船
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public PenaltyInfo() {
		super();
	}

	public PenaltyInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="船名长度必须介于 0 和 64 之间")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@Length(min=0, max=64, message="船籍港长度必须介于 0 和 64 之间")
	public String getShipPort() {
		return shipPort;
	}

	public void setShipPort(String shipPort) {
		this.shipPort = shipPort;
	}
	
	@Length(min=0, max=64, message="船舶种类长度必须介于 0 和 64 之间")
	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	
	@Length(min=0, max=64, message="船舶总吨长度必须介于 0 和 64 之间")
	public String getShipLoad() {
		return shipLoad;
	}

	public void setShipLoad(String shipLoad) {
		this.shipLoad = shipLoad;
	}
	
	@Length(min=0, max=64, message="主机功率长度必须介于 0 和 64 之间")
	public String getShipPower() {
		return shipPower;
	}

	public void setShipPower(String shipPower) {
		this.shipPower = shipPower;
	}
	
	@Length(min=0, max=64, message="船舶长度长度必须介于 0 和 64 之间")
	public String getShipLength() {
		return shipLength;
	}

	public void setShipLength(String shipLength) {
		this.shipLength = shipLength;
	}
	
	@Length(min=0, max=64, message="船舶登记号码长度必须介于 0 和 64 之间")
	public String getShipRegist() {
		return shipRegist;
	}

	public void setShipRegist(String shipRegist) {
		this.shipRegist = shipRegist;
	}
	
	@Length(min=0, max=256, message="船舶所有人长度必须介于 0 和 256 之间")
	public String getShipOwner() {
		return shipOwner;
	}

	public void setShipOwner(String shipOwner) {
		this.shipOwner = shipOwner;
	}
	
	@Length(min=0, max=256, message="船舶经营人长度必须介于 0 和 256 之间")
	public String getShipTransactor() {
		return shipTransactor;
	}

	public void setShipTransactor(String shipTransactor) {
		this.shipTransactor = shipTransactor;
	}
	
	@Length(min=0, max=512, message="船舶所有人地址长度必须介于 0 和 512 之间")
	public String getShipOwnerLoc() {
		return shipOwnerLoc;
	}

	public void setShipOwnerLoc(String shipOwnerLoc) {
		this.shipOwnerLoc = shipOwnerLoc;
	}
	
	@Length(min=0, max=512, message="船舶经营人地址长度必须介于 0 和 512 之间")
	public String getShipTransactorLoc() {
		return shipTransactorLoc;
	}

	public void setShipTransactorLoc(String shipTransactorLoc) {
		this.shipTransactorLoc = shipTransactorLoc;
	}
	
	@Length(min=0, max=64, message="始发港长度必须介于 0 和 64 之间")
	public String getDeparturePort() {
		return departurePort;
	}

	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}
	
	@Length(min=0, max=64, message="目的港长度必须介于 0 和 64 之间")
	public String getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIllegalHappenDate() {
		return illegalHappenDate;
	}

	public void setIllegalHappenDate(Date illegalHappenDate) {
		this.illegalHappenDate = illegalHappenDate;
	}
	
	@Length(min=0, max=256, message="违法行为发生地点长度必须介于 0 和 256 之间")
	public String getIllegalHappenLoc() {
		return illegalHappenLoc;
	}

	public void setIllegalHappenLoc(String illegalHappenLoc) {
		this.illegalHappenLoc = illegalHappenLoc;
	}
	
	@Length(min=0, max=256, message="处理人长度必须介于 0 和 256 之间")
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	@Length(min=0, max=64, message="身份证号长度必须介于 0 和 64 之间")
	public String getIdcardNum() {
		return idcardNum;
	}

	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}
	
	@Length(min=0, max=512, message="联系地址长度必须介于 0 和 512 之间")
	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}
	
	@Length(min=0, max=64, message="执法人员1长度必须介于 0 和 64 之间")
	public String getOfficer1() {
		return officer1;
	}

	public void setOfficer1(String officer1) {
		this.officer1 = officer1;
	}
	
	@Length(min=0, max=64, message="执法人员2长度必须介于 0 和 64 之间")
	public String getOfficer2() {
		return officer2;
	}

	public void setOfficer2(String officer2) {
		this.officer2 = officer2;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFileCreateDate() {
		return fileCreateDate;
	}

	public void setFileCreateDate(Date fileCreateDate) {
		this.fileCreateDate = fileCreateDate;
	}
	
	@Length(min=0, max=64, message="案号长度必须介于 0 和 64 之间")
	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	
	@Length(min=0, max=64, message="处罚轻重长度必须介于 0 和 64 之间")
	public String getPenaltySeverity() {
		return penaltySeverity;
	}

	public void setPenaltySeverity(String penaltySeverity) {
		this.penaltySeverity = penaltySeverity;
	}
	
	@Length(min=0, max=64, message="案由种类长度必须介于 0 和 64 之间")
	public String getPenaltyReason() {
		return penaltyReason;
	}

	public void setPenaltyReason(String penaltyReason) {
		this.penaltyReason = penaltyReason;
	}
	
	@Length(min=0, max=64, message="处罚金额长度必须介于 0 和 64 之间")
	public String getPenaltyMoney() {
		return penaltyMoney;
	}

	public void setPenaltyMoney(String penaltyMoney) {
		this.penaltyMoney = penaltyMoney;
	}
	
	@Length(min=0, max=64, message="完整案由长度必须介于 0 和 64 之间")
	public String getFullReason() {
		return fullReason;
	}

	public void setFullReason(String fullReason) {
		this.fullReason = fullReason;
	}
	
	@Length(min=0, max=64, message="违反条例长度必须介于 0 和 64 之间")
	public String getViolationRegulation() {
		return violationRegulation;
	}

	public void setViolationRegulation(String violationRegulation) {
		this.violationRegulation = violationRegulation;
	}
	
	@Length(min=0, max=64, message="处罚依据长度必须介于 0 和 64 之间")
	public String getPunishBasis() {
		return punishBasis;
	}

	public void setPunishBasis(String punishBasis) {
		this.punishBasis = punishBasis;
	}
	
	@Length(min=0, max=64, message="逆行方式长度必须介于 0 和 64 之间")
	public String getRetrogradeWay() {
		return retrogradeWay;
	}

	public void setRetrogradeWay(String retrogradeWay) {
		this.retrogradeWay = retrogradeWay;
	}
	
	@Length(min=0, max=64, message="逆行开始位置长度必须介于 0 和 64 之间")
	public String getRetroBeginPos() {
		return retroBeginPos;
	}

	public void setRetroBeginPos(String retroBeginPos) {
		this.retroBeginPos = retroBeginPos;
	}
	
	@Length(min=0, max=64, message="逆行结束位置长度必须介于 0 和 64 之间")
	public String getRetroEndPos() {
		return retroEndPos;
	}

	public void setRetroEndPos(String retroEndPos) {
		this.retroEndPos = retroEndPos;
	}
	
	@Length(min=0, max=64, message="实际干舷长度必须介于 0 和 64 之间")
	public String getRealFreeboard() {
		return realFreeboard;
	}

	public void setRealFreeboard(String realFreeboard) {
		this.realFreeboard = realFreeboard;
	}
	
	@Length(min=0, max=64, message="A级航区核定干舷长度必须介于 0 和 64 之间")
	public String getAFreeboard() {
		return aFreeboard;
	}

	public void setAFreeboard(String aFreeboard) {
		this.aFreeboard = aFreeboard;
	}
	
	@Length(min=0, max=32, message="违法处置代码长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=64, message="内河/海船长度必须介于 0 和 64 之间")
	public String getShipArea() {
		return shipArea;
	}

	public void setShipArea(String shipArea) {
		this.shipArea = shipArea;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getaFreeboard() {
		return aFreeboard;
	}

	public void setaFreeboard(String aFreeboard) {
		this.aFreeboard = aFreeboard;
	}
		
}