/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.RoleListType;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.ShipType;

/**
 * 防污染配置Entity
 * @author Dylan Wang
 * @version 2017-12-21
 */
public class AntiFoulCompany extends DataEntity<AntiFoulCompany> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 企业名称
	private String foulType;		// 防污染类别
	private List<AntiFoulShip> antiFoulShipList = Lists.newArrayList();		// 子表列表
	private List<String> shipLists = Lists.newArrayList();
	public AntiFoulCompany() {
		super();
	}

	public AntiFoulCompany(String id){
		super(id);
	}

	@Length(min=0, max=128, message="企业名称长度必须介于 0 和 128 之间")
	@ExcelField(title="企业名称", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="防污染类别长度必须介于 0 和 64 之间")
	@ExcelField(title="作业类别", align=2, sort=20,dictType="pollutant_work_type")
	public String getFoulType() {
		return foulType;
	}

	public void setFoulType(String foulType) {
		this.foulType = foulType;
	}
	
	@JsonIgnore
	@ExcelField(title="拥有船舶", align=1, sort=800,fieldType=ShipType.class)
	public List<String> getShipLists(){
		return shipLists;
	}
	
	public void setShipLists(List<String> shipLists) {
		this.shipLists = shipLists;
	}

	@JsonIgnore
	public List<AntiFoulShip> getAntiFoulShipList() {
		return antiFoulShipList;
	}

	public void setAntiFoulShipList(List<AntiFoulShip> antiFoulShipList) {
		this.antiFoulShipList = antiFoulShipList;
	}
}