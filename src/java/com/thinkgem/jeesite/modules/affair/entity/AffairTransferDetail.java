/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.entity;

import org.codehaus.plexus.util.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 二程船详细信息表Entity
 * @author Dylan Wang
 * @version 2017-07-04
 */
public class AffairTransferDetail extends ActEntity<AffairTransferDetail> {
	
	private static final long serialVersionUID = 1L;
	private String shipName;		// 船名
	private String no;		// 运单号
	private Integer count;		// 实际数量
	private Integer totalCount;		// 累计数量
	private Date auditDate;		// 审核日期
	private AffairTransferSecond second;
	private String auditText;
	private String candidatesIds;
	private String candidatesNames;
	private String imgSrc;
	private String isArchive ="1";//判断是否已经归档
	private String archive ;
	private String isForm;
	public AffairTransferDetail() {
		super();
	}

	public AffairTransferDetail(String id){
		super(id);
	}

	public AffairTransferDetail(AffairTransferSecond second){
		this.second = second;
	}
	@Length(min=0, max=64, message="船名长度必须介于 0 和 64 之间")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@Length(min=0, max=64, message="运单号长度必须介于 0 和 64 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public AffairTransferSecond getSecond() {
		return second;
	}

	public void setSecond(AffairTransferSecond second) {
		this.second = second;
	}

	public String getAuditText() {
		return auditText;
	}

	public void setAuditText(String auditText) {
		this.auditText = auditText;
	}

	public String getCandidatesIds() {
		return candidatesIds;
	}

	public void setCandidatesIds(String candidatesIds) {
		this.candidatesIds = candidatesIds;
	}

	public String getCandidatesNames() {
		return candidatesNames;
	}

	public void setCandidatesNames(String candidatesNames) {
		this.candidatesNames = candidatesNames;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getIsArchive() {
		if("1".equals(isArchive) || "".equals(isArchive) || isArchive==null)
			return "待办理";
		else
			return "已办理";
			
	}

	public void setIsArchive(String isArchive) {
		this.isArchive = isArchive;
	}

	public String getIsForm() {
		return isForm;
	}

	public void setIsForm(String isForm) {
		this.isForm = isForm;
	}

	public String getArchive() {
		return isArchive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	
	
}