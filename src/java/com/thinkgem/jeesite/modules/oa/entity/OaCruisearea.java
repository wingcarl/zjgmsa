/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 巡航水域增删改查Entity
 * @author 王浩宇
 * @version 2017-03-16
 */
public class OaCruisearea extends DataEntity<OaCruisearea> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 所属部门
	private String content;		// 具体内容
	private String type;		// 所属种类
	private String grid;		// 所属网格
	
	public OaCruisearea() {
		super();
	}

	public OaCruisearea(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="具体内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=11, message="所属种类长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=11, message="所属网格长度必须介于 0 和 11 之间")
	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}
	
}