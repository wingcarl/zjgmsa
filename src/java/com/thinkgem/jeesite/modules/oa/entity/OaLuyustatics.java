/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 陆域巡查数据Entity
 * @author dylan wang
 * @version 2017-03-07
 */
public class OaLuyustatics extends DataEntity<OaLuyustatics> {
	
	private static final long serialVersionUID = 1L;
	private Integer cyxcrs;		// 参加巡查人数
	private Integer cdzfcl;		// 出动执法车辆
	private Integer cdzfct;		// 出动执法船艇
	private Integer jccb;		// 检查船舶
	private Integer dkmt;		// 客汽渡码头\渡运区域
	private Integer wxpmt;		// 危险品码头
	private Integer sgzyq;		// 水工作业区
	private Integer mdtbq;		// 锚地\停泊区
	private Integer qtmt;		// 其他码头
	private Integer ccwfxw;		// 查处违法行为
	private Integer czwt;		// 发现存在问题或不足
	private Integer xzcf;		// 实施行政处罚
	private Integer zhzxfk;		// 向指挥中心反馈
	private String xcfx;		// 巡查发现或缺陷简况
	private String beizhu;		// 备注
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Office create_dept; //创建部门

	public OaLuyustatics() {
		super();
	}

	public OaLuyustatics(String id){
		super(id);
	}

	public Integer getCyxcrs() {
		return cyxcrs;
	}

	public void setCyxcrs(Integer cyxcrs) {
		this.cyxcrs = cyxcrs;
	}
	
	public Integer getCdzfcl() {
		return cdzfcl;
	}

	public void setCdzfcl(Integer cdzfcl) {
		this.cdzfcl = cdzfcl;
	}
	
	public Integer getCdzfct() {
		return cdzfct;
	}

	public void setCdzfct(Integer cdzfct) {
		this.cdzfct = cdzfct;
	}
	
	public Integer getJccb() {
		return jccb;
	}

	public void setJccb(Integer jccb) {
		this.jccb = jccb;
	}
	
	public Integer getDkmt() {
		return dkmt;
	}

	public void setDkmt(Integer dkmt) {
		this.dkmt = dkmt;
	}
	
	public Integer getWxpmt() {
		return wxpmt;
	}

	public void setWxpmt(Integer wxpmt) {
		this.wxpmt = wxpmt;
	}
	
	public Integer getSgzyq() {
		return sgzyq;
	}

	public void setSgzyq(Integer sgzyq) {
		this.sgzyq = sgzyq;
	}
	
	public Integer getMdtbq() {
		return mdtbq;
	}

	public void setMdtbq(Integer mdtbq) {
		this.mdtbq = mdtbq;
	}
	
	public Integer getQtmt() {
		return qtmt;
	}

	public void setQtmt(Integer qtmt) {
		this.qtmt = qtmt;
	}
	
	public Integer getCcwfxw() {
		return ccwfxw;
	}

	public void setCcwfxw(Integer ccwfxw) {
		this.ccwfxw = ccwfxw;
	}
	
	public Integer getCzwt() {
		return czwt;
	}

	public void setCzwt(Integer czwt) {
		this.czwt = czwt;
	}
	
	public Integer getXzcf() {
		return xzcf;
	}

	public void setXzcf(Integer xzcf) {
		this.xzcf = xzcf;
	}
	
	public Integer getZhzxfk() {
		return zhzxfk;
	}

	public void setZhzxfk(Integer zhzxfk) {
		this.zhzxfk = zhzxfk;
	}
	
	@Length(min=0, max=1024, message="巡查发现或缺陷简况长度必须介于 0 和 1024 之间")
	public String getXcfx() {
		return xcfx;
	}

	public void setXcfx(String xcfx) {
		this.xcfx = xcfx;
	}
	
	@Length(min=0, max=1024, message="备注长度必须介于 0 和 1024 之间")
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Office getCreate_dept() {
		return create_dept;
	}

	public void setCreate_dept(Office create_dept) {
		this.create_dept = create_dept;
	}	
}