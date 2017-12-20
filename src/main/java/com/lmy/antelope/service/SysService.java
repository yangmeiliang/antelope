package com.lmy.antelope.service;

import com.lmy.antelope.domain.entities.SysMenu;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
public interface SysService {

    Page<SysMenu> pageMenuList();
}
