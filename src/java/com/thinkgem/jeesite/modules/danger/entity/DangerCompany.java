/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 隐患排查对象信息Entity
 * @author Dylan Wang
 * @version 2018-01-08
 */
public class DangerCompany extends DataEntity<DangerCompany> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 排查对象名称
	private String type;		// 排查对象类别
	private String leader;		// 责任人
	private String telephone;		// 联系电话
	private Office office;		// 所属部门
	
	public DangerCompany() {
		super();
	}

	public DangerCompany(String id){
		super(id);
	}

	@Length(min=0, max=255, message="排查对象名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="排查对象类别长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="责任人长度必须介于 0 和 64 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}