package org.ko.prototype.generator.builder;

import org.ko.prototype.generator.bean.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigFactory {

	private static final Logger log = LoggerFactory.getLogger(ConfigFactory.class);
	
	public static DBConfig getConfig(){
		DBConfig config = new DBConfig();
		config.setIp("111.231.224.68");
		config.setPort(3306);
		config.setUser("root");
		config.setPassword("tiger");
		config.setDb("art-prototype");
		
		return config;
	}
	
	private ConfigFactory(){}
}
