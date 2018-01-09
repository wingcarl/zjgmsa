/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.intercept.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 拦截船舶情况Entity
 * @author Dylan Wang
 * @version 2017-06-16
 */
public class GridManaIntercept extends DataEntity<GridManaIntercept> {
	
	private static final long serialVersionUID = 1L;
	private Date interceptDate;		// 拦截时间
	private String shipName;		// 警戒船
	private String interceptShipName;		// 拦截船名
	private String shipLocation;		// 拦截地点
	private String dealDetail;		// 处理情况
	private Date beginInterceptDate;		// 开始 拦截时间
	private Date endInterceptDate;		// 结束 拦截时间
	
	public GridManaIntercept() {
		super();
	}

	public GridManaIntercept(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="拦截时间",  align=2, sort=1)
	public Date getInterceptDate() {
		return interceptDate;
	}

	public void setInterceptDate(Date interceptDate) {
		this.interceptDate = interceptDate;
	}
	
	@Length(min=0, max=64, message="警戒船长度必须介于 0 和 64 之间")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@Length(min=0, max=64, message="拦截船名长度必须介于 0 和 64 之间")
	@ExcelField(title="拦截船名",  align=2, sort=2)
	public String getInterceptShipName() {
		return interceptShipName;
	}

	public void setInterceptShipName(String interceptShipName) {
		this.interceptShipName = interceptShipName;
	}
	
	@Length(min=0, max=64, message="拦截地点长度必须介于 0 和 64 之间")
	@ExcelField(title="拦截地点",  align=2, sort=3)
	public String getShipLocation() {
		return shipLocation;
	}

	public void setShipLocation(String shipLocation) {
		this.shipLocation = shipLocation;
	}
	
	@Length(min=0, max=256, message="处理情况长度必须介于 0 和 256 之间")
	@ExcelField(title="处理情况",  align=2, sort=4)
	public String getDealDetail() {
		return dealDetail;
	}

	public void setDealDetail(String dealDetail) {
		this.dealDetail = dealDetail;
	}
	
	public Date getBeginInterceptDate() {
		return beginInterceptDate;
	}

	public void setBeginInterceptDate(Date beginInterceptDate) {
		this.beginInterceptDate = beginInterceptDate;
	}
	
	public Date getEndInterceptDate() {
		return endInterceptDate;
	}

	public void setEndInterceptDate(Date endInterceptDate) {
		this.endInterceptDate = endInterceptDate;
	}
		
}