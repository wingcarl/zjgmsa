/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 隐患详情Entity
 * @author Dylan Wang
 * @version 2017-08-31
 */
public class DangerInvestDetail extends DataEntity<DangerInvestDetail> {
	
	private static final long serialVersionUID = 1L;
	private String detail;		// 隐患具体情况
	private String deadtime;		// 整改时限
	private String requirement;		// 整改要求
	private String dangerName;
	private DangerInvest dangerInvest;		
	private List<DangerInvestSpy> dangerInvestSpyList = Lists.newArrayList();		// 子表列表

	public DangerInvestDetail() {
		super();
	}

	public void setDangerName(String dangerName) {
		this.dangerName = dangerName;
	}

	@Length(min=0, max=255, message="隐患具体情况长度必须介于 0 和255 之间")
	@ExcelField(title="隐患名称", align=2, sort=0)
	public String getDangerName() {
		return dangerName;
	}

	public DangerInvestDetail(DangerInvest dangerInvest){
		this.dangerInvest = dangerInvest;
	}
	public DangerInvestDetail(String id){
		super(id);
	}

	@Length(min=0, max=512, message="隐患具体情况长度必须介于 0 和 512 之间")
	@ExcelField(title="隐患具体情况", align=2, sort=7)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=32, message="整改时限长度必须介于 0 和 32 之间")
	@ExcelField(title="整改时限", align=2, sort=14)
	public String getDeadtime() {
		return deadtime;
	}

	public void setDeadtime(String deadtime) {
		this.deadtime = deadtime;
	}
	
	@Length(min=0, max=515, message="整改要求长度必须介于 0 和 515 之间")
	@ExcelField(title="整改要求", align=2, sort=15)
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public DangerInvest getDangerInvest() {
		return dangerInvest;
	}

	public void setDangerInvest(DangerInvest dangerInvest) {
		this.dangerInvest = dangerInvest;
	}

	public List<DangerInvestSpy> getDangerInvestSpyList() {
		return dangerInvestSpyList;
	}

	public void setDangerInvestSpyList(List<DangerInvestSpy> dangerInvestSpyList) {
		this.dangerInvestSpyList = dangerInvestSpyList;
	}
	
	
	
	
	@Length(min=0, max=64, message="隐患分类或分级建议长度必须介于 0 和 64 之间")
	@ExcelField(title="隐患分类或分级建议", align=2, sort=2,dictType="danger_classify")
	public String getDangerClassify() {
		return dangerInvest.getDangerClassify();
	}

	@ExcelField(title="隐患类型", align=2, sort=2,dictType="danger_type")
	public String getDangerType() {
		return dangerInvest.getDangerType();
	}

	@Length(min=0, max=1024, message="违反的法律、法规等，或其他因素可能导致水上交通事故发生的物的危险状态、人的不安全行为和管理上的缺陷长度必须介于 0 和 1024 之间")
	@ExcelField(title="违反的法律、法规等，或其他因素可能导致水上交通事故发生的物的危险状态、人的不安全行为和管理上的缺陷", align=2, sort=3)
	public String getAgainstLaw() {
		return dangerInvest.getAgainstLaw();
	}

	
	
	@Length(min=0, max=1024, message="隐患可能产生的后果长度必须介于 0 和 1024 之间")
	@ExcelField(title="隐患可能产生的后果", align=2, sort=4)
	public String getConsequences() {
		return dangerInvest.getConsequences();
	}

	
	@Length(min=0, max=128, message="隐患整改责任单位长度必须介于 0 和 128 之间")
	@ExcelField(title="隐患整改责任单位", align=2, sort=4)
	public String getDangerCompany() {
		return dangerInvest.getDangerCompany();
	}

	
	
	@ExcelField(title="发现部门", align=2, sort=5)
	public Office getFindOffice() {
		return dangerInvest.getFindOffice();
	}

	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="发现时间", align=2, sort=6)
	public Date getFindTime() {
		return dangerInvest.getFindTime();
	}

	
	
	
	@Length(min=0, max=1024, message="整改存在的难点长度必须介于 0 和 1024 之间")
	@ExcelField(title="整改存在的难点", align=2, sort=8)
	public String getDangerDifficulty() {
		return dangerInvest.getDangerDifficulty();
	}

	
	
	@Length(min=0, max=1024, message="目前海事采取的对策长度必须介于 0 和 1024 之间")
	@ExcelField(title="目前海事采取的对策", align=2, sort=9)
	public String getDealWay() {
		return dangerInvest.getDealWay();
	}

	
	@Length(min=0, max=64, message="是否下达整改通知书长度必须介于 0 和 64 之间")
	@ExcelField(title="是否下达整改通知书", align=2, sort=10,dictType="yes_no")
	public String getIsTransmit() {
		return dangerInvest.getIsTransmit();
	}

	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="通知书发放时间", align=2, sort=12)
	public Date getNoticeTime() {
		return dangerInvest.getNoticeTime();
	}

	
	@Length(min=0, max=64, message="通知书编号长度必须介于 0 和 64 之间")
	@ExcelField(title="通知书编号", align=2, sort=11)
	public String getNoticeNo() {
		return dangerInvest.getNoticeNo();
	}

	
	
	@Length(min=0, max=1024, message="采取强制措施情况长度必须介于 0 和 1024 之间")
	@ExcelField(title="采取强制措施情况", align=2, sort=13)
	public String getEnforceMeasure() {
		return dangerInvest.getEnforceMeasure();
	}

	
	
	@Length(min=0, max=32, message="整改时限长度必须介于 0 和 32 之间")
	public String getRectifyTime() {
		return dangerInvest.getRectifyTime();
	}

	
	
	@Length(min=0, max=1024, message="整改要求长度必须介于 0 和 1024 之间")
	public String getRectifyRequire() {
		return dangerInvest.getRectifyRequire();
	}

	
	
	@Length(min=0, max=1024, message="下一步工作措施长度必须介于 0 和 1024 之间")
	@ExcelField(title="下一步工作措施", align=2, sort=16)
	public String getNextWay() {
		return dangerInvest.getNextWay();
	}

	
	
	@Length(min=0, max=64, message="整改是否已完成长度必须介于 0 和 64 之间")
	@ExcelField(title="整改是否已完成", align=2, sort=17,dictType="yes_no")
	public String getIsComplete() {
		return dangerInvest.getIsComplete();
	}

	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="整改完成日期", align=2, sort=18)
	public Date getCompleteDate() {
		return dangerInvest.getCompleteDate();
	}

	
	
	


	@ExcelField(title="整改完成情况", align=2, sort=19,dictType="danger_recify")
	public String getDangerRecify() {
		return dangerInvest.getDangerRecify();
	}

	

	public String getIsConfirm() {
		return dangerInvest.getIsConfirm();
	}

	
	
}