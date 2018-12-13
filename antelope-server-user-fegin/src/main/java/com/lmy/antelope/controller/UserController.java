package com.lmy.antelope.controller;

import com.lmy.antelope.api.UserFeignClient;
import com.lmy.antelope.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangmeiliang
 * @date 2018/3/7
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public User userInfo(@PathVariable Integer id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/create")
    public User careate(User user) {
        return userFeignClient.create(user);
    }


}
