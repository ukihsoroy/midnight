package org.ko.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * description: ScheduleApplication <br>
 * date: 2020/4/14 9:59 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
//定时任务开启
@EnableScheduling
@SpringBootApplication
public class ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }

}
