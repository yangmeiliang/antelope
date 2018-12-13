package com.lmy.antelope.domain.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer amount;

    private Integer quantity;
    /**
     * 每个用户可领取张数
     */
    private Integer receiveLimit;
    /**
     * 适用时间类型：1:时间段,2:领取后生效
     */
    private Integer applyTimeType;
    /**
     * 领取后生效时长 (单位:小时)
     */
    private Integer applyTimeDuration;
    private Date startTime;
    private Date endTime;
    private String creator;
    private String modifier;
    @Column(insertable = false)
    private Boolean deleted;

}
