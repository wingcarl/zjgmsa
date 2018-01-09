/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.ZJGMSAUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;
import com.thinkgem.jeesite.modules.oa.service.OaInportShipService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 每日在港船舶动态Controller
 * @author 王浩宇
 * @version 2017-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaInportShip")
public class OaInportShipController extends BaseController {

	@Autowired
	private OaInportShipService oaInportShipService;
	@Autowired
	private OfficeService officeService;
	@ModelAttribute
	public OaInportShip get(@RequestParam(required=false) String id) {
		OaInportShip entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaInportShipService.get(id);
		}
		if (entity == null){
			entity = new OaInportShip();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaInportShip:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaInportShip oaInportShip, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(oaInportShip.getCreateStartDate() == null || oaInportShip.getCreateEndDate() == null ){
			oaInportShip.setCreateStartDate(new Date());
			oaInportShip.setCreateEndDate(new Date());
		}
		Page<OaInportShip> page = oaInportShipService.findPage(new Page<OaInportShip>(request, response), oaInportShip); 
		model.addAttribute("page", page);
		List<Office> infoList = new ArrayList<Office>();
		infoList = officeService.findAll();
		model.addAttribute("infoList",ZJGMSAUtils.getOfficeList(infoList));
		return "modules/oa/oaInportShipList";
	}
	
	@RequiresPermissions("oa:oaInportShip:view")
	@RequestMapping(value = {"searchByShipName"},method = RequestMethod.POST)
	public String searchByShipName(OaInportShip oaInportShip, HttpServletRequest request, HttpServletResponse response, Model model) {
		String shipName = request.getParameter("shipName");
		oaInportShip.setShipName(shipName);
		List<OaInportShip> inportShip = oaInportShipService.findByShipName(oaInportShip);
		OaInportShip ship = inportShip.get(0);
		JSONObject jsonObject = new JSONObject();  
		jsonObject.put("channel", ship.getChannel());  
        jsonObject.put("grid", ship.getGrid());  
        jsonObject.put("shipLocation", ship.getShipLocation());  
        jsonObject.put("shipType", ship.getShipType());  
        jsonObject.put("shipLength", ship.getShipLength());  
        jsonObject.put("departurePort", ship.getDeparturePort());  
        jsonObject.put("destinationPort", ship.getDestinationPort());  
        jsonObject.put("carryCargo", ship.getCarryCargo());  
        jsonObject.put("workStatus", ship.getWorkStatus());  
        jsonObject.put("channel", ship.getLocation());  
        jsonObject.put("grid", ship.getGrid());  
        jsonObject.put("location", ship.getLocation());  
        jsonObject.put("incharge", ship.getInchargePeople());
        jsonObject.put("telephone", ship.getTelephone());
        jsonObject.put("constructStatus", ship.getConstructStatus());
        jsonObject.put("remark", ship.getRemarks());
		PrintWriter pw = null;  
		StringBuffer sb = new StringBuffer();  
		sb.append(JSON.toJSONString(jsonObject));  
		try  
		{  
		    response.setContentType("text/html;charset=UTF-8");  
		    pw = response.getWriter();  
		    pw.write(sb.toString());  
		    pw.close();  
		}  
		catch (IOException e)  
		{  
		    e.printStackTrace();  
		}  
		return null;
	}

	@RequiresPermissions("oa:oaInportShip:view")
	@RequestMapping(value = "form")
	public String form(OaInportShip oaInportShip, Model model) {
		oaInportShip.setCurrentUser(UserUtils.getUser());
		List<OaInportShip> ships = oaInportShipService.getOfficeDefaultMessage(oaInportShip);
		if(ships.size()>0){
			oaInportShip.setChannel(ships.get(0).getChannel());
			oaInportShip.setGrid(ships.get(0).getGrid());
		}
		List<String> shipNameList = oaInportShipService.findAllShipNames(oaInportShip);
		
		model.addAttribute("shipNameList",JSON.toJSON(shipNameList));
		model.addAttribute("oaInportShip", oaInportShip);
		return "modules/oa/oaInportShipForm";
	}

	@RequiresPermissions("oa:oaInportShip:edit")
	@RequestMapping(value = "save")
	public String save(OaInportShip oaInportShip, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaInportShip)){
			return form(oaInportShip, model);
		}
		oaInportShipService.save(oaInportShip);
		addMessage(redirectAttributes, "保存每日在港船舶动态成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaInportShip/?repage";
	}
	
	@RequiresPermissions("oa:oaInportShip:edit")
	@RequestMapping(value = "delete")
	public String delete(OaInportShip oaInportShip, RedirectAttributes redirectAttributes) {
		oaInportShipService.delete(oaInportShip);
		addMessage(redirectAttributes, "删除每日在港船舶动态成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaInportShip/?repage";
	}
	
	@RequiresPermissions("oa:oaInportShip:edit")
	@RequestMapping(value = "deleteToday")
	public String deleteToday(OaInportShip oaInportShip, RedirectAttributes redirectAttributes) {
		oaInportShip.setCreateDate(new Date());
		oaInportShip.setCreateBy(oaInportShip.getCurrentUser());
		oaInportShipService.deleteToday(oaInportShip);
		addMessage(redirectAttributes, "删除今日在港船舶动态成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaInportShip/?repage";
	}

	/**
	 * 导入每日在港船舶数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaInportShip:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/oa/oaInportShip/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<OaInportShip> list = ei.getDataList(OaInportShip.class);
			for (OaInportShip ship : list){
				if(StringUtils.isNotBlank(ship.getShipName())){
					try{
						
						BeanValidators.validateWithException(validator, ship);
						ship.setCreateDate(new Date());
						ship.setCreateBy(UserUtils.getUser());
						/*if(StringUtils.isNotBlank(ship.getTelephone())){
							BigDecimal bd = new BigDecimal(ship.getTelephone()); 
							ship.setTelephone(bd.toPlainString());
						}*/
						oaInportShipService.save(ship);;
						successNum++;
					
					}catch(ConstraintViolationException ex){
						failureMsg.append("<br/>登录名 "+ship.getShipName()+" 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList){
							failureMsg.append(message+"; ");
							failureNum++;
						}
					}catch (Exception ex) {
						failureMsg.append("<br/>登录名 "+ship.getShipName()+" 导入失败："+ex.getMessage());
					}
				}
				
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条在港船舶，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条在港船舶信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入在港船舶失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/oaInportShip/list?repage";
    }
	/**
	 * 下载导入每日在港船舶数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaInportShip:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			OaInportShip ship = new OaInportShip();
            String fileName = "每日在港船舶导入模板.xls";
    		List<OaInportShip> list = Lists.newArrayList(); list.add(ship);
    		new ExportExcel("每日在港船舶动态数据", OaInportShip.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/oaInportShip?repage";
    }
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaInportShip:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OaInportShip ship, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "每日在港船舶动态"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
    		List<OaInportShip> ships = oaInportShipService.findList(ship);

            new ExportExcel("每日在港船舶动态", OaInportShip.class).setDataList(ships).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/oaInportShip?repage";
    }
}