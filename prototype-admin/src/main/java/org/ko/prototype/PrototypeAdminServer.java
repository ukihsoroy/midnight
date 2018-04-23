package org.ko.prototype;

import org.ko.prototype.annotation.PrototypeApplication;
import org.springframework.boot.SpringApplication;

@PrototypeApplication
public class PrototypeAdminServer {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeAdminServer.class, args);
    }
}
