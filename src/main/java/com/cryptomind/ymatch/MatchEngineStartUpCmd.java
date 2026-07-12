package com.cryptomind.ymatch;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;


@Slf4j
@Component
public class MatchEngineStartUpCmd implements CommandLineRunner {

    @Resource
    private TakerService takerService;

    @Override
    public void run(String... args) throws Exception {
        takerService._reload();
        log.info("SubscriberStartCmdLineRunner start completed");
    }
}
