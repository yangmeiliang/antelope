package com.lmy.antelope.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@Data
@Entity
@Table(name = "sys_menu")
public class SysMenu extends BaseDomain {

    private String url;
    private String name;
    private Integer order;
    private String parentId;
    private Boolean isPage;
}
