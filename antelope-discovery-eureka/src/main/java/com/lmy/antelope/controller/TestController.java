package com.lmy.antelope.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangmeiliang
 * @date 2018/3/7
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/01")
    public String test01() {
        return "01";
    }


}
