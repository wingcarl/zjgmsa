/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.flow.entity.FlowMeasureScale;
import com.thinkgem.jeesite.modules.report.entity.ReportMonthly;
import com.thinkgem.jeesite.modules.flow.dao.FlowMeasureScaleDao;

/**
 * 流量观测数据尺度Service
 * @author Dylan Wang
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class FlowMeasureScaleService extends CrudService<FlowMeasureScaleDao, FlowMeasureScale> {

	@Autowired
	FlowMeasureScaleDao flowMeasureScaleDao;
	public FlowMeasureScale get(String id) {
		return super.get(id);
	}
	
	public List<FlowMeasureScale> findList(FlowMeasureScale flowMeasureScale) {
		return super.findList(flowMeasureScale);
	}
	
	public Page<FlowMeasureScale> findPage(Page<FlowMeasureScale> page, FlowMeasureScale flowMeasureScale) {
		return super.findPage(page, flowMeasureScale);
	}
	
	@Transactional(readOnly = false)
	public void save(FlowMeasureScale flowMeasureScale) {
		super.save(flowMeasureScale);
	}
	
	@Transactional(readOnly = false)
	public void delete(FlowMeasureScale flowMeasureScale) {
		super.delete(flowMeasureScale);
	}

	@Transactional(readOnly = false)
	public void importReportFromExcel(HSSFSheet sheet) {
		FlowMeasureScale flowMeasureScale = new FlowMeasureScale();
		double date = sheet.getRow(2).getCell(4).getNumericCellValue();
    	Date d = DateUtils.toDate(date);
    	flowMeasureScale.setFlowMeasureDate(d);//设置导入表格月份时间
    	for(int i=10;i<16;i++){
    		flowMeasureScale.setSectionName(sheet.getRow(i).getCell(1).getStringCellValue());
        	flowMeasureScale.setTotal(String.valueOf(getNum(sheet,i,3)));
        	flowMeasureScale.setEquivalentWeight((String.valueOf(getNum(sheet,i,2))));
        	flowMeasureScale.setUpTotal(((String.valueOf(getNum(sheet,i,4)))));
        	flowMeasureScale.setUpLess30(((String.valueOf(getNum(sheet,i,5)))));
        	flowMeasureScale.setUpBetween3050(((String.valueOf(getNum(sheet,i,6)))));
        	flowMeasureScale.setUpBetween5090(((String.valueOf(getNum(sheet,i,7)))));
        	flowMeasureScale.setUpBetween90180(((String.valueOf(getNum(sheet,i,8)))));
        	flowMeasureScale.setUpMore180(((String.valueOf(getNum(sheet,i,9)))));
        	flowMeasureScale.setDownTotal(((String.valueOf(getNum(sheet,i,10)))));
        	flowMeasureScale.setDownLess30(((String.valueOf(getNum(sheet,i,11)))));
        	flowMeasureScale.setDownBetween3050(((String.valueOf(getNum(sheet,i,12)))));
        	flowMeasureScale.setDownBetween5090(((String.valueOf(getNum(sheet,i,13)))));
        	flowMeasureScale.setDownBetween90180(((String.valueOf(getNum(sheet,i,14)))));
        	flowMeasureScale.setDownMore180(((String.valueOf(getNum(sheet,i,15)))));
        	//flowMeasureScale.setIsNewRecord(true);
        	flowMeasureScale.setId(null);
        	this.save(flowMeasureScale);
    	}
    	

	}
	private double getNum(HSSFSheet sheet,int i,int j){
		return sheet.getRow(i).getCell(j).getNumericCellValue();
	}

	public List<FlowMeasureScale> findListBySection(FlowMeasureScale flowMeasureScale) {

		return flowMeasureScaleDao.findListBySection(flowMeasureScale);
	}

	public String getFlowValue(FlowMeasureScale scale,String[] flowType) {
		String val = "";
		double count = 0;
		if(flowType!=null){
			String[] type = flowType;
			
			for(int i=0;i<type.length;i++){
				if("1".equals(type[i]))
					count += Double.parseDouble(scale.getEquivalentWeight());
				if("2".equals(type[i]))
					count += Double.parseDouble(scale.getTotal());
				if("3".equals(type[i]))
					count += Double.parseDouble(scale.getUpTotal());
				if("4".equals(type[i]))
					count += Double.parseDouble(scale.getUpLess30());
				if("5".equals(type[i]))
					count += Double.parseDouble(scale.getUpBetween3050());
				if("6".equals(type[i]))
					count += Double.parseDouble(scale.getUpBetween5090());
				if("7".equals(type[i]))
					count += Double.parseDouble(scale.getUpBetween90180());
				if("8".equals(type[i]))
					count += Double.parseDouble(scale.getUpMore180());	
				if("9".equals(type[i]))
					count += Double.parseDouble(scale.getDownTotal());
				if("10".equals(type[i]))
					count += Double.parseDouble(scale.getDownLess30());
				if("11".equals(type[i]))
					count += Double.parseDouble(scale.getDownBetween3050());
				if("12".equals(type[i]))
					count += Double.parseDouble(scale.getDownBetween5090());
				if("13".equals(type[i]))
					count += Double.parseDouble(scale.getDownBetween90180());	
				if("14".equals(type[i]))
					count += Double.parseDouble(val = scale.getDownMore180());
			}
			val = String.valueOf(count);
		}
		if(!val.isEmpty()){
			DecimalFormat df = new DecimalFormat("######0.0");
			Double d = Double.parseDouble(val);
			df.format(d);
			val = d.toString();
		}
		
		return val;
	}

	public void setSectionNameByVal(FlowMeasureScale flowMeasureScale) {
		List<String> sectionList = new ArrayList<String>();
		if(flowMeasureScale.getSectionVal()!=null){
			String[] params = flowMeasureScale.getSectionVal();
			for(int i=0;i<params.length;i++){
				if("1".equals(params[i])){
					sectionList.add("福南观测点");
					continue;
				}
				if("2".equals(params[i])){
					sectionList.add("福北观测点");
					continue;
				}
				if("3".equals(params[i])){
					sectionList.add("福中观测点");
					continue;
				}
				if("4".equals(params[i])){
					sectionList.add("浏海沙观测点");
					continue;
				}
				if("5".equals(params[i])){
					sectionList.add("沪通大桥观测点");
					continue;
				}
				if("6".equals(params[i])){
					sectionList.add("七干河观测点");
					continue;
				}
			}
			flowMeasureScale.setSectionList(sectionList);
					
		}
		
	}
}