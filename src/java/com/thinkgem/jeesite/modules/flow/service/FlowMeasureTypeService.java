/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.flow.service;

import java.math.BigDecimal;
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
import com.thinkgem.jeesite.modules.flow.entity.FlowMeasureType;
import com.thinkgem.jeesite.modules.flow.dao.FlowMeasureTypeDao;

/**
 * 流量观测按船种分Service
 * @author Dylan
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class FlowMeasureTypeService extends CrudService<FlowMeasureTypeDao, FlowMeasureType> {
	@Autowired
	FlowMeasureTypeDao flowMeasureTypeDao;
	public FlowMeasureType get(String id) {
		return super.get(id);
	}
	
	public List<FlowMeasureType> findList(FlowMeasureType flowMeasureType) {
		return super.findList(flowMeasureType);
	}
	
	public Page<FlowMeasureType> findPage(Page<FlowMeasureType> page, FlowMeasureType flowMeasureType) {
		return super.findPage(page, flowMeasureType);
	}
	
	@Transactional(readOnly = false)
	public void save(FlowMeasureType flowMeasureType) {
		super.save(flowMeasureType);
	}
	
	@Transactional(readOnly = false)
	public void delete(FlowMeasureType flowMeasureType) {
		super.delete(flowMeasureType);
	}

	@Transactional(readOnly = false)
	public void importReportFromExcel(HSSFSheet sheet) {
		FlowMeasureType flowMeasureType = new FlowMeasureType();
		double date = sheet.getRow(2).getCell(6).getNumericCellValue();
    	Date d = DateUtils.toDate(date);
    	flowMeasureType.setFlowMeasureDate(d);//设置导入表格月份时间
    	for(int i=11;i<17;i++){
    		flowMeasureType.setSectionName(sheet.getRow(i).getCell(1).getStringCellValue());
        	flowMeasureType.setTotal(String.valueOf(getNum(sheet,i,2)));
        	flowMeasureType.setUpTotal(((String.valueOf(getNum(sheet,i,3)))));
        	flowMeasureType.setUpPassengerShip(((String.valueOf(getNum(sheet,i,4)))));
        	flowMeasureType.setUpCargoShip(((String.valueOf(getNum(sheet,i,5)))));
        	flowMeasureType.setUpContainerShip(((String.valueOf(getNum(sheet,i,6)))));

        	flowMeasureType.setUpDangerousShip(((String.valueOf(getNum(sheet,i,7)))));
        	flowMeasureType.setUpBoatTrain(((String.valueOf(getNum(sheet,i,8)))));
        	flowMeasureType.setUpFishBoat(((String.valueOf(getNum(sheet,i,9)))));
        	flowMeasureType.setUpProjectShip(((String.valueOf(getNum(sheet,i,10)))));
        	flowMeasureType.setUpPublicShip(((String.valueOf(getNum(sheet,i,11)))));
        	flowMeasureType.setUpOthers(((String.valueOf(getNum(sheet,i,12)))));

        	flowMeasureType.setDownTotal(((String.valueOf(getNum(sheet,i,13)))));
        	flowMeasureType.setDownPassengerShip(((String.valueOf(getNum(sheet,i,14)))));
        	flowMeasureType.setDownCargoShip(((String.valueOf(getNum(sheet,i,15)))));
        	flowMeasureType.setDownContainerShip(((String.valueOf(getNum(sheet,i,16)))));

        	flowMeasureType.setDownDangerousShip(((String.valueOf(getNum(sheet,i,17)))));
        	flowMeasureType.setDownBoatTrain(((String.valueOf(getNum(sheet,i,18)))));
        	flowMeasureType.setDownFishBoat(((String.valueOf(getNum(sheet,i,19)))));
        	flowMeasureType.setDownProjectShip(((String.valueOf(getNum(sheet,i,20)))));
        	flowMeasureType.setDownPublicShip(((String.valueOf(getNum(sheet,i,21)))));
        	flowMeasureType.setDownOthers(((String.valueOf(getNum(sheet,i,22)))));
        	//flowMeasureType.setIsNewRecord(true);
        	flowMeasureType.setId(null);
        	this.save(flowMeasureType);
    	}
    	

	}
	private double getNum(HSSFSheet sheet,int i,int j){
		
		double d = sheet.getRow(i).getCell(j).getNumericCellValue();
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	public List<FlowMeasureType> findListBySection(FlowMeasureType flowMeasureType) {

		return flowMeasureTypeDao.findListBySection(flowMeasureType);
	}

	public String getFlowValue(FlowMeasureType measureType,String[] flowType) {
		String val = "";
		double count = 0;
		if(flowType!=null){
			String[] type = flowType;
			
			for(int i=0;i<type.length;i++){
				
				if("1".equals(type[i]))
					count += Double.parseDouble(measureType.getTotal());
				if("2".equals(type[i]))
					count += Double.parseDouble(measureType.getUpTotal());
				if("3".equals(type[i]))
					count += Double.parseDouble(measureType.getUpPassengerShip());
				if("4".equals(type[i]))
					count += Double.parseDouble(measureType.getUpCargoShip());
				if("5".equals(type[i]))
					count += Double.parseDouble(measureType.getUpContainerShip());
				if("6".equals(type[i]))
					count += Double.parseDouble(measureType.getUpDangerousShip());
				if("7".equals(type[i]))
					count += Double.parseDouble(measureType.getUpBoatTrain());
				if("8".equals(type[i]))
					count += Double.parseDouble(measureType.getUpFishBoat());	
				if("9".equals(type[i]))
					count += Double.parseDouble(measureType.getUpProjectShip());
				if("10".equals(type[i]))
					count += Double.parseDouble(measureType.getUpPublicShip());
				if("11".equals(type[i]))
					count += Double.parseDouble(measureType.getUpOthers());
				if("12".equals(type[i]))
					count += Double.parseDouble(measureType.getDownTotal());
				if("13".equals(type[i]))
					count += Double.parseDouble(measureType.getDownPassengerShip());
				if("14".equals(type[i]))
					count += Double.parseDouble(measureType.getDownCargoShip());
				if("15".equals(type[i]))
					count += Double.parseDouble(measureType.getDownContainerShip());
				if("16".equals(type[i]))
					count += Double.parseDouble(measureType.getDownDangerousShip());
				if("17".equals(type[i]))
					count += Double.parseDouble(measureType.getDownBoatTrain());
				if("18".equals(type[i]))
					count += Double.parseDouble(measureType.getDownFishBoat());	
				if("19".equals(type[i]))
					count += Double.parseDouble(measureType.getDownProjectShip());
				if("20".equals(type[i]))
					count += Double.parseDouble(measureType.getDownPublicShip());
				if("21".equals(type[i]))
					count += Double.parseDouble(measureType.getDownOthers());
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
	public void setSectionNameByVal(FlowMeasureType flowMeasureType) {
		List<String> sectionList = new ArrayList<String>();
		if(flowMeasureType.getSectionVal()!=null){
			String[] params = flowMeasureType.getSectionVal();
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

				}if("7".equals(params[i])){
					sectionList.add("六干河观测点");
					continue;
				}if("8".equals(params[i])){
					sectionList.add("老沙观测点");
					continue;

			}
			flowMeasureType.setSectionList(sectionList);
					
		}
		}
	}
}