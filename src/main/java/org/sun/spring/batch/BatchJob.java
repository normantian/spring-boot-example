package org.sun.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tianfei on 16/11/28.
 */
@Component
@EnableScheduling
public class BatchJob {

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    private AsyncWorker worker;

    public static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(cron = "*/30 * * * * ?")
    public void cronJob() {
        LOGGER.info("The time is now " + dateFormat.format(new Date()));
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }

    //這樣可以看的出來當在 Scheduled 主線程的時候還是要等他跑完 report1 後才會跑 report2，但是 AsyncWorker 部份是異步執行，不用等他跑完

    @Scheduled(fixedRate = 3000, initialDelay = 1 * 1000)
    public void report1() {
        for (int i = 0; i < 10; i++) {
            worker.work("reportCurrentTime1 - " + counter.incrementAndGet());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            }
        }
    }

    @Scheduled(fixedRate = 3000, initialDelay = 1 * 1000)
    public void report2(){
        for (int i = 0; i < 10; i++) {
            worker.work("reportCurrentTime2 - " + counter.incrementAndGet());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            }
        }
    }
}
