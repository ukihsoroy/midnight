package org.ko.prototype.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SupportRunner implements CommandLineRunner {

    private static final Logger _LOGGER = LoggerFactory.getLogger(SupportRunner.class);

    @Override
    public void run(String... args) throws Exception {
        _LOGGER.info("Thread {} run master datasource.", Thread.currentThread().getName());
    }
}
