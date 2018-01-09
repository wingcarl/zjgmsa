/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.service;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.report.entity.ReportMonthly;
import com.thinkgem.jeesite.modules.report.dao.ReportMonthlyDao;

/**
 * 海事业务月度通报Service
 * @author Dylan Wang
 * @version 2017-07-24
 */
@Service
@Transactional(readOnly = true)
public class ReportMonthlyService extends CrudService<ReportMonthlyDao, ReportMonthly> {

	public ReportMonthly get(String id) {
		return super.get(id);
	}
	
	public List<ReportMonthly> findList(ReportMonthly reportMonthly) {
		return super.findList(reportMonthly);
	}
	
	public Page<ReportMonthly> findPage(Page<ReportMonthly> page, ReportMonthly reportMonthly) {
		return super.findPage(page, reportMonthly);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportMonthly reportMonthly) {
		super.save(reportMonthly);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportMonthly reportMonthly) {
		super.delete(reportMonthly);
	}

	/**
	 * 解析Excel表格中的内容
	 * @param sheet
	 */
	@Transactional(readOnly = false)
	public void importReportFromExcel(HSSFSheet sheet) {
		ReportMonthly reportMonthly = new ReportMonthly();
		double date = sheet.getRow(1).getCell(4).getNumericCellValue();
    	Date d = DateUtils.toDate(date);
    	reportMonthly.setCreateDate(d);//设置导入表格月份时间
    	
    	//巡航巡查
    	//水域巡查
		reportMonthly.setCruise((int)sheet.getRow(2).getCell(4).getNumericCellValue());
    	reportMonthly.setCruiseTotalTime(sheet.getRow(3).getCell(4).getNumericCellValue());
    	reportMonthly.setCruiseTotalDistance((int)sheet.getRow(4).getCell(4).getNumericCellValue());
    	reportMonthly.setCruisePeopleCount((int)sheet.getRow(5).getCell(4).getNumericCellValue());
    	reportMonthly.setNightCruiseCount((int)sheet.getRow(6).getCell(4).getNumericCellValue());
    	reportMonthly.setShipCheck((int)sheet.getRow(7).getCell(4).getNumericCellValue());
    	reportMonthly.setIllegalCount((int)sheet.getRow(8).getCell(4).getNumericCellValue());
    	reportMonthly.setDealIllegalCount((int)sheet.getRow(9).getCell(4).getNumericCellValue());
    	//陆域巡查
    	reportMonthly.setCruiseCar(((int)sheet.getRow(10).getCell(4).getNumericCellValue()));
    	reportMonthly.setLuyuPeopleCount(((int)sheet.getRow(11).getCell(4).getNumericCellValue()));
    	reportMonthly.setLuyuShipCheck(((int)sheet.getRow(12).getCell(4).getNumericCellValue()));
    	reportMonthly.setLuyuIllegalCount(((int)sheet.getRow(13).getCell(4).getNumericCellValue()));
    	reportMonthly.setLuyuDealCount(((int)sheet.getRow(14).getCell(4).getNumericCellValue()));
    	
    	//vts数据
    	reportMonthly.setVtsShipReport(((int)sheet.getRow(15).getCell(4).getNumericCellValue()));
    	reportMonthly.setVtsShipMonitor(((int)sheet.getRow(16).getCell(4).getNumericCellValue()));
    	reportMonthly.setVtsIllegal(((int)sheet.getRow(17).getCell(4).getNumericCellValue()));
    	reportMonthly.setVtsTraffic(((int)sheet.getRow(18).getCell(4).getNumericCellValue()));
    	reportMonthly.setVtsInfo(((int)sheet.getRow(19).getCell(4).getNumericCellValue()));
    	reportMonthly.setVtsUnion(((int)sheet.getRow(20).getCell(4).getNumericCellValue()));
    	reportMonthly.setVtsMonitor((((int)sheet.getRow(21).getCell(4).getNumericCellValue())));
    	
    	//辖区险情
    	reportMonthly.setDangerEmergencyCount(((int)sheet.getRow(22).getCell(4).getNumericCellValue()));
    	reportMonthly.setDangerCount(((int)sheet.getRow(23).getCell(4).getNumericCellValue()));
    	reportMonthly.setDangerPeople(((int)sheet.getRow(24).getCell(4).getNumericCellValue()));
    	reportMonthly.setRescuePeople(((int)sheet.getRow(25).getCell(4).getNumericCellValue()));
    	reportMonthly.setDangerShip(((int)sheet.getRow(26).getCell(4).getNumericCellValue()));
    	reportMonthly.setRescueShip(((int)sheet.getRow(27).getCell(4).getNumericCellValue()));
    	
    	reportMonthly.setUpDangerCount(((int)sheet.getRow(28).getCell(4).getNumericCellValue()));
    	reportMonthly.setUpDangerPeople(((int)sheet.getRow(29).getCell(4).getNumericCellValue()));
    	reportMonthly.setUpRescuePeople(((int)sheet.getRow(30).getCell(4).getNumericCellValue()));
    	reportMonthly.setUpDangerShip(((int)sheet.getRow(31).getCell(4).getNumericCellValue()));
    	reportMonthly.setUpRescueShip(((int)sheet.getRow(32).getCell(4).getNumericCellValue()));
    	reportMonthly.setShipOut(((int)sheet.getRow(33).getCell(4).getNumericCellValue()));
    	reportMonthly.setSocialShipOut(((int)sheet.getRow(34).getCell(4).getNumericCellValue()));
    	reportMonthly.setSavePercent((sheet.getRow(35).getCell(4).getNumericCellValue()));
    	
    	//事故
    	reportMonthly.setAccidentCount(((int)sheet.getRow(36).getCell(4).getNumericCellValue()));
    	reportMonthly.setTantCount(((int)sheet.getRow(37).getCell(4).getNumericCellValue()));
    	reportMonthly.setEconomicLoss(((int)sheet.getRow(38).getCell(4).getNumericCellValue()));
    	reportMonthly.setLevelAccidentCount(((int)sheet.getRow(39).getCell(4).getNumericCellValue()));
    	reportMonthly.setLevelTantCount(((int)sheet.getRow(40).getCell(4).getNumericCellValue()));
    	reportMonthly.setLevelEconomicLost(((int)sheet.getRow(41).getCell(4).getNumericCellValue()));
    	
    	//水工
    	reportMonthly.setProjectAudit(((int)sheet.getRow(42).getCell(4).getNumericCellValue()));
    	reportMonthly.setProjectPreview(((int)sheet.getRow(43).getCell(4).getNumericCellValue()));
    	reportMonthly.setProjectNow(((int)sheet.getRow(44).getCell(4).getNumericCellValue()));
    	reportMonthly.setNavigatorNotice(((int)sheet.getRow(45).getCell(4).getNumericCellValue()));

    	
    	reportMonthly.setAdvisory(((int)sheet.getRow(46).getCell(4).getNumericCellValue()));
    	reportMonthly.setTempTrafficControl(((int)sheet.getRow(47).getCell(4).getNumericCellValue()));
    	reportMonthly.setControlHours((sheet.getRow(48).getCell(4).getNumericCellValue()));
    	reportMonthly.setControlDanger(((int)sheet.getRow(49).getCell(4).getNumericCellValue()));
    	reportMonthly.setControlDangerHours((sheet.getRow(50).getCell(4).getNumericCellValue()));
    	reportMonthly.setShipInPort(((int)sheet.getRow(51).getCell(4).getNumericCellValue()));
    	reportMonthly.setShipByPort(((int)sheet.getRow(52).getCell(4).getNumericCellValue()));
    	reportMonthly.setHiddenDanger(((int)sheet.getRow(53).getCell(4).getNumericCellValue()));
    	reportMonthly.setHiddenDangerReport(((int)sheet.getRow(54).getCell(4).getNumericCellValue()));
    	reportMonthly.setReformHiddenDanger(((int)sheet.getRow(55).getCell(4).getNumericCellValue()));
    	reportMonthly.setOverWeight(((int)sheet.getRow(56).getCell(4).getNumericCellValue()));
    	reportMonthly.setPenaltyOverWeight(((int)sheet.getRow(57).getCell(4).getNumericCellValue()));
    	reportMonthly.setPenaltyOverWeightMoney((sheet.getRow(58).getCell(4).getNumericCellValue()));
    	reportMonthly.setShipReport(((int)sheet.getRow(59).getCell(4).getNumericCellValue()));
    	reportMonthly.setShipReportAll(((int)sheet.getRow(60).getCell(4).getNumericCellValue()));
    	reportMonthly.setSeaShipReport((sheet.getRow(61).getCell(4).getNumericCellValue()));
    	
    	reportMonthly.setInternalAudit(((int)sheet.getRow(62).getCell(4).getNumericCellValue()));
    	reportMonthly.setInternalSingleCount(((int)sheet.getRow(63).getCell(4).getNumericCellValue()));
    	reportMonthly.setIntralShipCount(((int)sheet.getRow(64).getCell(4).getNumericCellValue()));
    	
    	reportMonthly.setShipRegist(((int)sheet.getRow(65).getCell(4).getNumericCellValue()));
    	reportMonthly.setThroughputInPort((sheet.getRow(66).getCell(4).getNumericCellValue()));
    	reportMonthly.setThroughputOutPort((sheet.getRow(67).getCell(4).getNumericCellValue()));
    	reportMonthly.setThroughputTotalPort((sheet.getRow(68).getCell(4).getNumericCellValue()));
    	reportMonthly.setThroughputInWharf(getNum(sheet,69));
    	
    	reportMonthly.setThroughputOutWharf(getNum(sheet,70));
    	reportMonthly.setThroughputTotalWharf(getNum(sheet,71));
    	reportMonthly.setShipBurden(getNum(sheet,72));
    	reportMonthly.setPscCount((int)getNum(sheet,73));
    	reportMonthly.setPscDefect((int)getNum(sheet,74));
    	reportMonthly.setPscRetention((int)getNum(sheet,75));
    	reportMonthly.setSeaCount((int)getNum(sheet,76));
    	reportMonthly.setSeaDefect((int)getNum(sheet,77));
    	reportMonthly.setSeaRetention((int)getNum(sheet,78));
    	reportMonthly.setRiverboatCount((int)getNum(sheet,79));
    	reportMonthly.setRiverboatDefect((int)getNum(sheet,80));
    	reportMonthly.setRiverboatRetention((int)getNum(sheet,81));
    	
    	reportMonthly.setPortCount((int)getNum(sheet,82));
    	reportMonthly.setPortPeopleCount(getNum(sheet,83));
    	reportMonthly.setPortCarCount(getNum(sheet,84));
    	reportMonthly.setSanhuaCount((int)getNum(sheet,85));
    	reportMonthly.setBaozhuangCount((int)getNum(sheet,86));
    	reportMonthly.setSanguCount((int)getNum(sheet,87));
    	reportMonthly.setASanguCount((int)getNum(sheet,88));
    	reportMonthly.setDangerTotalCount((int)getNum(sheet,89));
    	reportMonthly.setSanhuaCargoCount(getNum(sheet,90));
    	
    	
    	reportMonthly.setBaozhuangCargoCount(getNum(sheet,91));
    	reportMonthly.setSanguCargoCount(getNum(sheet,92));
    	reportMonthly.setASanguCargoCount(getNum(sheet,93));
    	reportMonthly.setDangerCargoTotalCount(getNum(sheet,94));
    	reportMonthly.setCanyouCount(getNum(sheet,95));
    	reportMonthly.setXicangshuiCount(getNum(sheet,96));
    	reportMonthly.setYazaishuiCount(getNum(sheet,97));
    	reportMonthly.setFangwuzuoyeCount(getNum(sheet,98));
    	reportMonthly.setBoxCheckCount((int)getNum(sheet,99));
    	reportMonthly.setBoxDefectCount((int)getNum(sheet,100));
    	
    	reportMonthly.setBoxDefectPercent(getNum(sheet,101));
    	reportMonthly.setPenaltyCount((int)getNum(sheet,102));
    	reportMonthly.setPenaltyMoneyCount(getNum(sheet,103));
    	reportMonthly.setPenaltyNotice((int)getNum(sheet,104));
    	reportMonthly.setMoneyZwzx(getNum(sheet,105));
    	reportMonthly.setMoneyGqc(getNum(sheet,106));
    	reportMonthly.setMoneyBsq(getNum(sheet,107));
    	reportMonthly.setMoneyJfc(getNum(sheet,108));
    	reportMonthly.setMoneyNfc(getNum(sheet,109));
    	reportMonthly.setMoneyDqb(getNum(sheet,110));
    	reportMonthly.setMoneyDaishou(getNum(sheet,111));
    	reportMonthly.setMoneyTotal(getNum(sheet,112));
    	this.save(reportMonthly);
		
	}
	
	private double getNum(HSSFSheet sheet,int i){
		return sheet.getRow(i).getCell(4).getNumericCellValue();
	}
}