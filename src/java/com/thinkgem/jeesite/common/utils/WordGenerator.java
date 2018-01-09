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
public class WordGenerator {
	  private static Configuration configuration = null;  
	    private static Map<String, Template> allTemplates = null;  
	      
	    static {  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("utf-8");  
	        configuration.setClassForTemplateLoading(WordGenerator.class, "com");  
	        allTemplates = new HashMap<>();   // Java 7 钻石语法  
	        try {  
	            allTemplates.put("wordModel4", configuration.getTemplate("wordModel4.ftl"));  
	            allTemplates.put("auditModel", configuration.getTemplate("auditModel.ftl"));  
	            allTemplates.put("danger", configuration.getTemplate("danger.ftl"));  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            throw new RuntimeException(e);  
	        }  
	    }  
	  
	    private WordGenerator() {  
	        throw new AssertionError();  
	    }  
	  
	    public static File createDoc(Map<?, ?> dataMap, String type) {  
	        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
	        File f = new File(name);  
	        Template t = allTemplates.get(type);  
	        try {  
	            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
	            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
	            t.process(dataMap, w);  
	            w.close();  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	            throw new RuntimeException(ex);  
	        }  
	        return f;  
	    }  
	
}
