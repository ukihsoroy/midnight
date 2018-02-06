package org.ko.prototype.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
public class PrototypeSSOServer {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeSSOServer.class, args);
    }
}
