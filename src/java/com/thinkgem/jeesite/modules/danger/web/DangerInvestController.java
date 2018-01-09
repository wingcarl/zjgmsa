/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.danger.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferSecond;
import com.thinkgem.jeesite.modules.danger.dao.DangerInvestDetailDao;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvest;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestDetail;
import com.thinkgem.jeesite.modules.danger.entity.DangerInvestStat;
import com.thinkgem.jeesite.modules.danger.service.DangerInvestService;
import com.thinkgem.jeesite.modules.oa.entity.OaIllegal;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.vts.entity.VtsWorkData;

/**
 * 隐患排查Controller
 * @author Dylan Wang
 * @version 2017-08-31
 */
@Controller
@RequestMapping(value = "${adminPath}/danger/dangerInvest")
public class DangerInvestController extends BaseController {

	@Autowired
	private DangerInvestService dangerInvestService;

	@ModelAttribute
	public DangerInvest get(@RequestParam(required=false) String id) {
		DangerInvest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dangerInvestService.get(id);
		}
		if (entity == null){
			entity = new DangerInvest();
		}
		return entity;
	}
	
	@RequiresPermissions("danger:dangerInvest:view")
	@RequestMapping(value = {"list", ""})
	public String list(DangerInvest dangerInvest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DangerInvest> page = dangerInvestService.findPage(new Page<DangerInvest>(request, response), dangerInvest); 
		
		model.addAttribute("page", page);
		return "modules/danger/dangerInvestList";
	}

	@RequiresPermissions("danger:dangerInvest:view")
	@RequestMapping(value = "form")
	public String form(DangerInvest dangerInvest, Model model) {
		model.addAttribute("dangerInvest", dangerInvest);
		return "modules/danger/dangerInvestForm";
	}
	
	@RequiresPermissions("danger:dangerInvest:view")
	@RequestMapping(value = "dangerInvestReport")
	public String dangerInvestReport(DangerInvest dangerInvest, Model model) {
		dangerInvestService.setSpy(dangerInvest);
		model.addAttribute("dangerInvest", dangerInvest);
		return "modules/danger/dangerInvestReport";
	}
	@RequiresPermissions("danger:dangerInvest:view")
	@RequestMapping(value = "confirm")
	public String confirm(DangerInvest dangerInvest, Model model) {
		dangerInvestService.confirm(dangerInvest);
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInvest/?repage";

	}
	@RequiresPermissions("danger:dangerInvest:view")
	@RequestMapping(value = "statics")
	public String statics(DangerInvest dangerInvest, Model model) {
		List<DangerInvestStat> page = dangerInvestService.getSumData(dangerInvest); 
		
		model.addAttribute("page", page);
		model.addAttribute("v", dangerInvestService.getDangerSum(page));
		return "modules/danger/dangerInvestStatics";
	}
	
	@RequiresPermissions("danger:dangerInvest:edit")
	@RequestMapping(value = "save")
	public String save(DangerInvest dangerInvest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dangerInvest)){
			return form(dangerInvest, model);
		}
		dangerInvestService.save(dangerInvest);
		addMessage(redirectAttributes, "保存隐患排查登记表成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInvest/?repage";
	}
	
	@RequiresPermissions("danger:dangerInvest:edit")
	@RequestMapping(value = "delete")
	public String delete(DangerInvest dangerInvest, RedirectAttributes redirectAttributes) {
		dangerInvestService.delete(dangerInvest);
		addMessage(redirectAttributes, "删除隐患排查登记表成功");
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInvest/?repage";
	}
	
	@RequiresPermissions("danger:dangerInvest:edit")
    @RequestMapping(value = "exportAudit", method=RequestMethod.POST)
    public String exportAudit(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		String id = request.getParameter("sid");
		DangerInvest dangerInvest = dangerInvestService.get(id);
		InputStream fin = null;  
	     ServletOutputStream out = null;  
	     File file = null;
		try {
			 file = dangerInvestService.generateDailyReportByFreeMarker(dangerInvest);
			 fin = new FileInputStream(file);  
              
	            response.setCharacterEncoding("utf-8");  
	            response.setContentType("application/msword");  
	            
	            
	            String title = dangerInvest.getDangerCompany()+" "+".doc";
	            title = StringUtils.trim(title);
	            response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(title,"UTF-8"));  
	               
	            out = response.getOutputStream();  
	            byte[] buffer = new byte[512];  // 缓冲区  
	            int bytesToRead = -1;  
	            // 通过循环将读入的Word文件的内容输出到浏览器中  
	            while((bytesToRead = fin.read(buffer)) != -1) {  
	                out.write(buffer, 0, bytesToRead);  
	            }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  
            if(fin != null) fin.close();  
            if(out != null) out.close();  
            if(file != null) file.delete(); // 删除临时文件  
        }  
		return null;
    }
	/**
	 * 导入每日在港船舶数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("danger:dangerInvest:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DangerInvestDetail> list = ei.getDataList(DangerInvestDetail.class);
			for (DangerInvestDetail ship : list){
				if(StringUtils.isNotBlank(ship.getDangerName())){
					try{
						
						BeanValidators.validateWithException(validator, ship);
						ship.setCreateDate(new Date());
						ship.setCreateBy(UserUtils.getUser());
						/*if(StringUtils.isNotBlank(ship.getTelephone())){
							BigDecimal bd = new BigDecimal(ship.getTelephone()); 
							ship.setTelephone(bd.toPlainString());
						}*/
						//DangerInvestDetailService.save(ship);;
						successNum++;
					
					}catch(ConstraintViolationException ex){
						failureMsg.append("<br/>登录名 "+ship.getDangerName()+" 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList){
							failureMsg.append(message+"; ");
							failureNum++;
						}
					}catch (Exception ex) {
						failureMsg.append("<br/>登录名 "+ship.getDangerName()+" 导入失败："+ex.getMessage());
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
		return "redirect:" + adminPath + "/danger/DangerInvest/list?repage";
    }
	/**
	 * 下载导入每日在港船舶数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("danger:dangerInvest:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			DangerInvestDetail ship = new DangerInvestDetail();
            String fileName = "隐患模板.xls";
    		List<DangerInvestDetail> list = Lists.newArrayList(); list.add(ship);
    		new ExportExcel("隐患数据", DangerInvestDetail.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/danger/dangerInvest?repage";
    }
	@RequiresPermissions("danger:dangerInvest:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(DangerInvest dangerInvest, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "隐患数据"+DateUtils.getDate("yyyyMMdd")+".xls";
    		//Page<OaIllegal> page = oaIllegalService.findPage(new Page<OaIllegal>(request, response), oaIllegal); 
    		List<DangerInvest> list = dangerInvestService.findList(dangerInvest); 
    		List<DangerInvestDetail> did = new ArrayList<DangerInvestDetail>();
    		for(DangerInvest d : list){
    			List<DangerInvestDetail> detailList = d.getDangerInvestDetailList();
    			for(DangerInvestDetail detail : detailList){
    				detail.setDangerInvest(d);
    			}
    			did.addAll(detailList);
    		}
    		new ExportExcel("隐患数据", DangerInvestDetail.class).setDataList(did).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出隐患数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/danger/dangerInvest/?repage";
    }
}