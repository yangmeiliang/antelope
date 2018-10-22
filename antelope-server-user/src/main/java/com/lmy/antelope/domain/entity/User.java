package com.lmy.antelope.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yangmeiliang
 * @date 2018/10/22
 */
@Data
@Entity
@Table(name = "sys_user")
public class User extends BaseDomain {
    private String username;
    private String password;
    private String realName;
}
