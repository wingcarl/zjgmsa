/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 欢迎语Entity
 * @author tangchao
 * @version 2016-04-03
 */
public class WeixinSubscribe extends DataEntity<WeixinSubscribe> {
	
	private static final long serialVersionUID = 1L;
	private String accountid;		// 公众号设置ID
	private String addtime;		// 时间
	private String msgtype;		// 消息类型
	private String templateid;		// 模板ID
	private String templatename;		// 模板名称
	
	public WeixinSubscribe() {
		super();
	}

	public WeixinSubscribe(String id){
		super(id);
	}

	@Length(min=0, max=255, message="公众号设置ID长度必须介于 0 和 255 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=255, message="时间长度必须介于 0 和 255 之间")
	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	@Length(min=0, max=255, message="消息类型长度必须介于 0 和 255 之间")
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	@Length(min=0, max=255, message="模板ID长度必须介于 0 和 255 之间")
	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	
	@Length(min=0, max=255, message="模板名称长度必须介于 0 和 255 之间")
	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	
}