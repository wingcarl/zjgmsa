/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 水水中转货物核销登记Entity
 * @author Dylan Wang
 * @version 2017-07-02
 */
public class AffairTransferSecond extends ActEntity<AffairTransferSecond> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 二程船预报数量
	private String destination;		// 二程船目的港
	private String auditor;		// 审核人
	private Date auditDate;		// 审核日期
	private String leafNum;		// 票号
	private String receiver;		// 票号
	private AffairTransferInfo firstId;		// 一程船ID 父类
	private Date beginAuditDate;		// 开始 审核日期
	private Date endAuditDate;		// 结束 审核日期
	private List<AffairTransferDetail> affairTransferDetailList = Lists.newArrayList();
	private String detailLength;
	
	public AffairTransferSecond() {
		super();
	}

	public AffairTransferSecond(String id){
		super(id);
	}

	public AffairTransferSecond(AffairTransferInfo firstId){
		this.firstId = firstId;
	}
	
	
	

	public void setNumber(String number) {
		this.number = number;
	}
	
	

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
	

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	

	public void setLeafNum(String leafNum) {
		this.leafNum = leafNum;
	}
	
	@Length(min=0, max=64, message="一程船ID长度必须介于 0 和 64 之间")
	public AffairTransferInfo getFirstId() {
		return firstId;
	}

	public void setFirstId(AffairTransferInfo firstId) {
		this.firstId = firstId;
	}
	
	public Date getBeginAuditDate() {
		return beginAuditDate;
	}

	public void setBeginAuditDate(Date beginAuditDate) {
		this.beginAuditDate = beginAuditDate;
	}
	
	public Date getEndAuditDate() {
		return endAuditDate;
	}

	public void setEndAuditDate(Date endAuditDate) {
		this.endAuditDate = endAuditDate;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public List<AffairTransferDetail> getAffairTransferDetailList() {
		return affairTransferDetailList;
	}

	public void setAffairTransferDetailList(List<AffairTransferDetail> affairTransferDetailList) {
		this.affairTransferDetailList = affairTransferDetailList;
	}

	public String getDetailLength() {
		Integer count = this.getAffairTransferDetailList().size();
		return count.toString();
	}

	
	@ExcelField(title="序号",  align=2, sort=1)
	public String getFirstNumber() {
		return firstId.getNumber();
	}
	@ExcelField(title="申请单位",  align=2, sort=2)
	public String getApplyOffice() {
		return firstId.getApplyOffice();
	}
	@ExcelField(title="始发港",  align=2, sort=3)
	public String FirstShipDeparture() {
		return firstId.getFirstShipDeparture();
	}	
	@ExcelField(title="航次号",  align=2, sort=4)
	public String getFirstMoveNum() {
		return firstId.getFirstMoveNum();
	}
	@ExcelField(title="货物名称",  align=2, sort=5)
	public String getFirstCargo() {
		return firstId.getFirstCargo();
	}
	@ExcelField(title="内外贸",  align=2, sort=6,dictType="in_out")
	public String getFirstInOut() {
		return firstId.getFirstInOut();
	}
	@ExcelField(title="装卸码头",  align=2, sort=7)
	public String getFirstUnloadPort() {
		return firstId.getFirstUnloadPort();
	}	
	@ExcelField(title="一程船船名",  align=2, sort=8)
	public String getFirstShipName() {
		return firstId.getFirstShipName();
	}
	@ExcelField(title="一程船实际载货量",  align=2, sort=9)
	public String getFirstLoad() {
		return firstId.getFirstLoad();
	}
	@Length(min=0, max=64, message="二程船预报数量长度必须介于 0 和 64 之间")
	@ExcelField(title="二程船预报数量",  align=2, sort=10)
	public String getNumber() {
		return number;
	}
	@Length(min=0, max=64, message="二程船目的港长度必须介于 0 和 64 之间")
	@ExcelField(title="二程船目的港",  align=2, sort=11)
	public String getDestination() {
		return destination;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="审核日期",  align=2, sort=12)
	public Date getAuditDate() {
		if(updateDate!=null)
			return updateDate;
		else 
			return createDate;
	}
	@Length(min=0, max=64, message="审核人长度必须介于 0 和 64 之间")
	@ExcelField(title="审核人",  align=2, sort=13)
	public String getAuditor() {
		if(updateBy!=null)
			return updateBy.getName();
		else 
			return "";
	}
	@Length(min=0, max=64, message="票号长度必须介于 0 和 64 之间")
	@ExcelField(title="票号",  align=2, sort=14)
	public String getLeafNum() {
		return leafNum;
	}
	
}