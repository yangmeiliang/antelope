package com.lmy.antelope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author yangmeiliang
 */
@SpringBootApplication
public class AntelopeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AntelopeApplication.class, args);
    }
}
