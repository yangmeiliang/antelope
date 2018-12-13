package com.lmy.antelope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yangmeiliang
 */
@EnableEurekaServer
@SpringBootApplication
@MapperScan(value = "com.lmy.antelope.mapper", properties = {"style=normal"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
