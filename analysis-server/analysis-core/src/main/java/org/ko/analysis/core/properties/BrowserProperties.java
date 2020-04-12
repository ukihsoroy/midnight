package org.ko.analysis.core.properties;

public class BrowserProperties {


    /**
     * login页面配置
     */
    private String loginPage = "/ko-login.html";

    /**
     * 注册页面
     */
    private String singUpUrl = "/ko-signUp.html";


    private int rememberMeSeconds = 3600;


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getSingUpUrl() {
        return singUpUrl;
    }

    public void setSingUpUrl(String singUpUrl) {
        this.singUpUrl = singUpUrl;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
