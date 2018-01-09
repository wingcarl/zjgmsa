/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.service;

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

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.WordGenerator;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferDetail;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferDetailDao;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferInfoDao;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferSecond;
import com.thinkgem.jeesite.modules.oa.entity.OaCruiseStat;
import com.thinkgem.jeesite.modules.oa.entity.OaDigitalcruise;
import com.thinkgem.jeesite.modules.oa.entity.OaExportDaily;
import com.thinkgem.jeesite.modules.oa.entity.PortData;
import com.thinkgem.jeesite.modules.vts.entity.VtsWorkData;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferSecondDao;

/**
 * 水水中转货物核销登记Service
 * @author Dylan Wang
 * @version 2017-07-02
 */
@Service
@Transactional(readOnly = true)
public class AffairTransferInfoService extends CrudService<AffairTransferInfoDao, AffairTransferInfo> {

	@Autowired
	private AffairTransferSecondDao affairTransferSecondDao;
	@Autowired
	private AffairTransferDetailDao affairTransferDetailDao;
	public AffairTransferInfo get(String id) {
		AffairTransferInfo affairTransferInfo = super.get(id);
		if(affairTransferInfo != null)
			affairTransferInfo.setAffairTransferSecondList(affairTransferSecondDao.findList(new AffairTransferSecond(affairTransferInfo)));
		return affairTransferInfo;
	}
	
	public List<AffairTransferInfo> findList(AffairTransferInfo affairTransferInfo) {
		return super.findList(affairTransferInfo);
	}
	
	public Page<AffairTransferInfo> findPage(Page<AffairTransferInfo> page, AffairTransferInfo affairTransferInfo) {
		Page<AffairTransferInfo> pageList =  super.findPage(page, affairTransferInfo);
		List<AffairTransferInfo> list = pageList.getList();
		for(AffairTransferInfo info : list){
			List<AffairTransferSecond> secondList = affairTransferSecondDao.findList(new AffairTransferSecond(info));
			for(AffairTransferSecond second : secondList){
				second.setAffairTransferDetailList(affairTransferDetailDao.findList(new AffairTransferDetail(second)));
			}
			info.setAffairTransferSecondList(secondList);
		}
		pageList.setList(list);
		return pageList;
	}
	
	@Transactional(readOnly = false)
	public void save(AffairTransferInfo affairTransferInfo) {
		super.save(affairTransferInfo);
		for (AffairTransferSecond affairTransferSecond : affairTransferInfo.getAffairTransferSecondList()){
			if (affairTransferSecond.getId() == null){
				continue;
			}
			if (AffairTransferSecond.DEL_FLAG_NORMAL.equals(affairTransferSecond.getDelFlag())){
				if (StringUtils.isBlank(affairTransferSecond.getId())){
					affairTransferSecond.setFirstId(affairTransferInfo);
					affairTransferSecond.preInsert();
					affairTransferSecondDao.insert(affairTransferSecond);
				}else{
					affairTransferSecond.preUpdate();
					affairTransferSecondDao.update(affairTransferSecond);
				}
			}else{
				affairTransferSecondDao.delete(affairTransferSecond);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(AffairTransferInfo affairTransferInfo) {
		super.delete(affairTransferInfo);
		affairTransferSecondDao.delete(new AffairTransferSecond(affairTransferInfo));
	}

	public File generateDailyReportByFreeMarker(AffairTransferInfo ship) throws IOException {
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
		Map<String,Object> key = new HashMap<String,Object>();
		key.put("firstShipName", ship.getFirstShipName());
		key.put("applyOffice", ship.getApplyOffice());
		key.put("auditDate", sdf.format(ship.getUpdateDate()));
		key.put("leafNumber", ship.getNumber());
		key.put("hc", ship.getFirstMoveNum());
          //设置日期，期数
         Date today = new Date();
         Calendar calendar = Calendar.getInstance();
  		 calendar.setTime(ship.getUpdateDate());
          key.put("myear", String.valueOf(calendar.get(Calendar.YEAR)));
          key.put("mmonth", String.valueOf(calendar.get(Calendar.MONTH)+1));
          key.put("mday", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

         
        
          
		//FreeMarkers freemarker = new FreeMarkers();	   
		//freemarker.createWord(list);
		 // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
        File file = null;  
        InputStream fin = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = WordGenerator.createDoc(key, "auditModel");  
            return file;
          
        } finally {  
            if(fin != null) fin.close();  

        } 
	}
	
}