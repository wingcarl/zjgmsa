package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class OaCruiseStat extends DataEntity<OaCruiseStat>{
	private static final long serialVersionUID = 1L;
	private Integer zyhl = 0 ;		// 重要航路
	private Integer mdtbq = 0;		// 锚地停泊区
	private Integer sgzyq = 0;	// 施工作业区
	private Integer wfhxgd = 0;		// 发现违反航行规定行为
	private Integer wfsgzy = 0;		// 发现违法施工作业行为
	private Integer kymt = 0;		// 客运码头、渡口
	private Integer zhbzyc = 0;		// 发现助航标志异常
	private Integer yhcs = 0;		// 夜航（次）
	private Integer pnq = 0;		// 抛泥区
	private Integer wfyzcs = 0;		// 发现违法养殖、采砂行为
	private Integer psc = 0;		// 检查船舶（艘次）
	private Integer dangerPort = 0;		// 危险品码头、装卸作业区
	private Integer jtzz = 0;		// 交通组织、护航
	private Integer ffxczl = 0;		// 发放宣传资料（份）
	private Integer qbq = 0;		// 桥（坝）区
	private Integer cyjz = 0;		// 参与救助
	private Integer fxwz = 0;		// 海巡艇发现违章（艘次）
	private Integer clwz = 0;		// 海巡艇发现违章处理起数
	private Integer qsk = 0;		// 取水口
	private Integer wfpf = 0;		// 发现违法排放行为
	private Office create_dept; //创建部门
	private Integer xhsj = 0 ; //巡航时间
	private Integer yhsj = 0; //夜航时间
	private Integer xhlc = 0; //巡航里程
	private Integer cdzfry = 0; //出动执法人员次数
	private Integer qtry = 0;
	private Integer xhsc = 0; //巡航艘次
	private Integer qtwfxw = 0;//其他违法行为 
	private Integer jzcb = 0;
	private Integer jzry = 0;
	private Integer otherArea = 0;
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	private Date beginHappenDate1;		// 开始 发生日期
	private Date endHappenDate1;		// 结束 发生日期
	private double totalTime = 0.0;
	private double yehangTime = 0.0;
	private String name = "";
	private Integer xhcs = 0;
	private Integer yehangcs = 0;
	private String depName = ""; 
	private String cruisePeople = "";
	private String cruiseDataId = "";
	private String username;
	private Integer wfxwTotal = 0;
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
	public Integer getKymt() {
		return kymt;
	}
	public void setKymt(Integer kymt) {
		this.kymt = kymt;
	}
	public Integer getZhbzyc() {
		return zhbzyc;
	}
	public void setZhbzyc(Integer zhbzyc) {
		this.zhbzyc = zhbzyc;
	}
	public Integer getYhcs() {
		return yhcs;
	}
	public void setYhcs(Integer yhcs) {
		this.yhcs = yhcs;
	}
	public Integer getPnq() {
		return pnq;
	}
	public void setPnq(Integer pnq) {
		this.pnq = pnq;
	}
	public Integer getWfyzcs() {
		return wfyzcs;
	}
	public void setWfyzcs(Integer wfyzcs) {
		this.wfyzcs = wfyzcs;
	}
	public Integer getPsc() {
		return psc;
	}
	public void setPsc(Integer psc) {
		this.psc = psc;
	}
	public Integer getDangerPort() {
		return dangerPort;
	}
	public void setDangerPort(Integer dangerPort) {
		this.dangerPort = dangerPort;
	}
	public Integer getJtzz() {
		return jtzz;
	}
	public void setJtzz(Integer jtzz) {
		this.jtzz = jtzz;
	}
	public Integer getFfxczl() {
		return ffxczl;
	}
	public void setFfxczl(Integer ffxczl) {
		this.ffxczl = ffxczl;
	}
	public Integer getQbq() {
		return qbq;
	}
	public void setQbq(Integer qbq) {
		this.qbq = qbq;
	}
	public Integer getCyjz() {
		return cyjz;
	}
	public void setCyjz(Integer cyjz) {
		this.cyjz = cyjz;
	}
	public Integer getFxwz() {
		return fxwz;
	}
	public void setFxwz(Integer fxwz) {
		this.fxwz = fxwz;
	}
	public Integer getClwz() {
		return clwz;
	}
	public void setClwz(Integer clwz) {
		this.clwz = clwz;
	}
	public Integer getQsk() {
		return qsk;
	}
	public void setQsk(Integer qsk) {
		this.qsk = qsk;
	}
	public Integer getWfpf() {
		return wfpf;
	}
	public void setWfpf(Integer wfpf) {
		this.wfpf = wfpf;
	}
	public Office getCreate_dept() {
		return create_dept;
	}
	public void setCreate_dept(Office create_dept) {
		this.create_dept = create_dept;
	}
	public Integer getXhsj() {
		return xhsj;
	}
	public void setXhsj(Integer xhsj) {
		this.xhsj = xhsj;
	}
	public Integer getYhsj() {
		return yhsj;
	}
	public void setYhsj(Integer yhsj) {
		this.yhsj = yhsj;
	}
	public Integer getXhlc() {
		return xhlc;
	}
	public void setXhlc(Integer xhlc) {
		this.xhlc = xhlc;
	}
	public Integer getCdzfry() {
		return cdzfry;
	}
	public void setCdzfry(Integer cdzfry) {
		this.cdzfry = cdzfry;
	}
	public Integer getXhsc() {
		return xhsc;
	}
	public void setXhsc(Integer xhsc) {
		this.xhsc = xhsc;
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
	public double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getYehangTime() {
		return yehangTime;
	}
	public void setYehangTime(double yehangTime) {
		this.yehangTime = yehangTime;
	}
	public Integer getXhcs() {
		return xhcs;
	}
	public void setXhcs(Integer xhcs) {
		this.xhcs = xhcs;
	}
	public Integer getYehangcs() {
		return yehangcs;
	}
	public void setYehangcs(Integer yehangcs) {
		this.yehangcs = yehangcs;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getCruisePeople() {
		return cruisePeople;
	}
	public void setCruisePeople(String cruisePeople) {
		this.cruisePeople = cruisePeople;
	}
	public String getCruiseDataId() {
		return cruiseDataId;
	}
	public void setCruiseDataId(String cruiseDataId) {
		this.cruiseDataId = cruiseDataId;
	}
	public Integer getQtry() {
		return qtry;
	}
	public void setQtry(Integer qtry) {
		this.qtry = qtry;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getQtwfxw() {
		return qtwfxw;
	}
	public void setQtwfxw(Integer qtwfxw) {
		this.qtwfxw = qtwfxw;
	}
	public Integer getOtherArea() {
		return otherArea;
	}
	public void setOtherArea(Integer otherArea) {
		this.otherArea = otherArea;
	}
	public Integer getJzcb() {
		return jzcb;
	}
	public void setJzcb(Integer jzcb) {
		this.jzcb = jzcb;
	}
	public Integer getJzry() {
		return jzry;
	}
	public void setJzry(Integer jzry) {
		this.jzry = jzry;
	}
	public Integer getWfxwTotal() {
		return wfpf+wfhxgd+wfsgzy+zhbzyc+wfyzcs+qtwfxw;
	}
	public void setWfxwTotal(Integer wfxwTotal) {
		this.wfxwTotal = wfxwTotal;
	}
	
	
}
