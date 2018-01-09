/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 水水中转货物核销登记Entity
 * @author Dylan Wang
 * @version 2017-07-02
 */
public class AffairTransferInfo extends DataEntity<AffairTransferInfo> {
	
	private static final long serialVersionUID = 1L;
	private String firstShipName;		// 一程船船名
	private String firstShipDeparture;		// 一程船始发港
	private String firstMoveNum;		// 航次号
	private String firstCargo;		// 货物
	private String firstUnloadPort;		// 卸载码头
	private String firstInOut;		// 内外贸
	private String firstLoad;		// 一程船实际载货量
	private String number;		// 序号
	private String applyOffice;		// 申请单位
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String contactor;
	private String telephone;
	private Office office;
	private List<AffairTransferSecond> affairTransferSecondList = Lists.newArrayList();		// 子表列表
	
	public AffairTransferInfo() {
		super();
	}

	public AffairTransferInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="一程船船名长度必须介于 0 和 64 之间")
	@ExcelField(title="一程船名",  align=2, sort=9)
	public String getFirstShipName() {
		return firstShipName;
	}

	public void setFirstShipName(String firstShipName) {
		this.firstShipName = firstShipName;
	}
	
	@Length(min=0, max=64, message="一程船始发港长度必须介于 0 和 64 之间")
	public String getFirstShipDeparture() {
		return firstShipDeparture;
	}

	public void setFirstShipDeparture(String firstShipDeparture) {
		this.firstShipDeparture = firstShipDeparture;
	}
	
	@Length(min=0, max=64, message="航次号长度必须介于 0 和 64 之间")
	public String getFirstMoveNum() {
		return firstMoveNum;
	}

	public void setFirstMoveNum(String firstMoveNum) {
		this.firstMoveNum = firstMoveNum;
	}
	
	@Length(min=0, max=64, message="货物长度必须介于 0 和 64 之间")
	public String getFirstCargo() {
		return firstCargo;
	}

	public void setFirstCargo(String firstCargo) {
		this.firstCargo = firstCargo;
	}
	
	@Length(min=0, max=64, message="卸载码头长度必须介于 0 和 64 之间")
	public String getFirstUnloadPort() {
		return firstUnloadPort;
	}

	public void setFirstUnloadPort(String firstUnloadPort) {
		this.firstUnloadPort = firstUnloadPort;
	}
	
	@Length(min=0, max=1, message="内外贸长度必须介于 0 和 1 之间")
	public String getFirstInOut() {
		return firstInOut;
	}

	public void setFirstInOut(String firstInOut) {
		this.firstInOut = firstInOut;
	}
	
	@Length(min=0, max=64, message="一程船实际载货量长度必须介于 0 和 64 之间")
	public String getFirstLoad() {
		return firstLoad;
	}

	public void setFirstLoad(String firstLoad) {
		this.firstLoad = firstLoad;
	}
	
	@Length(min=0, max=64, message="序号长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=64, message="申请单位长度必须介于 0 和 64 之间")
	public String getApplyOffice() {
		return applyOffice;
	}

	public void setApplyOffice(String applyOffice) {
		this.applyOffice = applyOffice;
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
		
	public List<AffairTransferSecond> getAffairTransferSecondList() {
		return affairTransferSecondList;
	}

	public void setAffairTransferSecondList(List<AffairTransferSecond> affairTransferSecondList) {
		this.affairTransferSecondList = affairTransferSecondList;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}