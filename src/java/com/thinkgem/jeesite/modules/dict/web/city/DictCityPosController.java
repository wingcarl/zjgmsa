/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.web.city;

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

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.dict.entity.city.DictCityPos;
import com.thinkgem.jeesite.modules.dict.service.city.DictCityPosService;

/**
 * 城市经纬度字典管理Controller
 * @author dylan wang
 * @version 2017-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/dict/city/dictCityPos")
public class DictCityPosController extends BaseController {

	@Autowired
	private DictCityPosService dictCityPosService;
	
	@ModelAttribute
	public DictCityPos get(@RequestParam(required=false) String id) {
		DictCityPos entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictCityPosService.get(id);
		}
		if (entity == null){
			entity = new DictCityPos();
		}
		return entity;
	}
	
	@RequiresPermissions("dict:city:dictCityPos:view")
	@RequestMapping(value = {"list", ""})
	public String list(DictCityPos dictCityPos, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DictCityPos> page = dictCityPosService.findPage(new Page<DictCityPos>(request, response), dictCityPos); 
		model.addAttribute("page", page);
		return "modules/dict/city/dictCityPosList";
	}

	@RequiresPermissions("dict:city:dictCityPos:view")
	@RequestMapping(value = "form")
	public String form(DictCityPos dictCityPos, Model model) {
		model.addAttribute("dictCityPos", dictCityPos);
		return "modules/dict/city/dictCityPosForm";
	}

	@RequiresPermissions("dict:city:dictCityPos:edit")
	@RequestMapping(value = "save")
	public String save(DictCityPos dictCityPos, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dictCityPos)){
			return form(dictCityPos, model);
		}
		dictCityPosService.save(dictCityPos);
		addMessage(redirectAttributes, "保存城市经纬度字典成功");
		return "redirect:"+Global.getAdminPath()+"/dict/city/dictCityPos/?repage";
	}
	
	@RequiresPermissions("dict:city:dictCityPos:edit")
	@RequestMapping(value = "delete")
	public String delete(DictCityPos dictCityPos, RedirectAttributes redirectAttributes) {
		dictCityPosService.delete(dictCityPos);
		addMessage(redirectAttributes, "删除城市经纬度字典成功");
		return "redirect:"+Global.getAdminPath()+"/dict/city/dictCityPos/?repage";
	}
	/**
	 * 导入城市经纬度数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dict:city:DictCityPos:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/dict/city/DictCityPos/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DictCityPos> list = ei.getDataList(DictCityPos.class);
			for (DictCityPos sd : list){
				try{
					BeanValidators.validateWithException(validator, sd);
					dictCityPosService.save(sd);;
					successNum++;			
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>城市经纬度 "+sd.getCounty()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>城市经纬度 "+sd.getCounty()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条城市经纬度，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条城市经纬度"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入城市经纬度失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/dict/city/DictCityPos/list?repage";
    }
	
	/**
	 * 下载导入城市经纬度数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dict:city:DictCityPos:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "城市经纬度数据导入模板.xls";
    		List<DictCityPos> list = Lists.newArrayList(); list.add(new DictCityPos());
    		new ExportExcel("城市经纬度数据", DictCityPos.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/dict/city/DictCityPos/list?repage";
    }
}