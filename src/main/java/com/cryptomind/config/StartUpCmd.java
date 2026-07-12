package com.cryptomind.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cryptomind.ymatch.TakerService;

import javax.annotation.Resource;


@Slf4j
@Component
public class StartUpCmd implements CommandLineRunner {

    @Resource
    private TakerService takerService;
    

    @Override
    public void run(String... args) throws Exception {

        
        log.info("SubscriberStartCmdLineRunner start completed");
    }
}
