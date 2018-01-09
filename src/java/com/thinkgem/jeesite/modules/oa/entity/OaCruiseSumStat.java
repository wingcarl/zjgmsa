package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 输出月巡航数据的VO对象
 * @author Administrator
 *
 */
public class OaCruiseSumStat {
	private static final long serialVersionUID = 1L;
	private Integer cruiseTimes = 0;//出航次数
	private Double xhsj = 0.0;//出航总时间
	private Double hxsj = 0.0;//航行总时间
	private Integer xhlc = 0;//巡航里程
	private Integer yhcs = 0;//夜航次数
	private Double yhsj = 0.0;//夜航时间
	private Integer yhlc = 0;//夜航里程
	
	private Integer zyhl = 0;
	private Integer mdtbq = 0;		// 锚地停泊区
	private Integer sgzyq = 0;		// 施工作业区
	private Integer kymt = 0;		// 客运码头、渡口
	private Integer pnq = 0;		// 抛泥区
	private Integer dangerPort = 0;		// 危险品码头、装卸作业区
	private Integer qbq = 0;		// 桥（坝）区
	private Integer other = 0;
	private Integer sumTotalForRange = 0;

	private Integer wfpf = 0;		// 发现违法排放行为
	private Integer wfhxgd = 0;		// 发现违反航行规定行为
	private Integer wfsgzy = 0;		// 发现违法施工作业行为
	private Integer zhbzyc = 0;		// 发现助航标志异常
	private Integer wfyzcs = 0;		// 发现违法养殖、采砂行为
	private Integer otherwfxw = 0;
	private Integer jtzz = 0;		// 交通组织、护航
	private Integer cyjz = 0;		// 参与救助
	private Integer bjcb = 0;
	private Integer bjry = 0;
	private Integer sumTotalForTask = 0;
	
	private Integer qtry = 0;
	private Integer cdzfry = 0;
	private Integer sumTotalForRy = 0;

	private Integer qsk = 0;		// 取水口
	private Integer ffxczl = 0;		// 发放宣传资料（份）
	private Integer psc = 0;		// 检查船舶（艘次）
	private Integer wfxwTotal = 0;
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	private Date beginHappenDate1;		// 开始 发生日期
	private Date endHappenDate1;		// 结束 发生日期
	
	public Integer getCruiseTimes() {
		return cruiseTimes;
	}
	public void setCruiseTimes(Integer cruiseTimes) {
		this.cruiseTimes = cruiseTimes;
	}
	public Double getXhsj() {
		return xhsj;
	}
	public void setXhsj(Double xhsj) {
		this.xhsj = xhsj;
	}
	public Double getHxsj() {
		return hxsj;
	}
	public void setHxsj(Double hxsj) {
		this.hxsj = hxsj;
	}
	public Integer getXhlc() {
		return xhlc;
	}
	public void setXhlc(Integer xhlc) {
		this.xhlc = xhlc;
	}
	public Integer getYhcs() {
		return yhcs;
	}
	public void setYhcs(Integer yhcs) {
		this.yhcs = yhcs;
	}
	public Double getYhsj() {
		return yhsj;
	}
	public void setYhsj(Double yhsj) {
		this.yhsj = yhsj;
	}
	public Integer getYhlc() {
		return yhlc;
	}
	public void setYhlc(Integer yhlc) {
		this.yhlc = yhlc;
	}
	public Integer getZyhl() {
		return zyhl;
	}
	public void setZyhl(Integer zyhl) {
		this.zyhl = zyhl;
	}
	public Integer getMdtbq() {
		return mdtbq;
	}
	public void setMdtbq(Integer mdtbq) {
		this.mdtbq = mdtbq;
	}
	public Integer getSgzyq() {
		return sgzyq;
	}
	public void setSgzyq(Integer sgzyq) {
		this.sgzyq = sgzyq;
	}
	public Integer getKymt() {
		return kymt;
	}
	public void setKymt(Integer kymt) {
		this.kymt = kymt;
	}
	public Integer getPnq() {
		return pnq;
	}
	public void setPnq(Integer pnq) {
		this.pnq = pnq;
	}
	public Integer getDangerPort() {
		return dangerPort;
	}
	public void setDangerPort(Integer dangerPort) {
		this.dangerPort = dangerPort;
	}
	public Integer getQbq() {
		return qbq;
	}
	public void setQbq(Integer qbq) {
		this.qbq = qbq;
	}
	public Integer getOther() {
		return other;
	}
	public void setOther(Integer other) {
		this.other = other;
	}
	public Integer getSumTotalForRange() {
		int total = zyhl+mdtbq+sgzyq+kymt+pnq+dangerPort+qbq+other;
		return total;
	}
	public void setSumTotalForRange(Integer sumTotalForRange) {
		this.sumTotalForRange = sumTotalForRange;
	}
	public Integer getWfpf() {
		return wfpf;
	}
	public void setWfpf(Integer wfpf) {
		this.wfpf = wfpf;
	}
	public Integer getWfhxgd() {
		return wfhxgd;
	}
	public void setWfhxgd(Integer wfhxgd) {
		this.wfhxgd = wfhxgd;
	}
	public Integer getWfsgzy() {
		return wfsgzy;
	}
	public void setWfsgzy(Integer wfsgzy) {
		this.wfsgzy = wfsgzy;
	}
	public Integer getZhbzyc() {
		return zhbzyc;
	}
	public void setZhbzyc(Integer zhbzyc) {
		this.zhbzyc = zhbzyc;
	}
	public Integer getWfyzcs() {
		return wfyzcs;
	}
	public void setWfyzcs(Integer wfyzcs) {
		this.wfyzcs = wfyzcs;
	}
	public Integer getOtherwfxw() {
		return otherwfxw;
	}
	public void setOtherwfxw(Integer otherwfxw) {
		this.otherwfxw = otherwfxw;
	}
	public Integer getJtzz() {
		return jtzz;
	}
	public void setJtzz(Integer jtzz) {
		this.jtzz = jtzz;
	}
	public Integer getCyjz() {
		return cyjz;
	}
	public void setCyjz(Integer cyjz) {
		this.cyjz = cyjz;
	}
	public Integer getBjcb() {
		return bjcb;
	}
	public void setBjcb(Integer bjcb) {
		this.bjcb = bjcb;
	}
	public Integer getBjry() {
		return bjry;
	}
	public void setBjry(Integer bjry) {
		this.bjry = bjry;
	}
	public Integer getSumTotalForTask() {
		int total = wfpf+wfhxgd+wfsgzy+zhbzyc+wfyzcs+otherwfxw+jtzz+cyjz+bjcb+bjry;
		return total;
	}
	public void setSumTotalForTask(Integer sumTotalForTask) {
		this.sumTotalForTask = sumTotalForTask;
	}
	public Integer getQtry() {
		return qtry;
	}
	public void setQtry(Integer qtry) {
		this.qtry = qtry;
	}
	public Integer getCdzfry() {
		return cdzfry;
	}
	public void setCdzfry(Integer cdzfry) {
		this.cdzfry = cdzfry;
	}
	public Integer getSumTotalForRy() {
		int total = (qtry==null?0:qtry) + (cdzfry==null?0:cdzfry);
		return total;
	}
	public void setSumTotalForRy(Integer sumTotalForRy) {
		this.sumTotalForRy = sumTotalForRy;
	}
	public Integer getQsk() {
		return qsk; 
	}
	public void setQsk(Integer qsk) {
		this.qsk = qsk;
	}
	public Integer getFfxczl() {
		return ffxczl;
	}
	public void setFfxczl(Integer ffxczl) {
		this.ffxczl = ffxczl;
	}
	public Integer getPsc() {
		return psc;
	}
	public void setPsc(Integer psc) {
		this.psc = psc;
	}
	public Integer getWfxwTotal() {
		int total = wfpf+wfhxgd+wfsgzy+zhbzyc+wfyzcs+otherwfxw;
		return total;
	}
	public void setWfxwTotal(Integer wfxwTotal) {
		this.wfxwTotal = wfxwTotal;
	}
	public Date getBeginHappenDate() {
		return beginHappenDate;
	}
	public void setBeginHappenDate(Date beginHappenDate) {
		this.beginHappenDate = beginHappenDate;
	}
	public Date getEndHappenDate() {
		return endHappenDate;
	}
	public void setEndHappenDate(Date endHappenDate) {
		this.endHappenDate = endHappenDate;
	}
	public Date getBeginHappenDate1() {
		return beginHappenDate1;
	}
	public void setBeginHappenDate1(Date beginHappenDate1) {
		this.beginHappenDate1 = beginHappenDate1;
	}
	public Date getEndHappenDate1() {
		return endHappenDate1;
	}
	public void setEndHappenDate1(Date endHappenDate1) {
		this.endHappenDate1 = endHappenDate1;
	}
	
	
	
	
}
