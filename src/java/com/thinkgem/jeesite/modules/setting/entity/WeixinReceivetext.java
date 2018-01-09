/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.setting.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信接受消息Entity
 * @author tangchao
 * @version 2016-04-02
 */
public class WeixinReceivetext extends DataEntity<WeixinReceivetext> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private Date createtime;		// 时间
	private String fromusername;		// open_id
	private String msgid;		// 消息id
	private String msgtype;		// 类型
	private String rescontent;		// 回复内容
	private String response;		// 是否回复
	private String tousername;		// 接收方帐号
	private String accountid;		// 公众号ID
	private String nickname;		// 用户昵称
	
	public WeixinReceivetext() {
		super();
	}

	public WeixinReceivetext(String id){
		super(id);
	}

	@Length(min=0, max=255, message="内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=255, message="open_id长度必须介于 0 和 255 之间")
	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}
	
	@Length(min=0, max=255, message="消息id长度必须介于 0 和 255 之间")
	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	@Length(min=0, max=255, message="回复内容长度必须介于 0 和 255 之间")
	public String getRescontent() {
		return rescontent;
	}

	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}
	
	@Length(min=0, max=255, message="是否回复长度必须介于 0 和 255 之间")
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	@Length(min=0, max=255, message="接收方帐号长度必须介于 0 和 255 之间")
	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}
	
	@Length(min=0, max=100, message="公众号ID长度必须介于 0 和 100 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=200, message="用户昵称长度必须介于 0 和 200 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}