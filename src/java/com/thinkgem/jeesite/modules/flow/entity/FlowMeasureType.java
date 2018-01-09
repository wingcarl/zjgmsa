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
 * 流量观测按船种分Entity
 * @author Dylan
 * @version 2017-12-15
 */
public class FlowMeasureType extends DataEntity<FlowMeasureType> {
	
	private static final long serialVersionUID = 1L;
	private String sectionName;		// 断面名称
	private String equivalentWeight;		// 当量合计
	private String total;		// 合计
	private String upTotal;		// 上行小计
	private String upPassengerShip;		// 上行客船
	private String upCargoShip;		// 上行普通货船
	private String upContainerShip;		// 上行集装箱船
	private String upDangerousShip;		// 上行危险品船
	private String upBoatTrain;		// 上行船队
	private String upFishBoat;		// 上行渔船
	private String upProjectShip;		// 上行工程船
	private String upPublicShip;		// 上行公务船
	private String upOthers;		// 上行其它船舶
	private String downTotal;		// 下行小计
	private String downPassengerShip;		// 下行客船
	private String downCargoShip;		// 下行普通货船
	private String downContainerShip;		// 下行集装箱船
	private String downDangerousShip;		// 下行危险品船
	private String downBoatTrain;		// 下行船队
	private String downFishBoat;		// 下行渔船
	private String downProjectShip;		// 下行工程船
	private String downPublicShip;		// 下行公务船
	private String downOthers;		// 下行其它船舶
	private Date flowMeasureDate;		// 观测日期
	private Date beginFlowMeasureDate;		// 开始 观测日期
	private Date endFlowMeasureDate;		// 结束 观测日期
	private String[] flowType;
	private String[] sectionVal;
	private List<String> sectionList;
	public FlowMeasureType() {
		super();
	}

	public FlowMeasureType(String id){
		super(id);
	}

	@Length(min=0, max=255, message="断面名称长度必须介于 0 和 255 之间")
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	@Length(min=0, max=64, message="当量合计长度必须介于 0 和 64 之间")
	public String getEquivalentWeight() {
		return equivalentWeight;
	}

	public void setEquivalentWeight(String equivalentWeight) {
		this.equivalentWeight = equivalentWeight;
	}
	
	@Length(min=0, max=64, message="合计长度必须介于 0 和 64 之间")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=64, message="上行小计长度必须介于 0 和 64 之间")
	public String getUpTotal() {
		return upTotal;
	}

	public void setUpTotal(String upTotal) {
		this.upTotal = upTotal;
	}
	
	@Length(min=0, max=64, message="上行客船长度必须介于 0 和 64 之间")
	public String getUpPassengerShip() {
		return upPassengerShip;
	}

	public void setUpPassengerShip(String upPassengerShip) {
		this.upPassengerShip = upPassengerShip;
	}
	
	@Length(min=0, max=64, message="上行普通货船长度必须介于 0 和 64 之间")
	public String getUpCargoShip() {
		return upCargoShip;
	}

	public void setUpCargoShip(String upCargoShip) {
		this.upCargoShip = upCargoShip;
	}
	
	@Length(min=0, max=64, message="上行集装箱船长度必须介于 0 和 64 之间")
	public String getUpContainerShip() {
		return upContainerShip;
	}

	public void setUpContainerShip(String upContainerShip) {
		this.upContainerShip = upContainerShip;
	}
	
	@Length(min=0, max=64, message="上行危险品船长度必须介于 0 和 64 之间")
	public String getUpDangerousShip() {
		return upDangerousShip;
	}

	public void setUpDangerousShip(String upDangerousShip) {
		this.upDangerousShip = upDangerousShip;
	}
	
	@Length(min=0, max=64, message="上行船队长度必须介于 0 和 64 之间")
	public String getUpBoatTrain() {
		return upBoatTrain;
	}

	public void setUpBoatTrain(String upBoatTrain) {
		this.upBoatTrain = upBoatTrain;
	}
	
	@Length(min=0, max=64, message="上行渔船长度必须介于 0 和 64 之间")
	public String getUpFishBoat() {
		return upFishBoat;
	}

	public void setUpFishBoat(String upFishBoat) {
		this.upFishBoat = upFishBoat;
	}
	
	@Length(min=0, max=64, message="上行工程船长度必须介于 0 和 64 之间")
	public String getUpProjectShip() {
		return upProjectShip;
	}

	public void setUpProjectShip(String upProjectShip) {
		this.upProjectShip = upProjectShip;
	}
	
	@Length(min=0, max=64, message="上行公务船长度必须介于 0 和 64 之间")
	public String getUpPublicShip() {
		return upPublicShip;
	}

	public void setUpPublicShip(String upPublicShip) {
		this.upPublicShip = upPublicShip;
	}
	
	@Length(min=0, max=64, message="上行其它船舶长度必须介于 0 和 64 之间")
	public String getUpOthers() {
		return upOthers;
	}

	public void setUpOthers(String upOthers) {
		this.upOthers = upOthers;
	}
	
	@Length(min=0, max=64, message="下行小计长度必须介于 0 和 64 之间")
	public String getDownTotal() {
		return downTotal;
	}

	public void setDownTotal(String downTotal) {
		this.downTotal = downTotal;
	}
	
	@Length(min=0, max=64, message="下行客船长度必须介于 0 和 64 之间")
	public String getDownPassengerShip() {
		return downPassengerShip;
	}

	public void setDownPassengerShip(String downPassengerShip) {
		this.downPassengerShip = downPassengerShip;
	}
	
	@Length(min=0, max=64, message="下行普通货船长度必须介于 0 和 64 之间")
	public String getDownCargoShip() {
		return downCargoShip;
	}

	public void setDownCargoShip(String downCargoShip) {
		this.downCargoShip = downCargoShip;
	}
	
	@Length(min=0, max=64, message="下行集装箱船长度必须介于 0 和 64 之间")
	public String getDownContainerShip() {
		return downContainerShip;
	}

	public void setDownContainerShip(String downContainerShip) {
		this.downContainerShip = downContainerShip;
	}
	
	@Length(min=0, max=64, message="下行危险品船长度必须介于 0 和 64 之间")
	public String getDownDangerousShip() {
		return downDangerousShip;
	}

	public void setDownDangerousShip(String downDangerousShip) {
		this.downDangerousShip = downDangerousShip;
	}
	
	@Length(min=0, max=64, message="下行船队长度必须介于 0 和 64 之间")
	public String getDownBoatTrain() {
		return downBoatTrain;
	}

	public void setDownBoatTrain(String downBoatTrain) {
		this.downBoatTrain = downBoatTrain;
	}
	
	@Length(min=0, max=64, message="下行渔船长度必须介于 0 和 64 之间")
	public String getDownFishBoat() {
		return downFishBoat;
	}

	public void setDownFishBoat(String downFishBoat) {
		this.downFishBoat = downFishBoat;
	}
	
	@Length(min=0, max=64, message="下行工程船长度必须介于 0 和 64 之间")
	public String getDownProjectShip() {
		return downProjectShip;
	}

	public void setDownProjectShip(String downProjectShip) {
		this.downProjectShip = downProjectShip;
	}
	
	@Length(min=0, max=64, message="下行公务船长度必须介于 0 和 64 之间")
	public String getDownPublicShip() {
		return downPublicShip;
	}

	public void setDownPublicShip(String downPublicShip) {
		this.downPublicShip = downPublicShip;
	}
	
	@Length(min=0, max=64, message="下行其它船舶长度必须介于 0 和 64 之间")
	public String getDownOthers() {
		return downOthers;
	}

	public void setDownOthers(String downOthers) {
		this.downOthers = downOthers;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFlowMeasureDate() {
		return flowMeasureDate;
	}

	public void setFlowMeasureDate(Date flowMeasureDate) {
		this.flowMeasureDate = flowMeasureDate;
	}
	
	public Date getBeginFlowMeasureDate() {
		return beginFlowMeasureDate;
	}

	public void setBeginFlowMeasureDate(Date beginFlowMeasureDate) {
		this.beginFlowMeasureDate = beginFlowMeasureDate;
	}
	
	public Date getEndFlowMeasureDate() {
		return endFlowMeasureDate;
	}

	public void setEndFlowMeasureDate(Date endFlowMeasureDate) {
		this.endFlowMeasureDate = endFlowMeasureDate;
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