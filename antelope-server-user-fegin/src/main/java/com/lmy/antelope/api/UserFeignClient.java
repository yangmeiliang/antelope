package com.lmy.antelope.api;

import com.lmy.antelope.domain.entity.User;
import com.lmy.config.fegin.UserFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yangmeiliang
 * @date 2018/10/22
 */
@FeignClient(value = "antelope-server-user", configuration = UserFeignConfiguration.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    User create(@RequestBody User user);
}
