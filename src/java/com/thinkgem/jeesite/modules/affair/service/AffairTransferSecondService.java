/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferDetail;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferDetailDao;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferInfoDao;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferSecond;
import com.thinkgem.jeesite.modules.affair.dao.AffairTransferSecondDao;

/**
 * 水水中转货物核销登记Service
 * @author Dylan Wang
 * @version 2017-07-02
 */
@Service
@Transactional(readOnly = true)
public class AffairTransferSecondService extends CrudService<AffairTransferSecondDao, AffairTransferSecond> {

	@Autowired
	private AffairTransferSecondDao affairTransferSecondDao;
	@Autowired
	private AffairTransferDetailDao affairTransferDetailDao;
	
	public AffairTransferSecond get(String id) {
		AffairTransferSecond second = super.get(id);
		if(second!=null){
			AffairTransferDetail affairTransferDetail = new AffairTransferDetail();
			affairTransferDetail.setSecond(second);
			affairTransferDetail.setIsForm("1");
			List<AffairTransferDetail> lists = affairTransferDetailDao.findList(affairTransferDetail);
			second.setAffairTransferDetailList(lists);
		}
		return second;
	
	}
	
	
	
}