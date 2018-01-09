/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedata;

/**
 * 巡航数据带字表Entity
 * @author dylan wang
 * @version 2017-02-26
 */
public class OaCruisetime extends DataEntity<OaCruisetime> {
	
	private static final long serialVersionUID = 1L;
	private OaCruisedata oaCruisedata;		// oa_cruisedata_id 父类
	private Date startTime;		// start_time
	private Date endTime;		// end_time
	
	public OaCruisetime() {
		super();
	}

	public OaCruisetime(String id){
		super(id);
	}

	public OaCruisetime(OaCruisedata oaCruisedata){
		this.oaCruisedata = oaCruisedata;
	}

	@Length(min=0, max=64, message="oa_cruisedata_id长度必须介于 0 和64 之间")
	public OaCruisedata getOaCruisedata() {
		return oaCruisedata;
	}

	public void setOaCruisedata(OaCruisedata oaCruisedata) {
		this.oaCruisedata = oaCruisedata;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="start_time不能为空")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="end_time不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}