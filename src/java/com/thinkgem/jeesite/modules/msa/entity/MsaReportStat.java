package com.thinkgem.jeesite.modules.msa.entity;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.thinkgem.jeesite.modules.sys.entity.Office;

public class MsaReportStat {

	private String name;
	private Integer count;
	private Integer score;
	private Integer ztdy;
	private String quater;
	private String year;
	private Date beginPublicDate;
	private Date endPublicDate;
	private String author;		// 作者
	private Office depid;	
	private String statType;
	private String bar;
	//private Integer ztdy;
	
	public static final Map<String,Integer> quote = new HashMap<String,Integer>();
	public static final Map<String,Integer> target = new HashMap<String,Integer>();
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
	public String getQuater() {
		return quater;
	}

	public void setQuater(String quater) {
		this.quater = quater;
	}


	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	public Date getBeginPublicDate() {
		return beginPublicDate;
	}

	public void setBeginPublicDate(Date beginPublicDate) {
		this.beginPublicDate = beginPublicDate;
	}

	public Date getEndPublicDate() {
		return endPublicDate;
	}

	public void setEndPublicDate(Date endPublicDate) {
		this.endPublicDate = endPublicDate;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Office getDepid() {
		return depid;
	}

	public void setDepid(Office depid) {
		this.depid = depid;
	}


	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	
	public String getBar() {
		Integer basicScore = quote.get(name);
		if(basicScore == null)
			return "";
		double percent = (double)score/(double)basicScore;
		NumberFormat fmt = NumberFormat.getPercentInstance();
		fmt.setMaximumFractionDigits(2);
		String per = fmt.format(percent);
		return score+"/"+String.valueOf(basicScore)+" "+per;

		
	}

	public void setBar(String bar) {
		this.bar = bar;
	}
	

	public Integer getZtdy() {
		return ztdy;
	}

	public void setZtdy(Integer ztdy) {
		this.ztdy = ztdy;
	}


	static{
		
		quote.put("海巡执法支队",600);
		quote.put("办公室",450);
		quote.put("保税区办事处",700);
		quote.put("指挥中心",600);
		quote.put("船舶交通管理中心",600);

		quote.put("船舶监督处",450);
		quote.put("装备信息处",100);
		quote.put("南丰办事处",700);
		quote.put("后勤管理中心",125);
		quote.put("沪通大桥办事处",700);
		quote.put("执法督察处",250);
		quote.put("通航管理处",450);
		quote.put("锦丰海事处",700);
		quote.put("港区海事处",700);
		quote.put("财务会计处",125);
		quote.put("党群工作部",450);
		quote.put("纪检监察处",100);
		quote.put("危管防污处",450);
		quote.put("政务中心",150);
		quote.put("人事教育处",100);
		target.put("海巡执法支队",15);
		target.put("办公室",20);
		target.put("保税区办事处",20);
		target.put("指挥中心",20);
		target.put("船舶交通管理中心",20);

		target.put("船舶监督处",20);
		target.put("装备信息处",4);
		target.put("南丰办事处",20);
		target.put("后勤管理中心",4);
		target.put("沪通大桥办事处",20);
		target.put("执法督察处",4);
		target.put("通航管理处",20);
		target.put("锦丰海事处",20);
		target.put("港区海事处",20);
		target.put("财务会计处",4);
		target.put("党群工作部",20);
		target.put("纪检监察处",4);
		target.put("危管防污处",20);
		target.put("政务中心",4);
		target.put("人事教育处",4);
	}
}
