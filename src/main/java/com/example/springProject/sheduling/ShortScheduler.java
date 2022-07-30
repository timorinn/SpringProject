package com.example.springProject.sheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShortScheduler {

    @Scheduled(initialDelay = 1000L, fixedDelay = 2000L)
    public void doJob() {
        log.info("[ShortScheduler] : start job.");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.info("[ShortScheduler] : something went wrong... finished the job...");
            return;
        }
        log.info("[ShortScheduler] : finished job.");
    }
}
