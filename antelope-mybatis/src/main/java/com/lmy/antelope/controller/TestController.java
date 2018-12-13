package com.lmy.antelope.controller;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangmeiliang
 * @date 2018/10/18
 */
@RestController
@AllArgsConstructor
public class TestController {

    @GetMapping("/test1")
    public String test1(){
        return JSON.toJSONString("");
    }
}
