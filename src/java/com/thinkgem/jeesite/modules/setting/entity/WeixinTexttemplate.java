/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信文本消息Entity
 * @author lanbiao
 * @version 2016-04-02
 */
public class WeixinTexttemplate extends DataEntity<WeixinTexttemplate> {
	
	private static final long serialVersionUID = 1L;
	private String addtime;		// 时间
	private String content;		// 内容
	private String templatename;		// 模板名称
	private String accountid;		// 微信公众号设置ID
	
	public WeixinTexttemplate() {
		super();
	}

	public WeixinTexttemplate(String id){
		super(id);
	}

	@Length(min=0, max=255, message="时间长度必须介于 0 和 255 之间")
	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	@Length(min=0, max=255, message="内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="模板名称长度必须介于 0 和 255 之间")
	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	
	@Length(min=0, max=100, message="微信公众号设置ID长度必须介于 0 和 100 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}