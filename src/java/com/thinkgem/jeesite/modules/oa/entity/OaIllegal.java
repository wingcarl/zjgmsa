/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 违法行为增删改查Entity
 * @author dylanwang
 * @version 2017-02-23
 */
public class OaIllegal extends DataEntity<OaIllegal> {
	
	private static final long serialVersionUID = 1L;
	private String happenLocation;		// 发生地点
	private String illegalDetail;		// 违法行为
	private String shipName;		// 船名
	private String illegalType;
	private String beizhu;
	private Date happenDate;		// 发生日期
	private String dealWay;		// 处置结果
	private String oaCruisedataId;
	private String qtwf;
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	
	public OaIllegal() {
		super();
	}

	public OaIllegal(String id){
		super(id);
	}

	@Length(min=0, max=255, message="发生地点长度必须介于 0 和 255 之间")
	@ExcelField(title="违法行为发生地点", align=2, sort=20)
	public String getHappenLocation() {
		return happenLocation;
	}

	public void setHappenLocation(String happenLocation) {
		this.happenLocation = happenLocation;
	}
	
	@ExcelField(title="违法情况概述", align=2, sort=20,dictType="illegal_detail")
	public String getIllegalDetail() {
		return illegalDetail;
	}

	public void setIllegalDetail(String illegalDetail) {
		this.illegalDetail = illegalDetail;
	}
	
	@Length(min=0, max=64, message="船名长度必须介于 0 和 64 之间")
	@ExcelField(title="船名", align=2, sort=20)
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="违法行为发生日期", align=2, sort=20)
	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	
	@Length(min=0, max=2, message="处置结果长度必须介于 0 和 2 之间")
	@ExcelField(title="处置方式", align=2, sort=20,dictType="deal_way")
	public String getDealWay() {
		return dealWay;
	}

	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
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

	public String getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getOaCruisedataId() {
		return oaCruisedataId;
	}

	public void setOaCruisedataId(String oaCruisedataId) {
		this.oaCruisedataId = oaCruisedataId;
	}

	public String getQtwf() {
		return qtwf;
	}

	public void setQtwf(String qtwf) {
		this.qtwf = qtwf;
	}
		
}