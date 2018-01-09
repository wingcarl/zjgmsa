/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.entity.port;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 船籍港字典信息维护Entity
 * @author dylan wang
 * @version 2017-05-23
 */
public class DictRegistryPort extends DataEntity<DictRegistryPort> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 船籍港编号
	private String name;		// 船籍港名称
	private String pinyin;		// 船籍港英文
	private String province;		// 所在省份
	
	public DictRegistryPort() {
		super();
	}

	public DictRegistryPort(String id){
		super(id);
	}

	@ExcelField(title="编码", align=2, sort=22)
	@Length(min=0, max=11, message="船籍港编号长度必须介于 0 和 11 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@ExcelField(title="船籍港", align=2, sort=25)
	@Length(min=0, max=24, message="船籍港名称长度必须介于 0 和 24 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="船籍港英文", align=2, sort=26)
	@Length(min=0, max=24, message="船籍港英文长度必须介于 0 和 24 之间")
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	@ExcelField(title="省份", align=2, sort=26)
	@Length(min=0, max=11, message="所在省份长度必须介于 0 和 11 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}