package com.lmy.antelope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AntelopeApplicationTests {

    @Autowired(required = false)
    private RedisTemplate<String, String> template;

    @Test
    public void load() {
        HashOperations<String, Object, String> opsForHash = template.opsForHash();
        String key = "key01";
        String hashKey = "hashKey01";
        String value = "value1";
        opsForHash.put(key, hashKey, value);
        System.out.println(opsForHash.get(key, hashKey));
    }

}
