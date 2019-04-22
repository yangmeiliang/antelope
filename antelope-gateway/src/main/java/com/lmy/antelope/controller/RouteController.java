package com.lmy.antelope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * @author yangmeiliang
 */
@Controller
public class RouteController {

    @RequestMapping(value = {
            "/index/**",
            "/tag/**",
            "/article/*",
            "/clazz/special/**",
            "/clazz/order/**",
            "/clazz/personal/**",
            "/clazz/member/**",
            "/clazz/remedial/**",
            "/clazz/course/couponCourses",
            "/clazz/course/**",
            "/audio/**",
            "/clazz/literature/**",
            "/literature/**",
            "/other/**",
            "/h5/**",
            "/group/**",
            "/iframe/clazz/remedial/**",
            "/iframe/clazz/member/pay",
            "/activity/**",
            "/live/**",
            "/clazz/courseIntegration/**",
            "/clazz/live/**",
            "/order/done/**",
            "/clazz/search",
            "/clazz/share/**",
            "/clazz/summary",
            "/invoice/*",
            "/"
    })
    public Mono<String> index(Model model) {


        return Mono.create(monoSink -> monoSink.success("index"));
    }
}
