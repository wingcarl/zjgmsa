/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信公众号设置Entity
 * @author tangchao
 * @version 2016-04-02
 */
public class WeixinAccount extends DataEntity<WeixinAccount> {
	
	private static final long serialVersionUID = 1L;
	private String accountname;		// 公众帐号名称
	private String accounttoken;		// 公众帐号TOKEN
	private String accountnumber;		// 公众微信号
	private String accounttype;		// 公众号类型
	private String accountemail;		// 电子邮箱
	private String accountdesc;		// 公众帐号描述
	private String accountaccesstoken;		// ACCESS_TOKEN
	private String accountappid;		// 公众帐号APPID
	private String accountappsecret;		// 公众帐号APPSECRET
	private Date addtoekntime;		// addtoekntime
	private String username;		// username
	private String weixinAccountid;		// 原始ID
	private String apiticket;		// apiticket
	private Date apiticketttime;		// apiticketttime
	private String jsapiticket;		// jsapiticket
	private Date jsapitickettime;		// jsapitickettime
	
	public WeixinAccount() {
		super();
	}

	public WeixinAccount(String id){
		super(id);
	}

	@Length(min=0, max=200, message="公众帐号名称长度必须介于 0 和 200 之间")
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=0, max=200, message="公众帐号TOKEN长度必须介于 0 和 200 之间")
	public String getAccounttoken() {
		return accounttoken;
	}

	public void setAccounttoken(String accounttoken) {
		this.accounttoken = accounttoken;
	}
	
	@Length(min=0, max=200, message="公众微信号长度必须介于 0 和 200 之间")
	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	
	@Length(min=0, max=50, message="公众号类型长度必须介于 0 和 50 之间")
	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	
	@Length(min=0, max=200, message="电子邮箱长度必须介于 0 和 200 之间")
	public String getAccountemail() {
		return accountemail;
	}

	public void setAccountemail(String accountemail) {
		this.accountemail = accountemail;
	}
	
	@Length(min=0, max=500, message="公众帐号描述长度必须介于 0 和 500 之间")
	public String getAccountdesc() {
		return accountdesc;
	}

	public void setAccountdesc(String accountdesc) {
		this.accountdesc = accountdesc;
	}
	
	@Length(min=0, max=1000, message="ACCESS_TOKEN长度必须介于 0 和 1000 之间")
	public String getAccountaccesstoken() {
		return accountaccesstoken;
	}

	public void setAccountaccesstoken(String accountaccesstoken) {
		this.accountaccesstoken = accountaccesstoken;
	}
	
	@Length(min=0, max=200, message="公众帐号APPID长度必须介于 0 和 200 之间")
	public String getAccountappid() {
		return accountappid;
	}

	public void setAccountappid(String accountappid) {
		this.accountappid = accountappid;
	}
	
	@Length(min=0, max=500, message="公众帐号APPSECRET长度必须介于 0 和 500 之间")
	public String getAccountappsecret() {
		return accountappsecret;
	}

	public void setAccountappsecret(String accountappsecret) {
		this.accountappsecret = accountappsecret;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtoekntime() {
		return addtoekntime;
	}

	public void setAddtoekntime(Date addtoekntime) {
		this.addtoekntime = addtoekntime;
	}
	
	@Length(min=0, max=50, message="username长度必须介于 0 和 50 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=100, message="原始ID长度必须介于 0 和 100 之间")
	public String getWeixinAccountid() {
		return weixinAccountid;
	}

	public void setWeixinAccountid(String weixinAccountid) {
		this.weixinAccountid = weixinAccountid;
	}
	
	@Length(min=0, max=200, message="apiticket长度必须介于 0 和 200 之间")
	public String getApiticket() {
		return apiticket;
	}

	public void setApiticket(String apiticket) {
		this.apiticket = apiticket;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApiticketttime() {
		return apiticketttime;
	}

	public void setApiticketttime(Date apiticketttime) {
		this.apiticketttime = apiticketttime;
	}
	
	@Length(min=0, max=200, message="jsapiticket长度必须介于 0 和 200 之间")
	public String getJsapiticket() {
		return jsapiticket;
	}

	public void setJsapiticket(String jsapiticket) {
		this.jsapiticket = jsapiticket;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJsapitickettime() {
		return jsapitickettime;
	}

	public void setJsapitickettime(Date jsapitickettime) {
		this.jsapitickettime = jsapitickettime;
	}
	
}