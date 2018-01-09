/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.WordGenerator;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestDetail;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestSpy;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestStat;

import com.thinkgem.jeesite.modules.msa.entity.MsaIndexStat;

import com.thinkgem.jeesite.modules.test.dao.OaCruisetimeDao;
import com.thinkgem.jeesite.modules.test.entity.OaCruisetime;
import com.thinkgem.jeesite.modules.danger.dao.DangerInvestDao;
import com.thinkgem.jeesite.modules.danger.dao.DangerInvestDetailDao;
import com.thinkgem.jeesite.modules.danger.dao.DangerInvestSpyDao;

/**
 * 隐患排查Service
 * @author Dylan Wang
 * @version 2017-08-31
 */
@Service
@Transactional(readOnly = true)
public class DangerInvestService extends CrudService<DangerInvestDao, DangerInvest> {

	@Autowired
	private DangerInvestDetailDao dangerInvestDetailDao;
	@Autowired
	private DangerInvestSpyDao dangerInvestSpyDao;
	@Autowired
	private DangerInvestDao dangerInvestDao;
	public DangerInvest get(String id) {
		DangerInvest dangerInvest = super.get(id);
		dangerInvest.setDangerInvestDetailList(dangerInvestDetailDao.findList(new DangerInvestDetail(dangerInvest)));
		return super.get(id);
	}
	
	public List<DangerInvest> findList(DangerInvest dangerInvest) {
		List<DangerInvest> list =  super.findList(dangerInvest);
		for(DangerInvest invest : list){
			List<DangerInvestDetail> did = dangerInvestDetailDao.findList(new DangerInvestDetail(invest));
			for(DangerInvestDetail d : did){
				d.setDangerInvestSpyList(dangerInvestSpyDao.findList(new DangerInvestSpy(d)));
			}
		
			invest.setDangerInvestDetailList(did);
		}
		return list;
	}
	
	public Page<DangerInvest> findPage(Page<DangerInvest> page, DangerInvest dangerInvest) {
		dangerInvest.getSqlMap().put("dsf", dataScopeFilter(dangerInvest.getCurrentUser(), "c", "u"));

		Page<DangerInvest> p = super.findPage(page, dangerInvest);
		List<DangerInvest> list = p.getList();
		for(DangerInvest invest : list){
			List<DangerInvestDetail> did = dangerInvestDetailDao.findList(new DangerInvestDetail(invest));
			for(DangerInvestDetail d : did){
				d.setDangerInvestSpyList(dangerInvestSpyDao.findList(new DangerInvestSpy(d)));
			}
		
			invest.setDangerInvestDetailList(did);
		}
		p.setList(list);
		return p;
	}
	
	@Transactional(readOnly = false)
	public void save(DangerInvest dangerInvest) {
		super.save(dangerInvest);
		for (DangerInvestDetail dangerInvestDetail : dangerInvest.getDangerInvestDetailList()){
			if (dangerInvestDetail.getId() == null){
				continue;
			}
			if (DangerInvestDetail.DEL_FLAG_NORMAL.equals(dangerInvestDetail.getDelFlag())){
				if (StringUtils.isBlank(dangerInvestDetail.getId())){
					dangerInvestDetail.setDangerInvest(dangerInvest);
					dangerInvestDetail.preInsert();
					dangerInvestDetailDao.insert(dangerInvestDetail);
				}else{
					dangerInvestDetail.preUpdate();
					dangerInvestDetailDao.update(dangerInvestDetail);
				}
			}else{
				dangerInvestDetailDao.delete(dangerInvestDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DangerInvest dangerInvest) {
		super.delete(dangerInvest);
		dangerInvestDetailDao.delete(new DangerInvestDetail(dangerInvest));

	}

	public File generateDailyReportByFreeMarker(DangerInvest dangerInvest) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
		Map<String,Object> key = new HashMap<String,Object>();
		int i = 1;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for(DangerInvestDetail d : dangerInvest.getDangerInvestDetailList()){
			Map<String,Object> keyMap = new HashMap<String,Object>();
			keyMap.put("number", i++);
			keyMap.put("danger", d.getDetail());
			keyMap.put("require",d.getRequirement());
			list.add(keyMap);
		}
		
		key.put("sequence", list);
        key.put("name", dangerInvest.getDangerCompany());
		//FreeMarkers freemarker = new FreeMarkers();	   
		//freemarker.createWord(list);
		 // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
        File file = null;  
        InputStream fin = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = WordGenerator.createDoc(key, "danger");  
            return file;
          
        } finally {  
            if(fin != null) fin.close();  

        }
	}

	@Transactional(readOnly = false)
	public void confirm(DangerInvest dangerInvest) {
		DangerInvest d = this.get(dangerInvest);
		if("1".equals(d.getIsConfirm())){
			d.setIsConfirm("0");
		}else{
			d.setIsConfirm("1");
		}
		
		dangerInvestDao.confirm(d);
	}

	public List<DangerInvestStat> getSumData(DangerInvest dangerInvest) {
		List<DangerInvestStat> dis = dangerInvestDetailDao.findDetailList(dangerInvest);
		List<DangerInvestStat> follows = dangerInvestSpyDao.findSpyList(dangerInvest);
		for(DangerInvestStat st : dis){
			for(DangerInvestStat f : follows){
				if(f.getOffice().getId().equals(st.getOffice().getId())){
					st.setFollowTimes(f.getFollowTimes());
				}
			}
			st.setNoRecifySum(st.getDangerSum()-st.getRecifySum());
		}
		return dis;
	}

	public Object getVtsSum(List<DangerInvestStat> page) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDangerSum(List<DangerInvestStat> page) {
		DangerInvestStat sum = new DangerInvestStat();
		sum.setDangerSum(0);
		sum.setFollowTimes(0);
		sum.setNoRecifySum(0);
		sum.setRecifyFileSum(0);
		sum.setRecifySum(0);
		for(DangerInvestStat st : page){
			sum.setOffice(st.getOffice());
			sum.setDangerSum(sum.getDangerSum()+st.getDangerSum());
			if(st.getFollowTimes()!=null){
				sum.setFollowTimes(sum.getFollowTimes()+st.getFollowTimes());
			}
			sum.setRecifySum(sum.getRecifySum()+st.getRecifySum());
			sum.setRecifyFileSum(sum.getRecifyFileSum()+st.getRecifyFileSum());
			sum.setNoRecifySum(sum.getNoRecifySum()+st.getNoRecifySum());
		}
		return sum;
	}

	public void setSpy(DangerInvest dangerInvest) {
	
			List<DangerInvestDetail> did = dangerInvestDetailDao.findList(new DangerInvestDetail(dangerInvest));
			for(DangerInvestDetail d : did){
				d.setDangerInvestSpyList(dangerInvestSpyDao.findList(new DangerInvestSpy(d)));
			}
		
			dangerInvest.setDangerInvestDetailList(did);
		
		
	}


	public List<MsaIndexStat> findDataByMonth(MsaIndexStat stat) {
		return dangerInvestDao.findDataByMonth(stat);
	}

	public List<MsaIndexStat> findDataByType(MsaIndexStat stat) {
		// TODO Auto-generated method stub
		return dangerInvestDao.findDataByType(stat);
	}

	public List<MsaIndexStat> findDataByRecify(MsaIndexStat stat) {
		return dangerInvestDao.findDataByRecify(stat);
	}

	
}