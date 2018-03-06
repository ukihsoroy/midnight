package org.ko.prototype.core.helper;

import org.ko.prototype.core.bean.AppEnv;
import org.ko.prototype.core.type.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

public final class BootUtils {

	private final static Logger log = LoggerFactory.getLogger(BootUtils.class);
	
	private static final String VERSION_KEY = "app.api.version";
	private static final String NODE_KEY = "app.api.node";
//	private static final String PROFILE_KEY = "spring.profiles.active";
	
	private static final String DEFAULT_VERSION = "v1";
	private static final String DEFAULT_NODE = "node1";
//	private static final String DEFAULT_PROFILE = "dev";
	
	private static final String PID_SUFFIX = ".pid";
	
	public static void boot(String[] args, Class<?> type, AppContext context){
		String pidFile = args != null && args.length > 0 ? args[0] : null;
		
		String appId = context.getId();
		
		log.info("starting {} of app id {}", type.getCanonicalName(), appId);
		
		AppEnv.setAppId(appId);
		AppEnv.setNode(System.getProperty(NODE_KEY, DEFAULT_NODE));
		AppEnv.setVersion(System.getProperty(VERSION_KEY, DEFAULT_VERSION));
//		AppEnv.setEnvName(System.getProperty(PROFILE_KEY, DEFAULT_PROFILE));
		
		SpringApplication springApplication = new SpringApplication(type);
		
		if(pidFile == null){
			pidFile = AppEnv.getUserHome() + "/" + appId + "-" + System.getProperty(VERSION_KEY, DEFAULT_VERSION) + PID_SUFFIX;
		}
		
		springApplication.addListeners(new ApplicationPidFileWriter(pidFile));
		springApplication.run(args);
	}
	
	private BootUtils(){}
	
}

