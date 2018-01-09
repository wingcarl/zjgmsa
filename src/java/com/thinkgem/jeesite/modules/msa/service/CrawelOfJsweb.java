package com.thinkgem.jeesite.modules.msa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTML;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.msa.entity.MsaReport;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

	public class CrawelOfJsweb implements PageProcessor{
		private MsaReportService msaReportService = SpringContextHolder.getBean(MsaReportService.class);
		private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
		private List<MsaReport> reportList = new ArrayList<MsaReport>();
		@Override
		public Site getSite() {
			return site;
		}
		static List<MsaReport> messagesList = new ArrayList<MsaReport>();  
		@Override
		public void process(Page page) {
			Html h = page.getHtml();
			List<String> links = page.getHtml().xpath("//div[@class='default_pgContainer']/table/tbody/tr/td[2]/a/@href").all();
			if(links.size()>=0){
				page.addTargetRequests(links);
			}
			String dep = page.getHtml().xpath("//table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td/span[1]/text()").toString();
			String title = "";
			if("张家港海事局".equals(dep)){
				title = page.getHtml().xpath("//table/tbody/tr/td/table/tbody/tr[1]/td/text()").toString();
			}
			
			
			
			//msaReportService.updateByTitle(title,"3");
			
		}
		
		private Map<String,String> getConfig(){
			Map<String,String> configs = new HashMap<>();
			configs.put("执法支队", "2b32fcdb102447d29def506378ed8f8b");
			configs.put("办公室", "aa91c31cf5fa4782a2dc2adc83c6e37b");
			configs.put("保税区处", "72830d3bcc8e4a52afb1f90d2fc7d755");
			configs.put("指挥中心", "004b65a32fc348e1a4677f1db1397bbb");
			configs.put("交管中心", "7d6aa31e67744f67b9ae75eea12cafee");
			configs.put("VTS中心", "7d6aa31e67744f67b9ae75eea12cafee");

			configs.put("船舶处", "96d0305bb08b4036bc40b06cf8f580c2");
			configs.put("装备处", "05b5bade1ca042eda752be62bc116691");
			configs.put("南丰处", "fcde8a1044684e3791a3df602d255c1a");
			configs.put("后勤中心", "ba086e3028c74d2f8feb28dc83606eee");
			configs.put("大桥办", "dbf2de4f15b84cfa80e10c49a2712101");
			configs.put("督察处", "383e43ec9db7481db59b5619006c59a7");
			configs.put("通航处", "46337878659143ef9407067b662d2a24");
			configs.put("锦丰处", "d6766740d95d479a9a282fe35b120b3a");
			configs.put("港区处", "f15b69936e01490cad26285174df7461");
			configs.put("财务处", "a0e62e55783140c487b4764ef58a10ba");
			configs.put("党工部", "0eb136d3bd1940c0937b243118461478");
			configs.put("纪检处", "d3b795122d114812a4a742f89a3af244");
			configs.put("装备处", "05b5bade1ca042eda752be62bc116691");
			configs.put("危防处", "57d2aeefe81042238b71f55302c51a4d");
			configs.put("政务中心", "539d9e3663834df8bf6589c9514c7792");
			configs.put("人教处", "9a8436c0be814d4c9df62e5a037ca8bb");
			return configs;
		}
}
