package com.ks.ks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KsApplication {

    public static void main(String[] args) {

        SpringApplication.run(KsApplication.class, args);
    }



}
