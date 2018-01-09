package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulShip;
import com.thinkgem.jeesite.modules.pollute.service.AntiFoulCompanyService;

public class ShipType {
	private static AntiFoulCompanyService antiFoulCompanyService = SpringContextHolder.getBean(AntiFoulCompanyService.class);

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<String> shipList = Lists.newArrayList();
		for (String s : StringUtils.split(val, ",")){
			
			shipList.add(StringUtils.trimToEmpty(s));
			
		}
		return shipList.size()>0?shipList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<AntiFoulShip> shipList = (List<AntiFoulShip>)val;
			return Collections3.extractToString(shipList, "name", ", ");
		}
		return "";
	}
}
