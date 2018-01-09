/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 扩展接口Entity
 * @author tangchao
 * @version 2016-04-03
 */
public class WeixinExpandconfig extends DataEntity<WeixinExpandconfig> {
	
	private static final long serialVersionUID = 1L;
	private String accountid;		// 微信公众号设置ID
	private String classname;		// 功能类
	private String content;		// 功能描述
	private String keyword;		// 关键字
	private String name;		// 功能名称
	
	public WeixinExpandconfig() {
		super();
	}

	public WeixinExpandconfig(String id){
		super(id);
	}

	@Length(min=0, max=200, message="微信公众号设置ID长度必须介于 0 和 200 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=1, max=100, message="功能类长度必须介于 1 和 100 之间")
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=100, message="关键字长度必须介于 1 和 100 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=100, message="功能名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}