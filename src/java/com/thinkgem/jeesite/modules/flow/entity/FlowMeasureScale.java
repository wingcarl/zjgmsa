/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 流量观测数据尺度Entity
 * @author Dylan Wang
 * @version 2017-10-24
 */
public class FlowMeasureScale extends DataEntity<FlowMeasureScale> {
	
	private static final long serialVersionUID = 1L;
	private String sectionName;		// section_name
	private String equivalentWeight;		// equivalent_weight
	private String total;		// total
	private String upTotal;		// up_total
	private String upLess30;		// up_less_30
	private String upBetween3050;		// up_between_30_50
	private String upBetween5090;		// up_between_50_90
	private String upBetween90180;		// up_between_90_180
	private String upMore180;		// up_more_180
	private String downTotal;		// down_total
	private String downLess30;		// down_less_30
	private String downBetween3050;		// down_between_30_50
	private String downBetween5090;		// down_between_50_90
	private String downBetween90180;		// down_between_90_180
	private String downMore180;		// down_more_180
	private Date flowMeasureDate;		// flow_measure_date
	private Date beginFindTime;		// 开始 发现时间
	private Date endFindTime;		// 结束 发现时间
	private String[] flowType;
	private String[] sectionVal;
	private List<String> sectionList;
	public FlowMeasureScale() {
		super();
	}

	public FlowMeasureScale(String id){
		super(id);
	}

	@Length(min=0, max=255, message="section_name长度必须介于 0 和 255 之间")
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	@Length(min=0, max=64, message="equivalent_weight长度必须介于 0 和 64 之间")
	public String getEquivalentWeight() {
		return equivalentWeight;
	}

	public void setEquivalentWeight(String equivalentWeight) {
		this.equivalentWeight = equivalentWeight;
	}
	
	@Length(min=0, max=64, message="total长度必须介于 0 和 64 之间")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=64, message="up_total长度必须介于 0 和 64 之间")
	public String getUpTotal() {
		return upTotal;
	}

	public void setUpTotal(String upTotal) {
		this.upTotal = upTotal;
	}
	
	@Length(min=0, max=64, message="up_less_30长度必须介于 0 和 64 之间")
	public String getUpLess30() {
		return upLess30;
	}

	public void setUpLess30(String upLess30) {
		this.upLess30 = upLess30;
	}
	
	@Length(min=0, max=64, message="up_between_30_50长度必须介于 0 和 64 之间")
	public String getUpBetween3050() {
		return upBetween3050;
	}

	public void setUpBetween3050(String upBetween3050) {
		this.upBetween3050 = upBetween3050;
	}
	
	@Length(min=0, max=64, message="up_between_50_90长度必须介于 0 和 64 之间")
	public String getUpBetween5090() {
		return upBetween5090;
	}

	public void setUpBetween5090(String upBetween5090) {
		this.upBetween5090 = upBetween5090;
	}
	
	@Length(min=0, max=64, message="up_between_90_180长度必须介于 0 和 64 之间")
	public String getUpBetween90180() {
		return upBetween90180;
	}

	public void setUpBetween90180(String upBetween90180) {
		this.upBetween90180 = upBetween90180;
	}
	
	@Length(min=0, max=64, message="up_more_180长度必须介于 0 和 64 之间")
	public String getUpMore180() {
		return upMore180;
	}

	public void setUpMore180(String upMore180) {
		this.upMore180 = upMore180;
	}
	
	@Length(min=0, max=64, message="down_total长度必须介于 0 和 64 之间")
	public String getDownTotal() {
		return downTotal;
	}

	public void setDownTotal(String downTotal) {
		this.downTotal = downTotal;
	}
	
	@Length(min=0, max=64, message="down_less_30长度必须介于 0 和 64 之间")
	public String getDownLess30() {
		return downLess30;
	}

	public void setDownLess30(String downLess30) {
		this.downLess30 = downLess30;
	}
	
	@Length(min=0, max=64, message="down_between_30_50长度必须介于 0 和 64 之间")
	public String getDownBetween3050() {
		return downBetween3050;
	}

	public void setDownBetween3050(String downBetween3050) {
		this.downBetween3050 = downBetween3050;
	}
	
	@Length(min=0, max=64, message="down_between_50_90长度必须介于 0 和 64 之间")
	public String getDownBetween5090() {
		return downBetween5090;
	}

	public void setDownBetween5090(String downBetween5090) {
		this.downBetween5090 = downBetween5090;
	}
	
	@Length(min=0, max=64, message="down_between_90_180长度必须介于 0 和 64 之间")
	public String getDownBetween90180() {
		return downBetween90180;
	}

	public void setDownBetween90180(String downBetween90180) {
		this.downBetween90180 = downBetween90180;
	}
	
	@Length(min=0, max=64, message="down_more_180长度必须介于 0 和 64 之间")
	public String getDownMore180() {
		return downMore180;
	}

	public void setDownMore180(String downMore180) {
		this.downMore180 = downMore180;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFlowMeasureDate() {
		return flowMeasureDate;
	}

	public void setFlowMeasureDate(Date flowMeasureDate) {
		this.flowMeasureDate = flowMeasureDate;
	}

	public Date getBeginFindTime() {
		return beginFindTime;
	}

	public void setBeginFindTime(Date beginFindTime) {
		this.beginFindTime = beginFindTime;
	}

	public Date getEndFindTime() {
		return endFindTime;
	}

	public void setEndFindTime(Date endFindTime) {
		this.endFindTime = endFindTime;
	}

	
	public String[] getFlowType() {
		return flowType;
	}

	public void setFlowType(String[] flowType) {
		this.flowType = flowType;
	}



	public String[] getSectionVal() {
		return sectionVal;
	}

	public void setSectionVal(String[] sectionVal) {
		this.sectionVal = sectionVal;
	}

	public List<String> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<String> sectionList) {
		this.sectionList = sectionList;
	}
	
}