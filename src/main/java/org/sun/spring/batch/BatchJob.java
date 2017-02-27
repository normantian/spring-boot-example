package org.sun.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianfei on 16/11/28.
 */
@Component
@EnableScheduling
public class BatchJob {
    public static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(cron = "*/30 * * * * ?")
    public void cronJob() {
        LOGGER.info("The time is now " + dateFormat.format(new Date()));
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}