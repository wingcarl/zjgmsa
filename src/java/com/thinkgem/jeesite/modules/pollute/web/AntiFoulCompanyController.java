/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pollute.web;

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
import com.thinkgem.jeesite.modules.pollute.entity.AntiFoulCompany;
import com.thinkgem.jeesite.modules.pollute.service.AntiFoulCompanyService;
/**
 * 防污染配置Controller
 * @author Dylan Wang
 * @version 2017-12-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pollute/antiFoulCompany")
public class AntiFoulCompanyController extends BaseController {

	@Autowired
	private AntiFoulCompanyService antiFoulCompanyService;
	
	@ModelAttribute
	public AntiFoulCompany get(@RequestParam(required=false) String id) {
		AntiFoulCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = antiFoulCompanyService.get(id);
		}
		if (entity == null){
			entity = new AntiFoulCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("pollute:antiFoulCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(AntiFoulCompany antiFoulCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AntiFoulCompany> page = antiFoulCompanyService.findPage(new Page<AntiFoulCompany>(request, response), antiFoulCompany); 
		model.addAttribute("page", page);
		return "modules/pollute/antiFoulCompanyList";
	}

	@RequiresPermissions("pollute:antiFoulCompany:view")
	@RequestMapping(value = "form")
	public String form(AntiFoulCompany antiFoulCompany, Model model) {
		model.addAttribute("antiFoulCompany", antiFoulCompany);
		return "modules/pollute/antiFoulCompanyForm";
	}

	@RequiresPermissions("pollute:antiFoulCompany:edit")
	@RequestMapping(value = "save")
	public String save(AntiFoulCompany antiFoulCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, antiFoulCompany)){
			return form(antiFoulCompany, model);
		}
		antiFoulCompanyService.save(antiFoulCompany);
		addMessage(redirectAttributes, "保存防污染配置成功");
		return "redirect:"+Global.getAdminPath()+"/pollute/antiFoulCompany/?repage";
	}
	
	@RequiresPermissions("pollute:antiFoulCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(AntiFoulCompany antiFoulCompany, RedirectAttributes redirectAttributes) {
		antiFoulCompanyService.delete(antiFoulCompany);
		addMessage(redirectAttributes, "删除防污染配置成功");
		return "redirect:"+Global.getAdminPath()+"/pollute/antiFoulCompany/?repage";
	}
	
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pollute:antiFoulCompany:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<AntiFoulCompany> list = ei.getDataList(AntiFoulCompany.class);
			for (AntiFoulCompany company : list){
				BeanValidators.validateWithException(validator, company);
				antiFoulCompanyService.save(company);;
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pollute/antiFoulCompany/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pollute:antiFoulCompany:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "导入模板.xls";
    		List<AntiFoulCompany> list = Lists.newArrayList(); list.add(new AntiFoulCompany());
    		new ExportExcel("数据", AntiFoulCompany.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pollute/antiFoulCompany/list?repage";
    }

}