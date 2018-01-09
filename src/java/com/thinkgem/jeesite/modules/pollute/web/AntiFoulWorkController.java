/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestDetail;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulCompany;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulShip;
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulWork;
import com.thinkgem.jeesite.modules.pollute.entity.CompanyDto;
import com.thinkgem.jeesite.modules.pollute.entity.ShipDto;
import com.thinkgem.jeesite.modules.pollute.service.AntiFoulCompanyService;
import com.thinkgem.jeesite.modules.pollute.service.AntiFoulWorkService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 防污染作业Controller
 * @author Dylan Wang
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/pollute/antiFoulWork")
public class AntiFoulWorkController extends BaseController {
	@Autowired
	private AntiFoulCompanyService antiFoulCompanyService;
	@Autowired
	private AntiFoulWorkService antiFoulWorkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@ModelAttribute
	public AntiFoulWork get(@RequestParam(required=false) String id) {
		AntiFoulWork entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = antiFoulWorkService.get(id);
		}
		if (entity == null){
			entity = new AntiFoulWork();
		}
		return entity;
	}
	
	@RequiresPermissions("pollute:antiFoulWork:view")
	@RequestMapping(value = {"list", ""})
	public String list(AntiFoulWork antiFoulWork, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AntiFoulWork> page = antiFoulWorkService.findPage(new Page<AntiFoulWork>(request, response), antiFoulWork); 
		model.addAttribute("page", page);
		return "modules/pollute/antiFoulWorkList";
	}

	@RequiresPermissions("pollute:antiFoulWork:view")
	@RequestMapping(value = "form")
	public String form(AntiFoulWork antiFoulWork, Model model) {
		if(antiFoulWork.getReceiveTime()==null)
			antiFoulWork.setReceiveTime(new Date());
		if(antiFoulWork.getOffice()==null){
			User u = systemService.getUser(UserUtils.getUser().getId());
			
			Office office = officeService.get(u.getOffice().getId());
			antiFoulWork.setOffice(office);		
		}
		List<AntiFoulCompany> lc = antiFoulCompanyService.findList(new AntiFoulCompany());
		List<CompanyDto> companyList =  Lists.newArrayList();
		for(AntiFoulCompany c : lc){
			CompanyDto d = new CompanyDto();
			d.setCompanyId(c.getId());
			d.setCompanyName(c.getName());
			d.setFoulType(c.getFoulType());
			companyList.add(d);
		}
		List<AntiFoulShip> ship = antiFoulCompanyService.findAllShip();
		List<ShipDto> shipList =  Lists.newArrayList();

		for(AntiFoulShip s : ship){
			ShipDto sd = new ShipDto();
			sd.setShipId(s.getId());
			sd.setShipName(s.getShipName());
			if(s.getCompany()!=null){
				sd.setCompanyId(s.getCompany().getId());

			}
			shipList.add(sd);
		}
		try{
			model.addAttribute("shipList", JSON.toJSON(shipList));
			model.addAttribute("workDep", JSON.toJSON(antiFoulWork.getWorkDep()));
			model.addAttribute("workShip", JSON.toJSON(antiFoulWork.getShipName()));

			model.addAttribute("antiFoulCompany", JSON.toJSON(companyList));

		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("antiFoulWork", antiFoulWork);
		return "modules/pollute/antiFoulWorkForm";
	}

	@RequiresPermissions("pollute:antiFoulWork:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,AntiFoulWork antiFoulWork, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, antiFoulWork)){
			return form(antiFoulWork, model);
		}
		String otherShip = request.getParameter("otherShip");
		String otherCompany = request.getParameter("otherCompany");
		
		String remark = antiFoulWork.getRemarks();
		if(!otherCompany.isEmpty()){
			String aaa = " 补充作业单位名称:"+otherShip;
			remark += aaa;
		}
		if(!otherShip.isEmpty()){
			String aaa = " 补充作业船舶名称:"+otherShip;
			remark += aaa;
		}
		antiFoulWork.setRemarks(remark);
		antiFoulWorkService.save(antiFoulWork);
		addMessage(redirectAttributes, "保存防污染作业现场检查登记表成功");
		return "redirect:"+Global.getAdminPath()+"/pollute/antiFoulWork/?repage";
	}
	
	@RequiresPermissions("pollute:antiFoulWork:edit")
	@RequestMapping(value = "delete")
	public String delete(AntiFoulWork antiFoulWork, RedirectAttributes redirectAttributes) {
		antiFoulWorkService.delete(antiFoulWork);
		addMessage(redirectAttributes, "删除防污染作业现场检查登记表成功");
		return "redirect:"+Global.getAdminPath()+"/pollute/antiFoulWork/?repage";
	}
	
	@RequiresPermissions("pollute:antiFoulWork:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AntiFoulWork antiFoulWrok, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "防污染作业现场检查登记表"+DateUtils.getDate("yyyyMMdd")+".xls";
    		//Page<OaIllegal> page = oaIllegalService.findPage(new Page<OaIllegal>(request, response), oaIllegal); 
    		List<AntiFoulWork> list = antiFoulWorkService.findList(antiFoulWrok); 
    		
    		new ExportExcel("防污染作业现场检查登记数据",AntiFoulWork.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出防污染作业现场检查登记表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pollute/antiFoulWork/?repage";
    }
}