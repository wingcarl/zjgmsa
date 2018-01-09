/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarkers工具类
 * @author ThinkGem
 * @version 2013-01-15
 */
public class FreeMarkers {
	  private Configuration configuration = null;    
      
	  public FreeMarkers(){    
	        configuration = new Configuration();    
	        configuration.setDefaultEncoding("UTF-8");    
	    }    
	public static String renderString(String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString), new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
	
	 public void createWord(List<Map<String, Object>> dataMap){    
		    //List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
	        //dataMap = getData();    
	        Map<String,Object> key = new HashMap<String,Object>();
	        key.put("sequence", dataMap);
	        configuration.setClassForTemplateLoading(this.getClass(), "/com");  //FTL文件所存在的位置    
	        configuration.setDefaultEncoding("UTF-8");    
	        Template t=null;    
	        try {    
	        	configuration.setDirectoryForTemplateLoading(new File("E:/"));
	        	t= configuration.getTemplate("wordModel2.ftl"); //文件名    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	        File outFile = new File("E:/outFilessa"+Math.random()*10000+".doc");  //生成文件的路径  
	        Writer out = null;    
	        try {    
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));    
	        } catch (FileNotFoundException e1) {    
	            e1.printStackTrace();    
	        }    
	             
	        try {    
	            t.process(key, out);   
	            out.flush(); 
	            out.close();
	        } catch (TemplateException e) {    
	            e.printStackTrace();    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	    }    
	    //这里赋值的时候需要注意,xml中需要的数据你必须提供给它,不然会报找不到某元素错的.  
	   public List<Map<String, Object>> getData() {    
		   List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		 for(int i=0;i<5;i++){
			 HashMap<String,Object> keyMap = new HashMap<String,Object>();
			   keyMap.put("number",i);
			   keyMap.put("hxt", "海巡06809");
			   keyMap.put("cruisePeople","王浩宇，贾亚伯");
			   keyMap.put("cruiseTime", "1410-1600");
			   keyMap.put("cruiseTotalTime",564);
			   keyMap.put("cruiseArea", "双涧沙水域");
			   keyMap.put("solveProblem","1、查双狮水域正常，浏海沙水道通航秩序正常。\r\n2、查航道局码头施工作业正常。\r\n3、现场组织PEDERAL KIBUNE 进福南\r\n4、福中水道巡航F1浮恒裕涉嫌违章通知其接受处理。\r\n5. 43-42浮查锚泊船队 鲁济宁拖。2003,2736,2266,0034.\r\n6，建友44浮划江进福南过早，现场提醒。");
			   keyMap.put("remark", "4号夜航");
			 
			   list1.add(keyMap);
		 }
			  

		  
		   
		  

		   return list1;
//	      dataMap.put("number", 1);    
//	      dataMap.put("content", "内容"+2);    
	            
	        
	    }    
	public static void main(String[] args) throws IOException {
//		// renderString
//		Map<String, String> model = com.google.common.collect.Maps.newHashMap();
//		model.put("userName", "calvin");
//		String result = FreeMarkers.renderString("hello ${userName}", model);
//		System.out.println(result);
//		// renderTemplate
//		Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
//		Template template = cfg.getTemplate("testTemplate.ftl");
//		String result2 = FreeMarkers.renderTemplate(template, model);
//		System.out.println(result2);
		
//		Map<String, String> model = com.google.common.collect.Maps.newHashMap();
//		model.put("userName", "calvin");
//		String result = FreeMarkers.renderString("hello ${userName} ${r'${userName}'}", model);
//		System.out.println(result);
		//FreeMarkers fm = new FreeMarkers();    
	   // fm.createWord();    
		System.out.println(StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeHtml4("维护海昌在航道抛锚主机故障&amp;middot;。巫山河口驱赶3条三无船，纠正皖二航718锚位，巫山河口驱赶一条抛泥船。c")));
	}
	
}
