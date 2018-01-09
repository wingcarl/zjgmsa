/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.msa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.msa.entity.MsaReport;
import com.thinkgem.jeesite.modules.msa.entity.MsaReportStat;
import com.thinkgem.jeesite.modules.msa.dao.MsaReportDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 政务信息爬虫获取Service
 * @author dylan wang
 * @version 2017-05-26
 */
@Service
@Transactional(readOnly = true)
public class MsaReportService extends CrudService<MsaReportDao, MsaReport> {
	@Autowired
	MsaReportDao msaReportDao;
	public MsaReport get(String id) {
		return super.get(id);
	}
	
	public List<MsaReport> findList(MsaReport msaReport) {
		return super.findList(msaReport);
	}
	
	public Page<MsaReport> findPage(Page<MsaReport> page, MsaReport msaReport) {
		return super.findPage(page, msaReport);
	}
	
	@Transactional(readOnly = false)
	public void save(MsaReport msaReport) {
		//msaReport.setIsNewRecord(true);
		super.save(msaReport);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsaReport msaReport) {
		super.delete(msaReport);
	}
	@Transactional(readOnly = false)
	public void getAndSave(MsaReport msaReport) {
		
	}
	
	@Transactional(readOnly = false)
	public void saveList(List<MsaReport> reportList) {
		for(MsaReport r : reportList){
			r.setIsNewRecord(true);
			try{
				super.save(r);
			}catch(Exception e){
				System.out.println(r.getTitle()+"已采集");
			}
		}
		
	}

	public List<MsaReportStat> findMsaReportStatics(MsaReportStat msaReportStat) {
		MsaReport msaReport = new MsaReport();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(msaReportStat.getQuater()!=null && !"5".equals(msaReportStat.getQuater())){
			String year = msaReportStat.getYear();
			if(year == null) {
				year= String.valueOf(cal.get(Calendar.YEAR));
			}
			int quater = Integer.parseInt(msaReportStat.getQuater());
			int month = 1+(quater-1)*3;
			String beginMonth = String.valueOf(month);
			String endMonth = String.valueOf(month+3);
			try {
				Date endDate = sdf.parse(year+"-"+endMonth+"-1");
				Calendar ca = Calendar.getInstance();
				ca.setTime(endDate);
				ca.set(Calendar.DATE, ca.get(Calendar.DATE)-1);
				msaReport.setBeginPublicDate(sdf.parse(year+"-"+beginMonth+"-1"));
				msaReport.setEndPublicDate(ca.getTime());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if("5".equals(msaReportStat.getQuater())){
			msaReport.setBeginPublicDate(msaReportStat.getBeginPublicDate());
			msaReport.setEndPublicDate(msaReportStat.getEndPublicDate());
		}
		msaReport.setAuthor(msaReportStat.getAuthor());
		msaReport.setDepid(msaReportStat.getDepid());
		List<MsaReport> reportList = super.findList(msaReport);
		Map<String,Integer> counter = new HashMap<String,Integer>();
		Map<String,Integer> ztdyCounter = new HashMap<String,Integer>();
		Map<String,Integer> scores = new HashMap<String,Integer>();
		Map<String,Integer> aim = new HashMap<String,Integer>(); //目标任务
		
		List<MsaReportStat> statList = new ArrayList<MsaReportStat>();
		if("1".equals(msaReportStat.getStatType())){ //按部门统计
			//每个部门年度有在江苏局发表政务信息的任务，超过任务的每篇可以单独加30分
			for(MsaReport r : reportList){
				Integer count = counter.get(r.getDepid().getName());  //计数
				Integer score = scores.get(r.getDepid().getName()); //计分
				Integer ztdy = ztdyCounter.get(r.getDepid().getName()); //专题调研
				if(count==null){
					count = 0;
				}
				if(score == null){
					score = 0;
				}
				if(ztdy == null){
					ztdy = 0;
				}
				String type = r.getType();
				String[] types = type.split(",");
				List<Dict> dicts = DictUtils.getDictList("msa_report_type");
				Map<String,Object> scoreMap = new HashMap<String,Object>();
				for(Dict dict : dicts){
					scoreMap.put(dict.getValue(), dict.getDescription());
				}
				Integer maxScore = 0;
				for(int i=0;i<types.length;i++){
					if("2".equals(types[i])){ //专题调研类，特别计算
						ztdy++;
					}
					if(!"1".equals(types[i]) && !"2".equals(types[i])){ //如果此篇政务信息不是张家港局录用的
						Integer num = aim.get(r.getDepid().getName());
						if(num == null) num = 0;
						aim.put(r.getDepid().getName(), ++num);
					}
					int tmp = Integer.parseInt(scoreMap.get(types[i]).toString());
					if(tmp > maxScore) {
						maxScore = tmp;
					}
				}
				score += maxScore;
				counter.put(r.getDepid().getName(),count+1);
				ztdyCounter.put(r.getDepid().getName(),ztdy);

				scores.put(r.getDepid().getName(),score);
			}
			Map<String,Integer> target = msaReportStat.target;
			Iterator<Map.Entry<String,Integer>> it = target.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, Integer> entry = it.next();
				int s = entry.getValue()/4;
				Integer a = aim.get(entry.getKey()); //获得当季度在江苏局发表的文章数量
				if(a==null) a= 0;
				if(a > s ) //如果超额完成了在江苏局发表文章的目标，每篇额外加30分
				{
					Integer score = scores.get(entry.getKey());
					if(score == null) score = 0;
					score += (a-s)*30; 
					scores.put(entry.getKey(), score);
				}
				
			}
			for(String key : counter.keySet()){
				MsaReportStat stat = new MsaReportStat();
				stat.setName(key);
				stat.setCount(counter.get(key));
				stat.setScore(scores.get(key));
				stat.setZtdy(ztdyCounter.get(key));
				statList.add(stat);
			}
		}else if("2".equals(msaReportStat.getStatType())){
			for(MsaReport r : reportList){
				String[] authors = r.getAuthor().split(" |,");
				String author = authors[0];
				
				Integer count = counter.get(author);
				Integer ztdy = ztdyCounter.get(author);
				Integer score = scores.get(author);
				if(count==null){
					count = 0;
				}
				if(score == null){
					score = 0;
				}
				if(ztdy == null){
					ztdy = 0;
				}
				String type = r.getType();
				String[] types = type.split(",");
				List<Dict> dicts = DictUtils.getDictList("msa_report_type");
				Map<String,Object> scoreMap = new HashMap<String,Object>();
				for(Dict dict : dicts){
					scoreMap.put(dict.getValue(), dict.getDescription());
				}
				Integer maxScore = 0;
				for(int i=0;i<types.length;i++){
					if("2".equals(types[i])){
						ztdy++;
					}
					int tmp = Integer.parseInt(scoreMap.get(types[i]).toString());
					if(tmp > maxScore) {
						maxScore = tmp;
					}
				}
				score += maxScore;
				counter.put(author,count+1);
				ztdyCounter.put(author, ztdy);
				scores.put(author,score);
			}
			for(String key : counter.keySet()){
				MsaReportStat stat = new MsaReportStat();
				stat.setName(key);
				stat.setCount(counter.get(key));
				stat.setScore(scores.get(key));
				stat.setZtdy(ztdyCounter.get(key));
				statList.add(stat);
			}
		}
		Collections.sort(statList,new Comparator<MsaReportStat>(){
			@Override
			public int compare(MsaReportStat b1,MsaReportStat b2){
				return b2.getScore().compareTo(b1.getScore());
			}
		});
		return statList;
	}

	@Transactional(readOnly = false)
	public void updateMsaReportTypes(List<MsaReport> msaReportList) {
		for(MsaReport m : msaReportList){
			try{
				msaReportDao.updateByTitle(m.getTitle(), m.getType());

			}catch(Exception e ){
				e.printStackTrace();
			}
		}
		
	}

	
	
	
}