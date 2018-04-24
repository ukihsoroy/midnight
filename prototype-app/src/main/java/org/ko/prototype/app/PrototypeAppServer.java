package org.ko.prototype.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.ko")
public class PrototypeAppServer {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeAppServer.class, args);
    }
}
