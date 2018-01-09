/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 政务信息爬虫获取Entity
 * @author dylan wang
 * @version 2017-05-26
 */
public class MsaReport extends DataEntity<MsaReport> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String author;		// 作者
	private String url;		// 链接
	private String readingNum;		// 阅读量
	private Office depid;		// 部门
	private Date publicDate;		// 发表日期
	private Date beginPublicDate;		// 开始 发表日期
	private Date endPublicDate;		// 结束 发表日期
	private String type;
	private List<Dict> typeList;
	
	public MsaReport() {
		super();
	}

	public MsaReport(String id){
		super(id);
	}

	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=11, message="作者长度必须介于 0 和 11 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=0, max=256, message="链接长度必须介于 0 和 256 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=11, message="阅读量长度必须介于 0 和 11 之间")
	public String getReadingNum() {
		return readingNum;
	}

	public void setReadingNum(String readingNum) {
		this.readingNum = readingNum;
	}
	
	public Office getDepid() {
		return depid;
	}

	public void setDepid(Office depid) {
		this.depid = depid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublicDate() {
		return publicDate;
	}

	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	
	public Date getBeginPublicDate() {
		return beginPublicDate;
	}

	public void setBeginPublicDate(Date beginPublicDate) {
		this.beginPublicDate = beginPublicDate;
	}
	
	public Date getEndPublicDate() {
		return endPublicDate;
	}

	public void setEndPublicDate(Date endPublicDate) {
		this.endPublicDate = endPublicDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Dict> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Dict> typeList) {
		this.typeList = typeList;
	}
		
}