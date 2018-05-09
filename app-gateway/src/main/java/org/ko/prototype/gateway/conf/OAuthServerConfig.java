package org.ko.prototype.gateway.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 开启授权服务器
 */
@Configuration
@EnableAuthorizationServer
public class OAuthServerConfig {}
