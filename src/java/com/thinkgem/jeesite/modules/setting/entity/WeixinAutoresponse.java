/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 关键字回复Entity
 * @author tangchao
 * @version 2016-04-02
 */
public class WeixinAutoresponse extends DataEntity<WeixinAutoresponse> {
	
	private static final long serialVersionUID = 1L;
	private String addtime;		// 新增时间
	private String keyword;		// 关键字
	private String msgtype;		// 内容类型
	private String rescontent;		// 内容模板ID
	private String templatename;		// 内容模板
	private String accountid;		// 公众号设置ID
	
	public WeixinAutoresponse() {
		super();
	}

	public WeixinAutoresponse(String id){
		super(id);
	}

	@Length(min=0, max=255, message="新增时间长度必须介于 0 和 255 之间")
	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	@Length(min=0, max=255, message="关键字长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=255, message="内容类型长度必须介于 0 和 255 之间")
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	@Length(min=0, max=255, message="内容模板ID长度必须介于 0 和 255 之间")
	public String getRescontent() {
		return rescontent;
	}

	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}
	
	@Length(min=0, max=255, message="内容模板长度必须介于 0 和 255 之间")
	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	
	@Length(min=0, max=100, message="公众号设置ID长度必须介于 0 和 100 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}