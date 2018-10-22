package com.lmy.antelope.controller;

import com.lmy.antelope.domain.entity.User;
import com.lmy.antelope.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author yangmeiliang
 * @date 2018/3/7
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User userInfo(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }


}
