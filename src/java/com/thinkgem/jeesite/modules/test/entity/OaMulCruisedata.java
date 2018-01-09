/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 巡航数据带字表Entity
 * @author dylan wang
 * @version 2017-02-26
 */
public class OaMulCruisedata extends DataEntity<OaMulCruisedata> {
	
	private static final long serialVersionUID = 1L;
	private String zyhl;		// zyhl
	private String mdtbq;		// mdtbq
	private String sgzyq;		// sgzyq
	private String wfhxgd;		// wfhxgd
	private String wfsgzy;		// wfsgzy
	private String kymt;		// kymt
	private String zhbzyc;		// zhbzyc
	private String yhcs;		// yhcs
	private String pnq;		// pnq
	private String wfyzcs;		// wfyzcs
	private String psc;		// psc
	private String dangerPort;		// danger_port
	private String jtzz;		// jtzz
	private String ffxczl;		// ffxczl
	private String qbq;		// qbq
	private String cyjz;		// cyjz
	private String fxwz;		// fxwz
	private String clwz;		// clwz
	private String qsk;		// qsk
	private String wfpf;		// wfpf
	private String solveProblem;		// solve_problem
	private List<OaCruisetime> oaCruisetimeList = Lists.newArrayList();		// 子表列表
	
	public OaMulCruisedata() {
		super();
	}

	public OaMulCruisedata(String id){
		super(id);
	}

	@Length(min=0, max=11, message="zyhl长度必须介于 0 和 11 之间")
	public String getZyhl() {
		return zyhl;
	}

	public void setZyhl(String zyhl) {
		this.zyhl = zyhl;
	}
	
	@Length(min=0, max=11, message="mdtbq长度必须介于 0 和 11 之间")
	public String getMdtbq() {
		return mdtbq;
	}

	public void setMdtbq(String mdtbq) {
		this.mdtbq = mdtbq;
	}
	
	@Length(min=0, max=11, message="sgzyq长度必须介于 0 和 11 之间")
	public String getSgzyq() {
		return sgzyq;
	}

	public void setSgzyq(String sgzyq) {
		this.sgzyq = sgzyq;
	}
	
	@Length(min=0, max=11, message="wfhxgd长度必须介于 0 和 11 之间")
	public String getWfhxgd() {
		return wfhxgd;
	}

	public void setWfhxgd(String wfhxgd) {
		this.wfhxgd = wfhxgd;
	}
	
	@Length(min=0, max=11, message="wfsgzy长度必须介于 0 和 11 之间")
	public String getWfsgzy() {
		return wfsgzy;
	}

	public void setWfsgzy(String wfsgzy) {
		this.wfsgzy = wfsgzy;
	}
	
	@Length(min=0, max=11, message="kymt长度必须介于 0 和 11 之间")
	public String getKymt() {
		return kymt;
	}

	public void setKymt(String kymt) {
		this.kymt = kymt;
	}
	
	@Length(min=0, max=11, message="zhbzyc长度必须介于 0 和 11 之间")
	public String getZhbzyc() {
		return zhbzyc;
	}

	public void setZhbzyc(String zhbzyc) {
		this.zhbzyc = zhbzyc;
	}
	
	@Length(min=0, max=11, message="yhcs长度必须介于 0 和 11 之间")
	public String getYhcs() {
		return yhcs;
	}

	public void setYhcs(String yhcs) {
		this.yhcs = yhcs;
	}
	
	@Length(min=0, max=11, message="pnq长度必须介于 0 和 11 之间")
	public String getPnq() {
		return pnq;
	}

	public void setPnq(String pnq) {
		this.pnq = pnq;
	}
	
	@Length(min=0, max=11, message="wfyzcs长度必须介于 0 和 11 之间")
	public String getWfyzcs() {
		return wfyzcs;
	}

	public void setWfyzcs(String wfyzcs) {
		this.wfyzcs = wfyzcs;
	}
	
	@Length(min=0, max=11, message="psc长度必须介于 0 和 11 之间")
	public String getPsc() {
		return psc;
	}

	public void setPsc(String psc) {
		this.psc = psc;
	}
	
	@Length(min=0, max=11, message="danger_port长度必须介于 0 和 11 之间")
	public String getDangerPort() {
		return dangerPort;
	}

	public void setDangerPort(String dangerPort) {
		this.dangerPort = dangerPort;
	}
	
	@Length(min=0, max=11, message="jtzz长度必须介于 0 和 11 之间")
	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	
	@Length(min=0, max=11, message="ffxczl长度必须介于 0 和 11 之间")
	public String getFfxczl() {
		return ffxczl;
	}

	public void setFfxczl(String ffxczl) {
		this.ffxczl = ffxczl;
	}
	
	@Length(min=0, max=11, message="qbq长度必须介于 0 和 11 之间")
	public String getQbq() {
		return qbq;
	}

	public void setQbq(String qbq) {
		this.qbq = qbq;
	}
	
	@Length(min=0, max=11, message="cyjz长度必须介于 0 和 11 之间")
	public String getCyjz() {
		return cyjz;
	}

	public void setCyjz(String cyjz) {
		this.cyjz = cyjz;
	}
	
	@Length(min=0, max=11, message="fxwz长度必须介于 0 和 11 之间")
	public String getFxwz() {
		return fxwz;
	}

	public void setFxwz(String fxwz) {
		this.fxwz = fxwz;
	}
	
	@Length(min=0, max=11, message="clwz长度必须介于 0 和 11 之间")
	public String getClwz() {
		return clwz;
	}

	public void setClwz(String clwz) {
		this.clwz = clwz;
	}
	
	@Length(min=0, max=11, message="qsk长度必须介于 0 和 11 之间")
	public String getQsk() {
		return qsk;
	}

	public void setQsk(String qsk) {
		this.qsk = qsk;
	}
	
	@Length(min=0, max=11, message="wfpf长度必须介于 0 和 11 之间")
	public String getWfpf() {
		return wfpf;
	}

	public void setWfpf(String wfpf) {
		this.wfpf = wfpf;
	}
	
	@Length(min=0, max=1024, message="solve_problem长度必须介于 0 和 1024 之间")
	public String getSolveProblem() {
		return solveProblem;
	}

	public void setSolveProblem(String solveProblem) {
		this.solveProblem = solveProblem;
	}
	
	public List<OaCruisetime> getOaCruisetimeList() {
		return oaCruisetimeList;
	}

	public void setOaCruisetimeList(List<OaCruisetime> oaCruisetimeList) {
		this.oaCruisetimeList = oaCruisetimeList;
	}
}