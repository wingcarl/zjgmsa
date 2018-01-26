/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.spring.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 春运专项数据Entity
 * @author Dylan Wang
 * @version 2018-01-26
 */
public class SpringData extends DataEntity<SpringData> {
	
	private static final long serialVersionUID = 1L;
	private String shipNum;		// 船舶数
	private Office createOffice;		// 创建部门
	private Date inputDate;		// 录入时间
	private String shipPersonNum;		// 客位数
	private String shipNumPer;		// 船舶数同期比
	private String shipPersonNumPer;		// 客位数同期比
	private String personNum;		// 完成客运量
	private String personNumPer;		// 客运量与去年同期比
	private String carNum;		// 运送车辆
	private String carNumPer;		// 车运量与去年同期比
	private String accidentNum;		// 重特大安全事故次数
	private String injuryNum;		// 伤
	private String deadNum;		// 亡
	private String missingNum;		// 失踪
	private String zfryNum;		// 参加执法人员数
	private String zfcarNum;		// 出动执法车辆
	private String zfshipNum;		// 出动执法船艇
	private String xhsj;		// 海巡艇巡航时间
	private String xhlc;		// 海巡艇巡航里程
	private String illegalNum;		// 查处违章行为
	private String xqNum;		// 辖区事故险情
	private String fbzl;		// 发放宣传材料
	private String aqxx;		// 发布安全信息
	private String summary;		// 当日春运主要工作情况简介
	private Date beginInputDate;		// 开始 录入时间
	private Date endInputDate;		// 结束 录入时间
	
	public SpringData() {
		super();
	}

	public SpringData(String id){
		super(id);
	}

	@Length(min=0, max=64, message="船舶数长度必须介于 0 和 64 之间")
	public String getShipNum() {
		return shipNum;
	}

	public void setShipNum(String shipNum) {
		this.shipNum = shipNum;
	}
	
	public Office getCreateOffice() {
		return createOffice;
	}

	public void setCreateOffice(Office createOffice) {
		this.createOffice = createOffice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	
	@Length(min=0, max=64, message="客位数长度必须介于 0 和 64 之间")
	public String getShipPersonNum() {
		return shipPersonNum;
	}

	public void setShipPersonNum(String shipPersonNum) {
		this.shipPersonNum = shipPersonNum;
	}
	
	@Length(min=0, max=64, message="船舶数同期比长度必须介于 0 和 64 之间")
	public String getShipNumPer() {
		return shipNumPer;
	}

	public void setShipNumPer(String shipNumPer) {
		this.shipNumPer = shipNumPer;
	}
	
	@Length(min=0, max=64, message="客位数同期比长度必须介于 0 和 64 之间")
	public String getShipPersonNumPer() {
		return shipPersonNumPer;
	}

	public void setShipPersonNumPer(String shipPersonNumPer) {
		this.shipPersonNumPer = shipPersonNumPer;
	}
	
	@Length(min=0, max=64, message="完成客运量长度必须介于 0 和 64 之间")
	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}
	
	@Length(min=0, max=64, message="客运量与去年同期比长度必须介于 0 和 64 之间")
	public String getPersonNumPer() {
		return personNumPer;
	}

	public void setPersonNumPer(String personNumPer) {
		this.personNumPer = personNumPer;
	}
	
	@Length(min=0, max=64, message="运送车辆长度必须介于 0 和 64 之间")
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	
	@Length(min=0, max=64, message="车运量与去年同期比长度必须介于 0 和 64 之间")
	public String getCarNumPer() {
		return carNumPer;
	}

	public void setCarNumPer(String carNumPer) {
		this.carNumPer = carNumPer;
	}
	
	@Length(min=0, max=64, message="重特大安全事故次数长度必须介于 0 和 64 之间")
	public String getAccidentNum() {
		return accidentNum;
	}

	public void setAccidentNum(String accidentNum) {
		this.accidentNum = accidentNum;
	}
	
	@Length(min=0, max=64, message="伤长度必须介于 0 和 64 之间")
	public String getInjuryNum() {
		return injuryNum;
	}

	public void setInjuryNum(String injuryNum) {
		this.injuryNum = injuryNum;
	}
	
	@Length(min=0, max=64, message="亡长度必须介于 0 和 64 之间")
	public String getDeadNum() {
		return deadNum;
	}

	public void setDeadNum(String deadNum) {
		this.deadNum = deadNum;
	}
	
	@Length(min=0, max=64, message="失踪长度必须介于 0 和 64 之间")
	public String getMissingNum() {
		return missingNum;
	}

	public void setMissingNum(String missingNum) {
		this.missingNum = missingNum;
	}
	
	@Length(min=0, max=64, message="参加执法人员数长度必须介于 0 和 64 之间")
	public String getZfryNum() {
		return zfryNum;
	}

	public void setZfryNum(String zfryNum) {
		this.zfryNum = zfryNum;
	}
	
	@Length(min=0, max=64, message="出动执法车辆长度必须介于 0 和 64 之间")
	public String getZfcarNum() {
		return zfcarNum;
	}

	public void setZfcarNum(String zfcarNum) {
		this.zfcarNum = zfcarNum;
	}
	
	@Length(min=0, max=64, message="出动执法船艇长度必须介于 0 和 64 之间")
	public String getZfshipNum() {
		return zfshipNum;
	}

	public void setZfshipNum(String zfshipNum) {
		this.zfshipNum = zfshipNum;
	}
	
	@Length(min=0, max=64, message="海巡艇巡航时间长度必须介于 0 和 64 之间")
	public String getXhsj() {
		return xhsj;
	}

	public void setXhsj(String xhsj) {
		this.xhsj = xhsj;
	}
	
	@Length(min=0, max=64, message="海巡艇巡航里程长度必须介于 0 和 64 之间")
	public String getXhlc() {
		return xhlc;
	}

	public void setXhlc(String xhlc) {
		this.xhlc = xhlc;
	}
	
	@Length(min=0, max=64, message="查处违章行为长度必须介于 0 和 64 之间")
	public String getIllegalNum() {
		return illegalNum;
	}

	public void setIllegalNum(String illegalNum) {
		this.illegalNum = illegalNum;
	}
	
	@Length(min=0, max=64, message="辖区事故险情长度必须介于 0 和 64 之间")
	public String getXqNum() {
		return xqNum;
	}

	public void setXqNum(String xqNum) {
		this.xqNum = xqNum;
	}
	
	@Length(min=0, max=64, message="发放宣传材料长度必须介于 0 和 64 之间")
	public String getFbzl() {
		return fbzl;
	}

	public void setFbzl(String fbzl) {
		this.fbzl = fbzl;
	}
	
	@Length(min=0, max=64, message="发布安全信息长度必须介于 0 和 64 之间")
	public String getAqxx() {
		return aqxx;
	}

	public void setAqxx(String aqxx) {
		this.aqxx = aqxx;
	}
	
	@Length(min=0, max=1024, message="当日春运主要工作情况简介长度必须介于 0 和 1024 之间")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Date getBeginInputDate() {
		return beginInputDate;
	}

	public void setBeginInputDate(Date beginInputDate) {
		this.beginInputDate = beginInputDate;
	}
	
	public Date getEndInputDate() {
		return endInputDate;
	}

	public void setEndInputDate(Date endInputDate) {
		this.endInputDate = endInputDate;
	}
		
}