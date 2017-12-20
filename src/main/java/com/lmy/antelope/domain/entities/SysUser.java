package com.lmy.antelope.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@Data
@Entity
@NoArgsConstructor
public class SysUser {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String realName;
    @Column(insertable = false, updatable = false)
    private Date createTime;
    @Column(insertable = false, updatable = false)
    private Date updateTime;
}
