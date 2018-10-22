package com.lmy.antelope.config;

import lombok.Getter;
import lombok.Setter;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangmeiliang
 * @date 2018/8/15
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "memcached")
public class MemcachedConfiguration {

    private String defaultServers;

    @Bean
    public MemcachedClient memcachedClientBuilder() throws Exception {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(defaultServers);
        builder.setConnectTimeout(5000);
        return builder.build();
    }
}
