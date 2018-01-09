package com.thinkgem.jeesite.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.modules.sys.entity.Office;

public class ZJGMSAUtils {
	public static List<Office> getOfficeList(List<Office> infoList){
		
		List<String> soffice = new ArrayList<String>();
		soffice.add("海巡执法支队");
		soffice.add("南丰办事处");
		soffice.add("保税区办事处");
		soffice.add("港区海事处");
		soffice.add("锦丰海事处");
		soffice.add("沪通大桥办事处");
		List<Office> infoList1 = new ArrayList<Office>();
		for(Office o : infoList){
			if(soffice.contains(o.getName())){
				infoList1.add(o);
			}
		}
		return infoList1;
	}
	public static List<String> getJiCeng(){
		List<String> jiceng = new ArrayList<String>();
		jiceng.add(GANGQUCHU);
		jiceng.add(ZHIDUI);
		jiceng.add(JINFENCHU);
		jiceng.add(BAOSHUIQU);
		jiceng.add(DAQIAOBAN);
		jiceng.add(NANFENCHU);
		return jiceng;
	}
	public static final String ZHIDUI = "海巡执法支队";
	public static final String GANGQUCHU = "港区海事处";
	
	public static final String JINFENCHU = "锦丰海事处";
	public static final String NANFENCHU = "南丰办事处";
	public static final String BAOSHUIQU = "保税区办事处";
	public static final String DAQIAOBAN = "沪通大桥办事处";
}
