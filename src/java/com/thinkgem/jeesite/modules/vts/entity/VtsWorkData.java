/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vts.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * VTS工作数据Entity
 * @author dylan wang
 * @version 2017-05-05
 */
public class VtsWorkData extends DataEntity<VtsWorkData> {
	
	private static final long serialVersionUID = 1L;
	private String inportLimitShip = "0";		// 到港受限船舶
	private String transitLimitShip = "0";		// 过境受限船舶
	private String positionReport = "0";		// 接受船位报告
	private String importantShip = "0";		// 重点监控船舶
	private String toDoList = "";		// 待办和已办
	private String doneList;		// done_list
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String shipReport = "0";
	private String shipFollow = "0";
	private String giantShip = "0";
	private String fourPassenger = "0";
	private String informationService = "0";
	private String trafficOperation = "0";
	private String navaid = "0";
	private String avoidDanger = "0";
	private String enforceUnion = "0";
	private String mooringSpying = "0";
	private String illegal = "0";
	

	public VtsWorkData() {
		super();
	}

	public VtsWorkData(String id){
		super(id);
	}

	@Length(min=1, max=11, message="到港受限船舶长度必须介于 1 和 11 之间")
	public String getInportLimitShip() {
		return inportLimitShip;
	}

	public void setInportLimitShip(String inportLimitShip) {
		this.inportLimitShip = inportLimitShip;
	}
	
	@Length(min=1, max=11, message="过境受限船舶长度必须介于 1 和 11 之间")
	public String getTransitLimitShip() {
		return transitLimitShip;
	}

	public void setTransitLimitShip(String transitLimitShip) {
		this.transitLimitShip = transitLimitShip;
	}
	
	@Length(min=1, max=11, message="接受船位报告长度必须介于 1 和 11 之间")
	public String getPositionReport() {
		return positionReport;
	}

	public void setPositionReport(String positionReport) {
		this.positionReport = positionReport;
	}
	
	@Length(min=1, max=11, message="重点监控船舶长度必须介于 1 和 11 之间")
	public String getImportantShip() {
		return importantShip;
	}

	public void setImportantShip(String importantShip) {
		this.importantShip = importantShip;
	}
	
	public String getToDoList() {
		return toDoList;
	}

	public void setToDoList(String toDoList) {
		this.toDoList = toDoList;
	}
	

	public String getDoneList() {
		return doneList;
	}

	public void setDoneList(String doneList) {
		this.doneList = doneList;
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

	public String getShipReport() {
		return shipReport;
	}

	public void setShipReport(String shipReport) {
		this.shipReport = shipReport;
	}

	public String getShipFollow() {
		return shipFollow;
	}

	public void setShipFollow(String shipFollow) {
		this.shipFollow = shipFollow;
	}

	public String getGiantShip() {
		return giantShip;
	}

	public void setGiantShip(String giantShip) {
		this.giantShip = giantShip;
	}

	public String getFourPassenger() {
		return fourPassenger;
	}

	public void setFourPassenger(String fourPassenger) {
		this.fourPassenger = fourPassenger;
	}

	public String getInformationService() {
		return informationService;
	}

	public void setInformationService(String informationService) {
		this.informationService = informationService;
	}

	public String getTrafficOperation() {
		return trafficOperation;
	}

	public void setTrafficOperation(String trafficOperation) {
		this.trafficOperation = trafficOperation;
	}

	public String getNavaid() {
		return navaid;
	}

	public void setNavaid(String navaid) {
		this.navaid = navaid;
	}

	public String getAvoidDanger() {
		return avoidDanger;
	}

	public void setAvoidDanger(String avoidDanger) {
		this.avoidDanger = avoidDanger;
	}

	public String getEnforceUnion() {
		return enforceUnion;
	}

	public void setEnforceDanger(String enforceUnion) {
		this.enforceUnion = enforceUnion;
	}

	public String getMooringSpying() {
		return mooringSpying;
	}

	public void setMooringSpying(String mooringSpying) {
		this.mooringSpying = mooringSpying;
	}

	public String getIllegal() {
		return illegal;
	}

	public void setIllegal(String illegal) {
		this.illegal = illegal;
	}
		
}