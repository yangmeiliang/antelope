package com.lmy.antelope;

import com.lmy.antelope.domain.entities.SysUser;
import com.lmy.antelope.repository.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AntelopeApplicationTests {

    @Autowired(required = false)
    private SysUserRepository repository;


    @Test
    public void contextLoads() {
        List<SysUser> list = repository.findAll();
        System.out.println(list.size());
    }

}
