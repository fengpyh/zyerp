package com.cryptomind;


import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * vpeng@gmail.com
 * git@github.com:chipfixman/app.git
 */
@Slf4j
@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableTransactionManagement
public class ZyERPApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZyERPApplication.class, args);
        log.info("version 2021-09-03");
    }
}

