/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.affair.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.ZJGMSAUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.affair.entity.AffairAudit;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferDetail;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferInfo;
import com.thinkgem.jeesite.modules.affair.entity.AffairTransferSecond;
import com.thinkgem.jeesite.modules.affair.service.AffairTransferDetailService;
import com.thinkgem.jeesite.modules.affair.service.AffairTransferInfoService;
import com.thinkgem.jeesite.modules.affair.service.AffairTransferSecondService;
import com.thinkgem.jeesite.modules.oa.entity.OaInportShip;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;

/**
 * 水水中转货物核销登记Controller
 * @author Dylan Wang
 * @version 2017-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/affair/affairTransferInfo")
public class AffairTransferInfoController extends BaseController {

	@Autowired
	private AffairTransferInfoService affairTransferInfoService;
	@Autowired
	private AffairTransferSecondService affairTransferSecondService;
	@Autowired
	private AffairTransferDetailService affairTransferDetailService;
	@Autowired
	private OfficeService officeService;
	@ModelAttribute
	public AffairTransferInfo get(@RequestParam(required=false) String id) {
		AffairTransferInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = affairTransferInfoService.get(id);
		}
		if (entity == null){
			entity = new AffairTransferInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("affair:affairTransferInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(AffairTransferSecond affairTransferSecond, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AffairTransferSecond> page = affairTransferSecondService.findPage(new Page<AffairTransferSecond>(request, response), affairTransferSecond); 
		model.addAttribute("page", page);
		List<Office> infoList = new ArrayList<Office>();
		infoList = officeService.findAll();
		model.addAttribute("infoList",ZJGMSAUtils.getOfficeList(infoList));
		return "modules/affair/affairTransferInfoList";
	}

	@RequiresPermissions("affair:affairTransferInfo:view")
	@RequestMapping(value = "form")
	public String form(AffairTransferInfo affairTransferInfo, Model model) {
		model.addAttribute("affairTransferInfo", affairTransferInfo);
		return "modules/affair/affairTransferInfoForm";
	}
	
	@RequiresPermissions("affair:affairTransferInfo:edit")
	@RequestMapping(value="formSecondDetail")
	public String formSecondDetail(HttpServletRequest request, AffairTransferSecond affairTransferSecond,Model model){
		String id = request.getParameter("id");
		AffairTransferInfo affairTransferInfo = affairTransferInfoService.get(new AffairTransferInfo(id));
		model.addAttribute("affairTransferInfo", affairTransferInfo);
		String secondId = request.getParameter("secondId");
		affairTransferSecond = affairTransferSecondService.get(secondId);
		model.addAttribute("affairTransferSecond",affairTransferSecond);
		return "modules/affair/affairTransferSecondForm";
	}
	
	@RequiresPermissions("affair:affairTransferInfo:edit")
	@RequestMapping(value = "formAudit")
	public String formAudit(AffairTransferDetail affairTransferDetail,AffairTransferSecond affairTransferSecond,Model model) {
		AffairTransferDetail detail = affairTransferDetailService.get(affairTransferDetail);
		affairTransferSecond = affairTransferSecondService.get(detail.getSecond().getId());
		affairTransferSecond.setAct(affairTransferDetail.getAct());
		AffairTransferInfo affairTransferInfo = affairTransferInfoService.get(affairTransferSecond.getFirstId());
		model.addAttribute("affairTransferDetail",affairTransferDetail);
		model.addAttribute("affairTransferSecond",affairTransferSecond);
		model.addAttribute("affairTransferInfo",affairTransferInfo);
		return "modules/affair/affairTransferAudit";
	}
	
	@RequiresPermissions("affair:affairTransferInfo:edit")
	@RequestMapping(value = "save")
	public String save(AffairTransferInfo affairTransferInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, affairTransferInfo)){
			return form(affairTransferInfo, model);
		}
		affairTransferInfoService.save(affairTransferInfo);
		addMessage(redirectAttributes, "保存水水中转货物核销登记薄成功");
		return "redirect:"+Global.getAdminPath()+"/affair/affairTransferInfo/?repage";
	}
	
	@RequiresPermissions("affair:affairTransferInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(AffairTransferInfo affairTransferInfo, RedirectAttributes redirectAttributes) {
		affairTransferInfoService.delete(affairTransferInfo);
		addMessage(redirectAttributes, "删除水水中转货物核销登记薄成功");
		return "redirect:"+Global.getAdminPath()+"/affair/affairTransferInfo/?repage";
	}
	
	@RequiresPermissions("affair:affairTransferInfo:edit")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AffairTransferSecond ship, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "水水中转核销台账"+DateUtils.getDate("yyyyMMddHH")+".xls";
    		List<AffairTransferSecond> ships = affairTransferSecondService.findList(ship);

            new ExportExcel("水水中转核销台账", AffairTransferSecond.class).setDataList(ships).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/affair/affairTransferInfo/list?repage";
    }
	
	@RequiresPermissions("affair:affairTransferInfo:edit")
    @RequestMapping(value = "exportAudit", method=RequestMethod.POST)
    public String exportAudit(AffairTransferSecond second,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		String id = request.getParameter("sid");
		AffairTransferInfo ship = affairTransferInfoService.get(id);
		InputStream fin = null;  
	     ServletOutputStream out = null;  
	     File file = null;
		try {
			 file = affairTransferInfoService.generateDailyReportByFreeMarker(ship);
			 fin = new FileInputStream(file);  
              
	            response.setCharacterEncoding("utf-8");  
	            response.setContentType("application/msword");  
	            
	            
	            String title = ship.getFirstShipName()+" "+ship.getApplyOffice()+".doc";
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

}