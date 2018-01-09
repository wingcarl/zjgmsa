/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisearea;
import com.thinkgem.jeesite.modules.oa.entity.OaCruisedataArea;
import com.thinkgem.jeesite.modules.oa.dao.OaCruiseareaDao;

/**
 * 巡航水域增删改查Service
 * @author 王浩宇
 * @version 2017-03-16
 */
@Service
@Transactional(readOnly = true)
public class OaCruiseareaService extends CrudService<OaCruiseareaDao, OaCruisearea> {

	@Autowired
	OaCruiseareaDao oaCruiseareaDao;
	
	public OaCruisearea get(String id) {
		return super.get(id);
	}
	
	public List<OaCruisearea> findList(OaCruisearea oaCruisearea) {
		return super.findList(oaCruisearea);
	}
	
	public Page<OaCruisearea> findPage(Page<OaCruisearea> page, OaCruisearea oaCruisearea) {
		return super.findPage(page, oaCruisearea);
	}
	
	@Transactional(readOnly = false)
	public void save(OaCruisearea oaCruisearea) {
		super.save(oaCruisearea);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaCruisearea oaCruisearea) {
		super.delete(oaCruisearea);
	}

	public JSONObject findCruiseareaListByOfficeId(OaCruisearea oaCruisearea) {
		List<OaCruisearea> lists = oaCruiseareaDao.findCruiseareaListByOfficeId(oaCruisearea);
		if(lists.size()>0){
			JSONObject obj = new JSONObject();
			for(OaCruisearea oa : lists){
				JSONArray ar = (JSONArray) obj.get(oa.getType());
				if(ar!=null){
					JSONObject o = new JSONObject();
					o.put("id", oa.getId());
					o.put("content", oa.getContent());
					ar.add(o);
					obj.put(oa.getType(), ar);
				}else{
					JSONObject o = new JSONObject();
					o.put("id", oa.getId());
					o.put("content", oa.getContent());
					JSONArray arempty = new JSONArray();
					arempty.add(o);
					obj.put(oa.getType(), arempty);
				}
			}
			return obj;
		}
		return null;
	}

	public Map<String, Double> getCruiseAreaCount(OaCruisedataArea area) {
		List<Map<String, Object>> ms = oaCruiseareaDao.getCruiseAreaCount(area);
		Map<String,Double> sd = new HashMap<String,Double>();
		for(Map<String,Object> m : ms){
			Object counter = m.get("counter");
			sd.put(m.get("content").toString(), Double.valueOf(counter.toString()));
		}
		return sd;
	}
	
}