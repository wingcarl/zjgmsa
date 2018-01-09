/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferDetail;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferSecond;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferDetailDao;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferSecondDao;

/**
 * 二程船详细信息表Service
 * @author Dylan Wang
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class AffairTransferDetailService extends CrudService<AffairTransferDetailDao, AffairTransferDetail> {
	
	@Autowired
	AffairTransferDetailDao affairTransferDetailDao;
	@Autowired
	AffairTransferSecondDao affairTransferSecondDao;
	@Autowired
	ActTaskService actTaskService;
	public AffairTransferDetail get(String id) {
		return super.get(id);
	}
	
	public List<AffairTransferDetail> findList(AffairTransferDetail affairTransferDetail) {
		return super.findList(affairTransferDetail);
	}
	
	public Page<AffairTransferDetail> findPage(Page<AffairTransferDetail> page, AffairTransferDetail affairTransferDetail) {
		affairTransferDetail.getSqlMap().put("dsf", dataScopeFilter(affairTransferDetail.getCurrentUser(), "o", "u"));
		return super.findPage(page, affairTransferDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(List<AffairTransferDetail> affairTransferDetailList,AffairTransferSecond affairTransferSecond) {

		for (AffairTransferDetail affairTransferDetail : affairTransferDetailList){
			if (affairTransferDetail.getId() == null){
				continue;
			}
			if (AffairTransferDetail.DEL_FLAG_NORMAL.equals(affairTransferDetail.getDelFlag())){
				if (StringUtils.isBlank(affairTransferDetail.getId())){
					affairTransferDetail.setSecond(affairTransferSecond);
					affairTransferDetail.preInsert();
					affairTransferDetailDao.insert(affairTransferDetail);
					
				
				}else{
					affairTransferDetail.setSecond(affairTransferSecond);
					affairTransferDetail.preUpdate();
					affairTransferDetailDao.update(affairTransferDetail);
				}
			}else{
				affairTransferDetailDao.delete(affairTransferDetail);
			}
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(AffairTransferDetail affairTransferDetail) {
		super.delete(affairTransferDetail);
	}

	@Transactional(readOnly = false)
	public void saveAudit(AffairTransferSecond affairTransferSecond, String candidatesIds, String auditComment) {
		String id = null;
		String title = affairTransferSecond.getAct().getTaskDefKey();
		AffairTransferDetail detail = new AffairTransferDetail();
		detail.setSecond(affairTransferSecond);
		List<AffairTransferDetail> lists = affairTransferDetailDao.findList(detail);
		for(AffairTransferDetail d : lists){
			if(StringUtils.isNotBlank(d.getProcInsId())){
				d.setAuditText(auditComment);
				affairTransferDetailDao.update(d);
			}
		}
		Map<String,Object> var = new HashMap<String,Object>();
		if("sid-73B9C213-D4A3-4E57-AA1D-3C4F566FD218".equals(title)){
			var.put("hsc", candidatesIds);
			var.put("zwzx", "026050dcc7fb47cd9d3a3526913d7383");
		}else if("sid-7D0069AF-2C39-4010-99E6-E1525FB6400E".equals(title)){
			var.put("zwzxLeader", candidatesIds);
		}
		
		String taskId = affairTransferSecond.getAct().getTaskId();
		actTaskService.complete(taskId, affairTransferSecond.getAct().getProcInsId(), auditComment, var);
  
	}
	
	@Transactional(readOnly = false)
	public void archive(AffairTransferDetail detail) {
		AffairTransferDetail d = affairTransferDetailDao.get(detail);
		String archive = detail.getArchive();
		if("1".equals(archive))
			d.setIsArchive("1");
		else
			d.setIsArchive("2");
		affairTransferDetailDao.update(d);
	}



	
	
}