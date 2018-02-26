package org.ko.prototype.data.master;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * starter 程序入口
 */
public class MasterDatasourceRunner implements CommandLineRunner {

    private static final Logger _LOGGER = LoggerFactory.getLogger(MasterDatasourceRunner.class);

    @Override
    public void run(String... args) throws Exception {
        _LOGGER.info("Thread {} run master datasource.", Thread.currentThread().getName());
    }
}
