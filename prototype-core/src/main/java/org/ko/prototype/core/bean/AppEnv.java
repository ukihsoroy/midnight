package org.ko.prototype.core.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class AppEnv {

    private static final Logger log = LoggerFactory.getLogger(AppEnv.class);

    private static String appId;

    private static String node;

    private static String version;

    private static String envName;

    private AppEnv(){}

    public static String getUserHome(){
        return System.getProperty("user.home");
    }

    public static String getOSName(){
        return System.getProperty("os.name");
    }

    public static String getTmpDir(){
        return System.getProperty("java.io.tmpdir");
    }

    public static String getHostName(){
        String host = "";

        try{
            host = InetAddress.getLocalHost().getHostName();
        }catch(Exception e){
            log.warn("failed to get localhost host name {}", e);
        }

        return host;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        AppEnv.appId = appId;
    }

    public static String getNode() {
        return node;
    }

    public static void setNode(String node) {
        AppEnv.node = node;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        AppEnv.version = version;
    }

    public static String getEnvName() {
        return envName;
    }

    public static void setEnvName(String envName) {
        AppEnv.envName = envName;
    }
}
