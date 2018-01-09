/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图文消息内容列Entity
 * @author tangchao
 * @version 2016-04-02
 */
public class WeixinNewsitem extends DataEntity<WeixinNewsitem> {
	
	private static final long serialVersionUID = 1L;
	private String newType;		// 图文类型：图文还是外部url
	private String author;		// 作者
	private String content;		// 内容
	private String description;		// 描述
	private String imagepath;		// 图片地址
	private String orders;		// 排序
	private String title;		// 标题
	private String templateid;		// 图文模板ID
	private String url;		// 外部URL
	private String accountid;		// 微信公众号设置ID
	
	public WeixinNewsitem() {
		super();
	}

	public WeixinNewsitem(String id){
		super(id);
	}

	@Length(min=0, max=255, message="图文类型：图文还是外部url长度必须介于 0 和 255 之间")
	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}
	
	@Length(min=0, max=255, message="作者长度必须介于 0 和 255 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="图片地址长度必须介于 0 和 255 之间")
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
	@Length(min=0, max=255, message="排序长度必须介于 0 和 255 之间")
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=32, message="图文模板ID长度必须介于 0 和 32 之间")
	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	
	@Length(min=0, max=255, message="外部URL长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=100, message="微信公众号设置ID长度必须介于 0 和 100 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}