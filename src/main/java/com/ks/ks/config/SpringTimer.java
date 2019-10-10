package com.ks.ks.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpringTimer {

    private int count=0;
    //每3秒执行一次
    @Scheduled(fixedRate = 3000)
    public void timerRate() {
        System.out.println(new Date());
    }

    @Scheduled(cron = "0 0/15 8-22 * * * ")
    public void timerCron() {
        System.out.println("current time : "+ new Date());
    }
}
