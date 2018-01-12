/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 沪通大桥水工动态表Entity
 * @author Dylan Wang
 * @version 2018-01-12
 */
public class MsaHutongProject extends DataEntity<MsaHutongProject> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 沪通大桥最新水工动态
	private Date beginUpdateDate;		// 开始 更新时间
	private Date endUpdateDate;		// 结束 更新时间
	
	public MsaHutongProject() {
		super();
	}

	public MsaHutongProject(String id){
		super(id);
	}

	@Length(min=0, max=512, message="沪通大桥最新水工动态长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
}