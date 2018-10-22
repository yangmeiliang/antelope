package com.lmy.antelope.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author yangmeiliang
 * @date 2018/9/28
 */
@Getter
@Setter
@MappedSuperclass
public class BaseDomain {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(insertable = false, updatable = false)
    private Date createTime;
    @Column(insertable = false, updatable = false)
    private Date updateTime;
}
