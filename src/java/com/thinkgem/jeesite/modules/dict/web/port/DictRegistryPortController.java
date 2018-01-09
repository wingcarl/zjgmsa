/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dict.web.port;

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
import com.thinkgem.jeesite.modules.dict.entity.port.DictRegistryPort;
import com.thinkgem.jeesite.modules.dict.service.port.DictRegistryPortService;
import com.thinkgem.jeesite.modules.schedule.entity.ScheduleDetail;

/**
 * 船籍港字典信息维护Controller
 * @author dylan wang
 * @version 2017-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/dict/port/dictRegistryPort")
public class DictRegistryPortController extends BaseController {

	@Autowired
	private DictRegistryPortService dictRegistryPortService;
	
	@ModelAttribute
	public DictRegistryPort get(@RequestParam(required=false) String id) {
		DictRegistryPort entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictRegistryPortService.get(id);
		}
		if (entity == null){
			entity = new DictRegistryPort();
		}
		return entity;
	}
	
	@RequiresPermissions("dict:port:dictRegistryPort:view")
	@RequestMapping(value = {"list", ""})
	public String list(DictRegistryPort dictRegistryPort, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DictRegistryPort> page = dictRegistryPortService.findPage(new Page<DictRegistryPort>(request, response), dictRegistryPort); 
		model.addAttribute("page", page);
		return "modules/dict/port/dictRegistryPortList";
	}

	@RequiresPermissions("dict:port:dictRegistryPort:view")
	@RequestMapping(value = "form")
	public String form(DictRegistryPort dictRegistryPort, Model model) {
		model.addAttribute("dictRegistryPort", dictRegistryPort);
		return "modules/dict/port/dictRegistryPortForm";
	}

	@RequiresPermissions("dict:port:dictRegistryPort:edit")
	@RequestMapping(value = "save")
	public String save(DictRegistryPort dictRegistryPort, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dictRegistryPort)){
			return form(dictRegistryPort, model);
		}
		dictRegistryPortService.save(dictRegistryPort);
		addMessage(redirectAttributes, "保存船籍港字典信息成功");
		return "redirect:"+Global.getAdminPath()+"/dict/port/dictRegistryPort/?repage";
	}
	
	@RequiresPermissions("dict:port:dictRegistryPort:edit")
	@RequestMapping(value = "delete")
	public String delete(DictRegistryPort dictRegistryPort, RedirectAttributes redirectAttributes) {
		dictRegistryPortService.delete(dictRegistryPort);
		addMessage(redirectAttributes, "删除船籍港字典信息成功");
		return "redirect:"+Global.getAdminPath()+"/dict/port/dictRegistryPort/?repage";
	}

	/**
	 * 导入船籍港数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dict:port:dictRegistryPort:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/dict/port/dictRegistryPort/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DictRegistryPort> list = ei.getDataList(DictRegistryPort.class);
			for (DictRegistryPort sd : list){
				try{
					BeanValidators.validateWithException(validator, sd);
					dictRegistryPortService.save(sd);;
					successNum++;			
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>船籍港 "+sd.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>船籍港 "+sd.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条船籍港，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条船籍港"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入船籍港失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/dict/port/dictRegistryPort/list?repage";
    }
	
	/**
	 * 下载导入船籍港数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dict:port:dictRegistryPort:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "船籍港数据导入模板.xls";
    		List<DictRegistryPort> list = Lists.newArrayList(); list.add(new DictRegistryPort());
    		new ExportExcel("船籍港数据", DictRegistryPort.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/dict/port/dictRegistryPort/list?repage";
    }
}