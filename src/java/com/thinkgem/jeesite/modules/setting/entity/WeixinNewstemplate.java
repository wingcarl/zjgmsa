/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图文消息Entity
 * @author tangchao
 * @version 2016-04-02
 */
public class WeixinNewstemplate extends DataEntity<WeixinNewstemplate> {
	
	private static final long serialVersionUID = 1L;
	private String addtime;		// 时间
	private String tempatename;		// 模板名称
	private String type;		// 类型
	private String accountid;		// 微信公众号设置ID
	
	public WeixinNewstemplate() {
		super();
	}

	public WeixinNewstemplate(String id){
		super(id);
	}

	@Length(min=0, max=255, message="时间长度必须介于 0 和 255 之间")
	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	@Length(min=0, max=255, message="模板名称长度必须介于 0 和 255 之间")
	public String getTempatename() {
		return tempatename;
	}

	public void setTempatename(String tempatename) {
		this.tempatename = tempatename;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="微信公众号设置ID长度必须介于 0 和 100 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}