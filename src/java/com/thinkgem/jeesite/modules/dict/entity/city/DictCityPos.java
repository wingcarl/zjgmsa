/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.entity.city;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 城市经纬度字典管理Entity
 * @author dylan wang
 * @version 2017-05-23
 */
public class DictCityPos extends DataEntity<DictCityPos> {
	
	private static final long serialVersionUID = 1L;
	private String province;		// 省份
	private String city;		// 地级市
	private String county;		// 区县
	private String longitude;		// 经度
	private String latitude;		// 纬度
	
	public DictCityPos() {
		super();
	}

	public DictCityPos(String id){
		super(id);
	}

	@Length(min=0, max=12, message="省份长度必须介于 0 和 12 之间")
	@ExcelField(title="省份", align=2, sort=26)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=12, message="地级市长度必须介于 0 和 12 之间")
	@ExcelField(title="地市", align=2, sort=27)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=12, message="区县长度必须介于 0 和 12 之间")
	@ExcelField(title="区县", align=2, sort=28)
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	@Length(min=0, max=12, message="经度长度必须介于 0 和 12 之间")
	@ExcelField(title="经度", align=2, sort=29)
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=12, message="纬度长度必须介于 0 和 12 之间")
	@ExcelField(title="纬度", align=2, sort=30)
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}