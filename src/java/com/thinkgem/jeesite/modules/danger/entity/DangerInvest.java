/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.test.entity.OaCruisetime;

/**
 * 隐患排查Entity
 * @author Dylan Wang
 * @version 2017-08-31
 */
public class DangerInvest extends DataEntity<DangerInvest> {
	
	private static final long serialVersionUID = 1L;
	private String dangerName;		// 隐患名称
	private String dangerClassify;		// 隐患分类或分级建议
	private String dangerType;
	private String dangerRecify;
	private String isConfirm;
	private String againstLaw;		// 违反的法律、法规等，或其他因素可能导致水上交通事故发生的物的危险状态、人的不安全行为和管理上的缺陷
	private String consequences;		// 隐患可能产生的后果
	private String dangerCompany;		// 隐患整改责任单位
	private Office findOffice;		// 发现单位或部门
	private Date findTime;		// 发现时间
	private String dangerDetail;		// 隐患具体情况
	private List<DangerInvestDetail> dangerInvestDetailList = Lists.newArrayList();		// 子表列表

	private String dangerDifficulty;		// 整改存在的难点
	private String dealWay;		// 目前海事采取的对策
	private String isTransmit;		// 是否下达整改通知书
	private Date noticeTime;		// 通知书发放时间
	private String noticeNo;		// 通知书编号
	private String enforceMeasure;		// 采取强制措施情况
	private String rectifyTime;		// 整改时限
	private String rectifyRequire;		// 整改要求
	private String nextWay;		// 下一步工作措施
	private String isComplete;		// 整改是否已完成
	private Date completeDate;		// 整改完成时间
	private Date beginFindTime;		// 开始 发现时间
	private Date endFindTime;		// 结束 发现时间
	
	public DangerInvest() {
		super();
	}

	public DangerInvest(String id){
		super(id);
	}

	@Length(min=0, max=128, message="隐患名称长度必须介于 0 和 128 之间")
	@ExcelField(title="隐患名称", align=2, sort=1)
	public String getDangerName() {
		return dangerName;
	}

	public void setDangerName(String dangerName) {
		this.dangerName = dangerName;
	}
	
	@Length(min=0, max=64, message="隐患分类或分级建议长度必须介于 0 和 64 之间")
	@ExcelField(title="隐患分类或分级建议", align=2, sort=2,dictType="danger_classify")
	public String getDangerClassify() {
		return dangerClassify;
	}

	public void setDangerClassify(String dangerClassify) {
		this.dangerClassify = dangerClassify;
	}
	
	@Length(min=0, max=1024, message="违反的法律、法规等，或其他因素可能导致水上交通事故发生的物的危险状态、人的不安全行为和管理上的缺陷长度必须介于 0 和 1024 之间")
	@ExcelField(title="违反的法律、法规等，或其他因素可能导致水上交通事故发生的物的危险状态、人的不安全行为和管理上的缺陷", align=2, sort=3)
	public String getAgainstLaw() {
		return againstLaw;
	}

	public void setAgainstLaw(String againstLaw) {
		this.againstLaw = againstLaw;
	}
	
	@Length(min=0, max=1024, message="隐患可能产生的后果长度必须介于 0 和 1024 之间")
	@ExcelField(title="隐患可能产生的后果", align=2, sort=4)
	public String getConsequences() {
		return consequences;
	}

	public void setConsequences(String consequences) {
		this.consequences = consequences;
	}
	
	@Length(min=0, max=128, message="隐患整改责任单位长度必须介于 0 和 128 之间")
	@ExcelField(title="隐患整改责任单位", align=2, sort=4)
	public String getDangerCompany() {
		return dangerCompany;
	}

	public void setDangerCompany(String dangerCompany) {
		this.dangerCompany = dangerCompany;
	}
	
	@ExcelField(title="发现部门", align=2, sort=5)
	public Office getFindOffice() {
		return findOffice;
	}

	public void setFindOffice(Office findOffice) {
		this.findOffice = findOffice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="发现时间", align=2, sort=6)
	public Date getFindTime() {
		return findTime;
	}

	public void setFindTime(Date findTime) {
		this.findTime = findTime;
	}
	
	@Length(min=0, max=1024, message="隐患具体情况长度必须介于 0 和 1024 之间")
	public String getDangerDetail() {
		return dangerDetail;
	}

	public void setDangerDetail(String dangerDetail) {
		this.dangerDetail = dangerDetail;
	}
	
	@Length(min=0, max=1024, message="整改存在的难点长度必须介于 0 和 1024 之间")
	public String getDangerDifficulty() {
		return dangerDifficulty;
	}

	public void setDangerDifficulty(String dangerDifficulty) {
		this.dangerDifficulty = dangerDifficulty;
	}
	
	@Length(min=0, max=1024, message="目前海事采取的对策长度必须介于 0 和 1024 之间")
	public String getDealWay() {
		return dealWay;
	}

	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}
	
	@Length(min=0, max=64, message="是否下达整改通知书长度必须介于 0 和 64 之间")
	public String getIsTransmit() {
		return isTransmit;
	}

	public void setIsTransmit(String isTransmit) {
		this.isTransmit = isTransmit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@Length(min=0, max=64, message="通知书编号长度必须介于 0 和 64 之间")
	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	
	@Length(min=0, max=1024, message="采取强制措施情况长度必须介于 0 和 1024 之间")
	public String getEnforceMeasure() {
		return enforceMeasure;
	}

	public void setEnforceMeasure(String enforceMeasure) {
		this.enforceMeasure = enforceMeasure;
	}
	
	@Length(min=0, max=32, message="整改时限长度必须介于 0 和 32 之间")
	public String getRectifyTime() {
		return rectifyTime;
	}

	public void setRectifyTime(String rectifyTime) {
		this.rectifyTime = rectifyTime;
	}
	
	@Length(min=0, max=1024, message="整改要求长度必须介于 0 和 1024 之间")
	public String getRectifyRequire() {
		return rectifyRequire;
	}

	public void setRectifyRequire(String rectifyRequire) {
		this.rectifyRequire = rectifyRequire;
	}
	
	@Length(min=0, max=1024, message="下一步工作措施长度必须介于 0 和 1024 之间")
	public String getNextWay() {
		return nextWay;
	}

	public void setNextWay(String nextWay) {
		this.nextWay = nextWay;
	}
	
	@Length(min=0, max=64, message="整改是否已完成长度必须介于 0 和 64 之间")
	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
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

	public List<DangerInvestDetail> getDangerInvestDetailList() {
		return dangerInvestDetailList;
	}

	public void setDangerInvestDetailList(List<DangerInvestDetail> dangerInvestDetailList) {
		this.dangerInvestDetailList = dangerInvestDetailList;
	}

	public String getDangerType() {
		return dangerType;
	}

	public void setDangerType(String dangerType) {
		this.dangerType = dangerType;
	}

	public String getDangerRecify() {
		return dangerRecify;
	}

	public void setDangerRecify(String dangerRecify) {
		this.dangerRecify = dangerRecify;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	
		
}