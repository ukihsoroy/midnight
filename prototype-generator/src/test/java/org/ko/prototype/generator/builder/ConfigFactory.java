package org.ko.prototype.generator.builder;

import org.ko.prototype.generator.bean.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigFactory {

	private static final Logger log = LoggerFactory.getLogger(ConfigFactory.class);
	
	public static DBConfig getConfig(){
		DBConfig config = new DBConfig();
		config.setIp("");
		config.setPort(3306);
		config.setUser("");
		config.setPassword(".");
		config.setDb("");
		
		return config;
	}
	
	private ConfigFactory(){}
}
