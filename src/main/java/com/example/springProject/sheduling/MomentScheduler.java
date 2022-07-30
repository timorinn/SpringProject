package com.example.springProject.sheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MomentScheduler {

    @Scheduled(fixedDelay = 20000L)
    public void doJob() {
        log.info("[MomentScheduler] : start job.");
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            log.info("[MomentScheduler] : something went wrong... finished the job...");
            return;
        }
        log.info("[MomentScheduler] : finished job.");
    }
}
