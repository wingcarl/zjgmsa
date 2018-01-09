package com.thinkgem.jeesite.modules.oa.entity;

import java.util.ArrayList;
import java.util.List;

public class EchartsData {
	 public List<String> legend = new ArrayList<String>();// 数据分组
	 public List<String> category = new ArrayList<String>();// 横坐标
	 public List<Series> series = new ArrayList<Series>();// 纵坐标

	 public EchartsData(List<String> legendList, List<String> categoryList,
	            List<Series> seriesList) {
	        super();
	        this.legend = legendList;
	        this.category = categoryList;
	        this.series = seriesList;
	    }
}
