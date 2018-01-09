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
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.test.entity.OaCruisetime;

/**
 * 隐患排查Entity
 * @author Dylan Wang
 * @version 2017-08-31
 */
public class DangerInvestStat extends DataEntity<DangerInvest> {
	
	private static final long serialVersionUID = 1L;
	private Integer dangerSum = 0; //隐患总数
	private Integer recifyFileSum = 0; //整改通知书数量
	private Integer recifySum = 0; //已整改隐患
	private Integer noRecifySum = 0; //未整改隐患
	private Integer followTimes = 0; //跟踪检查次数
	private Office office;
	public Integer getDangerSum() {
		return dangerSum;
	}
	public void setDangerSum(Integer dangerSum) {
		this.dangerSum = dangerSum;
	}
	public Integer getRecifyFileSum() {
		return recifyFileSum;
	}
	public void setRecifyFileSum(Integer recifyFileSum) {
		this.recifyFileSum = recifyFileSum;
	}
	public Integer getRecifySum() {
		return recifySum;
	}
	public void setRecifySum(Integer recifySum) {
		this.recifySum = recifySum;
	}
	public Integer getNoRecifySum() {
		return noRecifySum;
	}
	public void setNoRecifySum(Integer noRecifySum) {
		this.noRecifySum = noRecifySum;
	}
	public Integer getFollowTimes() {
		return followTimes;
	}
	public void setFollowTimes(Integer followTimes) {
		this.followTimes = followTimes;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
		
}