/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 违章字典Entity
 * @author dylan wang
 * @version 2017-06-02
 */
public class PenaltyRuleDict extends DataEntity<PenaltyRuleDict> {
	
	private static final long serialVersionUID = 1L;
	private String cause;		// 具体案由
	private String summaryCause;		// 总体案由
	private String clause;		// 依据法规
	
	public PenaltyRuleDict() {
		super();
	}

	public PenaltyRuleDict(String id){
		super(id);
	}

	@Length(min=0, max=255, message="具体案由长度必须介于 0 和 255 之间")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	@Length(min=0, max=64, message="总体案由长度必须介于 0 和 64 之间")
	public String getSummaryCause() {
		return summaryCause;
	}

	public void setSummaryCause(String summaryCause) {
		this.summaryCause = summaryCause;
	}
	
	@Length(min=0, max=255, message="依据法规长度必须介于 0 和 255 之间")
	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}
	
}