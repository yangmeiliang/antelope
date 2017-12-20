package com.lmy.antelope.controller;

import com.lmy.antelope.domain.PageInfo;
import com.lmy.antelope.domain.entities.SysMenu;
import com.lmy.antelope.service.SysService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@Controller
@AllArgsConstructor
@RequestMapping("/sys")
public class SysController {

    private SysService sysService;

    @GetMapping("/menu")
    public String menu() {
        return "sys/menu";
    }

    @RequestMapping("/menu_list")
    @ResponseBody
    public PageInfo<SysMenu> menuList(PageInfo<SysMenu> pageInfo) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(1L);
        sysMenu.setUrl("1231232");
        sysMenu.setName("fsdfsf");
        pageInfo.setCount(100);
        pageInfo.setData(Collections.singletonList(sysMenu));
        return pageInfo;
    }
}
