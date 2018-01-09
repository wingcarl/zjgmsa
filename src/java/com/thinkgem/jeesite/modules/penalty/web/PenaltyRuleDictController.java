/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.penalty.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.penalty.entity.PenaltyRuleDict;
import com.thinkgem.jeesite.modules.penalty.service.PenaltyRuleDictService;

/**
 * 违章字典Controller
 * @author dylan wang
 * @version 2017-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/penalty/penaltyRuleDict")
public class PenaltyRuleDictController extends BaseController {

	@Autowired
	private PenaltyRuleDictService penaltyRuleDictService;
	
	@ModelAttribute
	public PenaltyRuleDict get(@RequestParam(required=false) String id) {
		PenaltyRuleDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = penaltyRuleDictService.get(id);
		}
		if (entity == null){
			entity = new PenaltyRuleDict();
		}
		return entity;
	}
	
	@RequiresPermissions("penalty:penaltyRuleDict:view")
	@RequestMapping(value = {"list", ""})
	public String list(PenaltyRuleDict penaltyRuleDict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PenaltyRuleDict> page = penaltyRuleDictService.findPage(new Page<PenaltyRuleDict>(request, response), penaltyRuleDict); 
		model.addAttribute("page", page);
		return "modules/penalty/penaltyRuleDictList";
	}

	@RequiresPermissions("penalty:penaltyRuleDict:view")
	@RequestMapping(value = "form")
	public String form(PenaltyRuleDict penaltyRuleDict, Model model) {
		model.addAttribute("penaltyRuleDict", penaltyRuleDict);
		return "modules/penalty/penaltyRuleDictForm";
	}

	@RequiresPermissions("penalty:penaltyRuleDict:edit")
	@RequestMapping(value = "save")
	public String save(PenaltyRuleDict penaltyRuleDict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, penaltyRuleDict)){
			return form(penaltyRuleDict, model);
		}
		String tmp = "[{\"cause\":\"未按规定取得船舶安全管理证书或者临时船舶安全管理证书从事航行或者其他有关活动\",\"causeNo\":\"AY-2AB3CB82-04BE-40C0-BBC5-D00DAF2DEFC5\",\"clause\":\"《中华人民共和国内河海事行政处罚规定》第六条\"}]";
		JSONArray jArray = JSONArray.parseArray(tmp);
		List<PenaltyRuleDict> prdList = new ArrayList<PenaltyRuleDict>();
		for(int i=0;i<jArray.size();i++){
			JSONObject o = jArray.getJSONObject(i);
			String cause = o.get("cause").toString();
			String clause = o.getString("clause");
			PenaltyRuleDict dict = new PenaltyRuleDict();
			dict.setCause(cause);
			dict.setClause(clause);
			dict.setSummaryCause("1");
			prdList.add(dict);
		}
		for(PenaltyRuleDict dict : prdList){
			penaltyRuleDictService.save(dict);
		}
		
		addMessage(redirectAttributes, "保存违章字典成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyRuleDict/?repage";
	}
	
	@RequiresPermissions("penalty:penaltyRuleDict:edit")
	@RequestMapping(value = "delete")
	public String delete(PenaltyRuleDict penaltyRuleDict, RedirectAttributes redirectAttributes) {
		penaltyRuleDictService.delete(penaltyRuleDict);
		addMessage(redirectAttributes, "删除违章字典成功");
		return "redirect:"+Global.getAdminPath()+"/penalty/penaltyRuleDict/?repage";
	}

}