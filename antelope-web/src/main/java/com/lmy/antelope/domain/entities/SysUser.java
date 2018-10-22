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
@Table(name = "sys_user")
public class SysUser extends BaseDomain{

    private String username;
    private String password;
    private String realName;
}
