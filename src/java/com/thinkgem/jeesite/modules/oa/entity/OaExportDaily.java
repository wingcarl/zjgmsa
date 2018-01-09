package com.thinkgem.jeesite.modules.oa.entity;

import java.util.List;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class OaExportDaily {
	private Integer number = 0;
	private String hxt = "";
	private String zfry = "";
	private String xhsj = "";
	private double xhsc = 0.0;
	private String xhsy = "";
	private String fxjjwt = "";
	private String beizhu = "";
	private Integer zfrysl = 0;
	private Integer xhcs = 0;
	private double xhlc = 0.0;
	private Integer wzsl = 0;
	private Integer qsk = 0;
	private String dataId = "";

	
	@ExcelField(title="序号", align=2, sort=10)
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@ExcelField(title="海巡艇", align=2, sort=20)
	public String getHxt() {
		return hxt;
	}
	public void setHxt(String hxt) {
		this.hxt = hxt;
	}
	
	@ExcelField(title="执法人员", align=2, sort=30)
	public String getZfry() {
		return zfry;
	}
	public void setZfry(String zfry) {
		this.zfry = zfry;
	}
	
	
	@ExcelField(title="巡航时间", align=2, sort=40)
	public String getXhsj() {
		return xhsj;
	}
	public void setXhsj(String xhsj) {
		this.xhsj = xhsj;
	}
	
	
	
	@ExcelField(title="巡航时长（小时）十进制", align=2, sort=50)
	public double getXhsc() {
		return xhsc;
	}
	public void setXhsc(double xhsc) {
		this.xhsc = xhsc;
	}
	
	
	@ExcelField(title="巡航水域", align=2, sort=60)
	public String getXhsy() {
		return xhsy;
	}
	public void setXhsy(String xhsy) {
		this.xhsy = xhsy;
	}
	
	
	@ExcelField(title="发现解决的问题", align=2, sort=70)
	public String getFxjjwt() {
		return fxjjwt;
	}
	public void setFxjjwt(String fxjjwt) {
		this.fxjjwt = fxjjwt;
	}
	
	@ExcelField(title="备注", align=2, sort=80)
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	
	@ExcelField(title="执法人员数量", align=2, sort=90)
	public Integer getZfrysl() {
		return zfrysl;
	}
	public void setZfrysl(Integer zfrysl) {
		this.zfrysl = zfrysl;
	}
	
	
	@ExcelField(title="巡航次数", align=2, sort=100)
	public Integer getXhcs() {
		return xhcs;
	}
	public void setXhcs(Integer xhcs) {
		this.xhcs = xhcs;
	}
	
	
	@ExcelField(title="巡航里程（海里）", align=2, sort=110)
	public double getXhlc() {
		return xhlc;
	}
	public void setXhlc(double xhlc) {
		this.xhlc = xhlc;
	}
	
	
	@ExcelField(title="发现违章数量", align=2, sort=120)
	public Integer getWzsl() {
		return wzsl;
	}
	public void setWzsl(Integer wzsl) {
		this.wzsl = wzsl;
	}
	
	
	@ExcelField(title="取水口检查次数", align=2, sort=130)
	public Integer getQsk() {
		return qsk;
	}
	public void setQsk(Integer qsk) {
		this.qsk = qsk;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	
	
}
