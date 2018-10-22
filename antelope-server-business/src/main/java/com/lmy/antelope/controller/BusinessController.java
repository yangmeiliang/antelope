package com.lmy.antelope.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangmeiliang
 * @date 2018/3/7
 */
@RestController
@AllArgsConstructor
@RequestMapping("/business")
public class BusinessController {

    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public String test03(@PathVariable Integer id) {
        return restTemplate.getForObject("http://antelope-server-user/user/" + id, String.class);
    }


}
