package com.lmy.config.fegin;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangmeiliang
 * @date 2018/10/22
 */
@Configuration
public class UserFeignConfiguration {

    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }
}
