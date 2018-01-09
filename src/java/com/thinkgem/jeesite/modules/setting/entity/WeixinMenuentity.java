/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 自定义菜单Entity
 * @author tangchao
 * @version 2016-04-03
 */
public class WeixinMenuentity extends DataEntity<WeixinMenuentity> {
	
	private static final long serialVersionUID = 1L;
	private String menukey;		// 菜单编号
	private String msgtype;		// 消息类型
	private String name;		// 菜单名称
	private String orders;		// 排序
	private String templateid;		// 模板ID
	private String type;		// 菜单类型
	private String url;		// url
	private String fatherid;		// 父节点ID
	private String accountid;		// 公众号设置ID
	
	public WeixinMenuentity() {
		super();
	}

	public WeixinMenuentity(String id){
		super(id);
	}

	@Length(min=0, max=255, message="菜单编号长度必须介于 0 和 255 之间")
	public String getMenukey() {
		return menukey;
	}

	public void setMenukey(String menukey) {
		this.menukey = menukey;
	}
	
	@Length(min=0, max=255, message="消息类型长度必须介于 0 和 255 之间")
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	@Length(min=0, max=255, message="菜单名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	@Length(min=0, max=255, message="模板ID长度必须介于 0 和 255 之间")
	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	
	@Length(min=0, max=255, message="菜单类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="url长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=32, message="父节点ID长度必须介于 0 和 32 之间")
	public String getFatherid() {
		return fatherid;
	}

	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	
	@Length(min=0, max=255, message="公众号设置ID长度必须介于 0 和 255 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}