/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 隐患信息表Entity
 * @author Dylan Wang
 * @version 2018-01-08
 */
public class DangerInfo extends DataEntity<DangerInfo> {
	
	private static final long serialVersionUID = 1L;
	private String dangerCompany;		// 排查对象
	private String dangerType;		// 隐患类别
	private String dangerDetail;		// 隐患概况描述
	private String possbility;		// 可能性级别
	private String result;		// 后果严重程度
	private String difficulty;		// 整改难度
	private String level;		// 隐患级别
	private String grade;		// 隐患评级
	private String findWay;		// 发现途径
	private Date findTime;		// 发现时间
	private Office findOffice;		// 发现部门
	private String suggestion;		// 处理意见
	private String liable;		// 直接责任人
	private String dealResult;		// 整改情况
	private Date completeTime;		// 销号时间
	private String leader;		// 单位负责人
	private Date beginFindTime;		// 开始 发现时间
	private Date endFindTime;		// 结束 发现时间
	private String isComplete;
	private String dangerCompanyName;
	private List<DangerInfoSpy> spyList = Lists.newArrayList();
	public DangerInfo() {
		super();
	}

	public DangerInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="排查对象长度必须介于 0 和 64 之间")
	public String getDangerCompany() {
		return dangerCompany;
	}

	public void setDangerCompany(String dangerCompany) {
		this.dangerCompany = dangerCompany;
	}
	
	@Length(min=0, max=64, message="隐患类别长度必须介于 0 和 64 之间")
	public String getDangerType() {
		return dangerType;
	}

	public void setDangerType(String dangerType) {
		this.dangerType = dangerType;
	}
	
	@Length(min=0, max=255, message="隐患概况描述长度必须介于 0 和 255 之间")
	public String getDangerDetail() {
		return dangerDetail;
	}

	public void setDangerDetail(String dangerDetail) {
		this.dangerDetail = dangerDetail;
	}
	
	@Length(min=0, max=64, message="可能性级别长度必须介于 0 和 64 之间")
	public String getPossbility() {
		return possbility;
	}

	public void setPossbility(String possbility) {
		this.possbility = possbility;
	}
	
	@Length(min=0, max=64, message="后果严重程度长度必须介于 0 和 64 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=64, message="整改难度长度必须介于 0 和 64 之间")
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	@Length(min=0, max=64, message="隐患级别长度必须介于 0 和 64 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Length(min=0, max=64, message="隐患评级长度必须介于 0 和 64 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=64, message="发现途径长度必须介于 0 和 64 之间")
	public String getFindWay() {
		return findWay;
	}

	public void setFindWay(String findWay) {
		this.findWay = findWay;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFindTime() {
		return findTime;
	}

	public void setFindTime(Date findTime) {
		this.findTime = findTime;
	}
	
	public Office getFindOffice() {
		return findOffice;
	}

	public void setFindOffice(Office findOffice) {
		this.findOffice = findOffice;
	}
	
	@Length(min=0, max=255, message="处理意见长度必须介于 0 和 255 之间")
	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	@Length(min=0, max=64, message="直接责任人长度必须介于 0 和 64 之间")
	public String getLiable() {
		return liable;
	}

	public void setLiable(String liable) {
		this.liable = liable;
	}
	
	@Length(min=0, max=512, message="整改情况长度必须介于 0 和 512 之间")
	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
	@Length(min=0, max=64, message="单位负责人长度必须介于 0 和 64 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
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

	public String getDangerCompanyName() {
		return dangerCompanyName;
	}

	public void setDangerCompanyName(String dangerCompanyName) {
		this.dangerCompanyName = dangerCompanyName;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public List<DangerInfoSpy> getSpyList() {
		return spyList;
	}

	public void setSpyList(List<DangerInfoSpy> spyList) {
		this.spyList = spyList;
	}
		
}