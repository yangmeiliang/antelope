package com.lmy.antelope.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangmeiliang
 * @date 2018/3/22
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${testkey:test}")
    private String key;

    @GetMapping("/")
    public String test(){
        return key;
    }
}
