/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 防污染作业Entity
 * @author Dylan Wang
 * @version 2017-12-12
 */
public class AntiFoulWork extends DataEntity<AntiFoulWork> {
	
	private static final long serialVersionUID = 1L;
	private Date receiveTime;		// 信息接收时间
	private Office office;		// 所属单位
	private String shipName;		// 作业船名
	private String shipNameName;
	private String workDep;		// 作业单位
	private String workDepName;
	private String receiveShipName;		// 接收船名
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String workLocation;		// 作业地点
	private String workType;		// 作业类别
	private String foulType;		// 污染物类别
	private String workNum;		// 作业数量(吨)
	private String exam;		// 现场抽查
	private String result;		// 处置结果
	private String dealPeople;		// 处置人员
	private Date beginReceiveTime;		// 开始 信息接收时间
	private Date endReceiveTime;		// 结束 信息接收时间
	private String dFlag;
	private String timeQuantum;
	private String workNumYouwu="0";
	private String workNumTotal;
	private String num;
	
	public AntiFoulWork() {
		super();
	}

	public String getdFlag() {
		return dFlag;
	}



	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	@ExcelField(title="总作业数量", align=2, sort=9)
	public String getWorkNumTotal() {
		try{
			if(workType.equals("21")){
				Double d = Double.parseDouble(num);
				Double d2 = Double.parseDouble(workNumYouwu);
				workNumTotal = String.valueOf(d+d2);
				return workNumTotal;
			}else{
				return num;
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}

	public void setWorkNumTotal(String workNumTotal) {
		this.workNumTotal = workNumTotal;
	}

	public void setdFlag(String dFlag) {
		this.dFlag = dFlag;
	}



	public AntiFoulWork(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HHmm")
	public Date getReceiveTime() {
		return receiveTime;
	}
	
	@ExcelField(title="信息接收时间", align=2, sort=1)
	public String getReceiveTimeStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HHmm");
		return sdf.format(receiveTime);
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	
	@ExcelField(title="所属单位", align=2, sort=2)
	@JsonIgnore
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=0, max=64, message="作业船名长度必须介于 0 和 64 之间")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@Length(min=0, max=64, message="作业单位长度必须介于 0 和 64 之间")
	public String getWorkDep() {
		return workDep;
	}

	public void setWorkDep(String workDep) {
		this.workDep = workDep;
	}
	
	@Length(min=0, max=64, message="接收船名长度必须介于 0 和 64 之间")
	@ExcelField(title="接收船名", align=2, sort=5)
	public String getReceiveShipName() {
		return receiveShipName;
	}

	public void setReceiveShipName(String receiveShipName) {
		this.receiveShipName = receiveShipName;
	}
	@ExcelField(title="作业时间", align=2, sort=5)
	public String getTimeQuantum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日HHmm");
		this.timeQuantum = sdf.format(this.getStartTime()) + "-" + sdf.format(this.getEndTime());
		return timeQuantum;
	}

	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=255, message="作业地点长度必须介于 0 和 255 之间")
	@ExcelField(title="作业地点", align=2, sort=6)
	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	
	@Length(min=0, max=64, message="作业类别长度必须介于 0 和 64 之间")
	@ExcelField(title="作业类别", align=2, sort=7,dictType="pollutant_work_type")
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}
	
	@Length(min=0, max=64, message="污染物类别长度必须介于 0 和 64 之间")
	public String getFoulType() {
		return foulType;
	}
	
	public String getFoulTypes() {
		return DictUtils.getDictLabels(foulType, "pollutant_type","");
		
	}
	public void setFoulType(String foulType) {
		this.foulType = foulType;
	}
	
	@Length(min=0, max=64, message="作业数量(吨)长度必须介于 0 和 64 之间")
	@ExcelField(title="残油作业数量", align=2, sort=9)
	public String getWorkNum() {
		if(!"21".equals(workType)){
			return "0";
		}else{
			return num;
		}

	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	
	@Length(min=0, max=64, message="现场抽查长度必须介于 0 和 64 之间")
	public String getExam() {
		return exam;
	}
	
	@ExcelField(title="现场抽查", align=2, sort=10)
	public String getExamByImage(){
		if("1".equals(exam)){
			return "√";
		}else{
			return "-";
		}
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	
	@Length(min=0, max=255, message="处置结果长度必须介于 0 和 255 之间")
	@ExcelField(title="处置结果", align=2, sort=11,dictType="pollutant_deal_result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=255, message="处置人员长度必须介于 0 和 255 之间")
	@ExcelField(title="处置人员", align=2, sort=13)
	public String getDealPeople() {
		return dealPeople;
	}

	public void setDealPeople(String dealPeople) {
		this.dealPeople = dealPeople;
	}
	
	public Date getBeginReceiveTime() {
		return beginReceiveTime;
	}

	public void setBeginReceiveTime(Date beginReceiveTime) {
		this.beginReceiveTime = beginReceiveTime;
	}
	
	public Date getEndReceiveTime() {
		return endReceiveTime;
	}

	public void setEndReceiveTime(Date endReceiveTime) {
		this.endReceiveTime = endReceiveTime;
	}
	
	@ExcelField(title="备注", align=2, sort=12)
	public String remarkBy(){
		return super.remarks;
	}
	
	@ExcelField(title="作业船名", align=2, sort=3)
	public String getShipNameName() {
		return shipNameName;
	}

	public void setShipNameName(String shipNameName) {
		this.shipNameName = shipNameName;
	}

	@ExcelField(title="作业单位", align=2, sort=3)
	public String getWorkDepName() {
		return workDepName;
	}

	public void setWorkDepName(String workDepName) {
		this.workDepName = workDepName;
	}
	@ExcelField(title="油污水作业数量", align=2, sort=9)
	public String getWorkNumYouwu() {
		return workNumYouwu;
	}

	public void setWorkNumYouwu(String workNumYouwu) {
		this.workNumYouwu = workNumYouwu;
	}
	
}