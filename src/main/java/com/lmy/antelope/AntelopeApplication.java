package com.lmy.antelope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangmeiliang
 */
@EnableEurekaClient
@EnableEurekaServer
@SpringBootApplication
@EnableTransactionManagement
public class AntelopeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AntelopeApplication.class, args);
    }
}
