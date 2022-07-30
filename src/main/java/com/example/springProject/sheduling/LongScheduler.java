package com.example.springProject.sheduling;

import com.example.springProject.annotations.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LongScheduler {

    @Loggable
    @Scheduled(initialDelay = 1000L, fixedDelay = 4000L)
    public void doJob() {
        log.info("[LongScheduler] : start job.");
        try {
            Thread.sleep(12000L);
        } catch (InterruptedException e) {
            log.info("[LongScheduler] : something went wrong... finished the job...");
            return;
        }
        log.info("[LongScheduler] : finished job.");
    }

}
