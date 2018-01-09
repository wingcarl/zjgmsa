/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 防污染配置Entity
 * @author Dylan Wang
 * @version 2017-12-21
 */
public class AntiFoulShip extends DataEntity<AntiFoulShip> {
	
	private static final long serialVersionUID = 1L;
	private String shipName;		// 船名
	private AntiFoulCompany company;		// 防污染作业企业 父类
	
	public AntiFoulShip() {
		super();
	}

	public AntiFoulShip(String id){
		super(id);
	}

	public AntiFoulShip(AntiFoulCompany company){
		this.company = company;
	}

	@Length(min=0, max=128, message="船名长度必须介于 0 和 128 之间")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@JsonIgnore
	@Length(min=0, max=64, message="防污染作业企业长度必须介于 0 和 64 之间")
	public AntiFoulCompany getCompany() {
		return company;
	}

	public void setCompany(AntiFoulCompany company) {
		this.company = company;
	}
	
}