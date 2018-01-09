/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 每日在港船舶动态Entity
 * @author 王浩宇
 * @version 2017-03-09
 */
public class OaInportShip extends DataEntity<OaInportShip> {
	
	private static final long serialVersionUID = 1L;
	private String grid;		// 网格
	private String channel;		// 水道
	private String shipName;		// 船名
	private String shipLocation;		// 船(国)籍港
	private String shipType;		// 船舶种类
	private Double shipLength;		// 船长
	private String departurePort;		// 始发港
	private String destinationPort;		// 目的港
	private String carryCargo;		// 载货情况
	private String location;		// 具体位置
	private String workStatus;		// 作业状态
	private String inchargePeople;		// 负责人
	private String telephone;		// 联系电话
	private String constructStatus;		// 施工情况
	private Date createStartDate;
	private Date createEndDate;
	private String addDate;
	private String dep;
	private String officeId;
	
	public OaInportShip() {
		super();
	}

	public OaInportShip(String id){
		super(id);
	}

	@Length(min=0, max=64, message="网格长度必须介于 0 和 64 之间")
	@ExcelField(title="所属网格",  align=2, sort=1)
	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}
	
	@ExcelField(title="所属单位", type=1, align=2, sort=2)
	public String getDep(){
		return super.getCreateBy().getOffice().getName();
	}
	@Length(min=0, max=64, message="水道长度必须介于 0 和 64 之间")
	@ExcelField(title="所属水道",  align=2, sort=3)
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@Length(min=0, max=128, message="船名长度必须介于 0 和 128 之间")
	@ExcelField(title="船舶名称",  align=2, sort=4)
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@Length(min=0, max=64, message="船(国)籍港长度必须介于 0 和 64 之间")
	@ExcelField(title="国(船)籍港",  align=2, sort=5)
	public String getShipLocation() {
		return shipLocation;
	}

	public void setShipLocation(String shipLocation) {
		this.shipLocation = shipLocation;
	}
	
	@Length(min=0, max=64, message="船舶种类长度必须介于 0 和 64 之间")
	@ExcelField(title="船舶种类",  align=2, sort=6)
	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	
	@ExcelField(title="船舶长度",  align=2, sort=7)
	public Double getShipLength() {
		return shipLength;
	}

	public void setShipLength(Double shipLength) {
		this.shipLength = shipLength;
	}
	
	@Length(min=0, max=64, message="始发港长度必须介于 0 和 64 之间")
	@ExcelField(title="始发港",  align=2, sort=8)
	public String getDeparturePort() {
		return departurePort;
	}

	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}
	
	@Length(min=0, max=64, message="目的港长度必须介于 0 和 64 之间")
	@ExcelField(title="目的港",  align=2, sort=9)
	public String getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	
	@Length(min=0, max=64, message="载货情况长度必须介于 0 和 64 之间")
	@ExcelField(title="载货情况",  align=2, sort=10)
	public String getCarryCargo() {
		return carryCargo;
	}

	public void setCarryCargo(String carryCargo) {
		this.carryCargo = carryCargo;
	}
	
	@Length(min=0, max=64, message="作业状态长度必须介于 0 和 64 之间")
	@ExcelField(title="作业状态",  align=2, sort=11)
	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	@Length(min=0, max=64, message="具体位置长度必须介于 0 和 64 之间")
	@ExcelField(title="具体位置",  align=2, sort=12)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	@ExcelField(title="船舶代理",  align=2, sort=13)
	public String getInchargePeople() {
		return inchargePeople;
	}

	public void setInchargePeople(String inchargePeople) {
		this.inchargePeople = inchargePeople;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	@ExcelField(title="联系电话",  align=2, sort=14)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@ExcelField(title="登记时间", type=1, align=2, sort=15)
	public Date getAddDate() {
		return super.getCreateDate();
	}
	
	@Length(min=0, max=64, message="施工情况长度必须介于 0 和 64 之间")
	@ExcelField(title="备注",  align=2, sort=16)
	public String getConstructStatus() {
		return constructStatus;
	}

	public void setConstructStatus(String constructStatus) {
		this.constructStatus = constructStatus;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public Date getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(Date createStartDate) {
		this.createStartDate = createStartDate;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	


		
}