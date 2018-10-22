package com.lmy.antelope.controller;

import com.alibaba.fastjson.JSON;
import com.lmy.antelope.domain.entities.Coupon;
import com.lmy.antelope.service.CouponService;
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

    private CouponService couponService;

    @GetMapping("/test1")
    public String test1(){
        Coupon one = couponService.findOne(1);
        return JSON.toJSONString(one);
    }
}
