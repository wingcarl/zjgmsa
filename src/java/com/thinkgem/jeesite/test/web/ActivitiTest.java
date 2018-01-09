package com.thinkgem.jeesite.test.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class ActivitiTest {
	
	public static void createTable(){
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/jeesite?useUnicode=true&characterEncoding=utf-8");
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("123123");
		processEngineConfiguration.setDatabaseSchema(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine);
	}
	
	public static void main(String[] args) {
		createTable();
	}
}
