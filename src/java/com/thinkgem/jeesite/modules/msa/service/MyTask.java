package com.thinkgem.jeesite.modules.msa.service;



import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

@Component
public class MyTask {
	
	//@Scheduled(cron="0 15 * * * ? ")
	@Scheduled(cron="0 0 9,14,16,18 * * ? ")
	public void taskCycle(){
		//cron="06 29 10,11,13,17 * * ? "
		//System.out.println("hello-world ,my scrapy happy new year!");
		/*for(int i=8;i<24;i++){
			Spider.create(new Crawel()).addUrl("http://198.17.64.9/zjghsjnw/Pages/showinfo/Moreinfo.aspx?categorynum=001001&page="+i).thread(5).run();

		}*/
		Spider.create(new Crawel()).addUrl("http://198.17.64.9/zjghsjnw/Pages/showinfo/Moreinfo.aspx?categorynum=001001").thread(5).run();
		//Spider.create(new Crawel()).addUrl("http://198.17.64.9/zjghsjnw/Pages/showinfo/Moreinfo.aspx?categorynum=001001").thread(5).run();
		//0 0 9,11,15,17 * * ? 
		//System.out.println("start");
		//Request request = new Request("http://shipping.js-msa.gov.cn/loginAction.do;jsessionid=vfb6xifdh5xhEBAgBQ");
		//request.setMethod(HttpConstant.Method.POST);
		//Map<String,Object> nameValuePair = new HashMap<String,Object>();
		//NameValuePair[] values =  new NameValue
 		//request.s
		//Spider.create(new CrawelOfJsweb()).addRequest(request).thread(5).run();

	}
}
