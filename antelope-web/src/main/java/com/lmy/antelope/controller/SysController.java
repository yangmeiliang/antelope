package com.lmy.antelope.controller;

import com.lmy.antelope.domain.PageInfo;
import com.lmy.antelope.domain.entities.SysMenu;
import com.lmy.antelope.service.SysService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys")
public class SysController {

    private SysService sysService;

    @PostMapping("/menus")
    public PageInfo<SysMenu> menuList(@RequestBody PageInfo<SysMenu> pageInfo) {

        Page<SysMenu> page = sysService.pageMenuList();

        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(1);
        sysMenu.setUrl("1231232");
        sysMenu.setName("fsdfsf");
        pageInfo.setCount(100);
        pageInfo.setData(Collections.singletonList(sysMenu));
        return pageInfo;
    }
}
