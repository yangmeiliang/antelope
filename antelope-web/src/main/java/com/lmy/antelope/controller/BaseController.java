package com.lmy.antelope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@Controller
public class BaseController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/{path}.html")
    public String html(@PathVariable String path) {
        return path;
    }

}
