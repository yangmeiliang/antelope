package com.lmy.antelope.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yangmeiliang
 * @date 2018/7/23
 */
@Getter
@Setter
@Entity
@Table(name = "coupon_user_record")
public class CouponUserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer couponId;
    private Long writeOffCode;
    private String username;
    /**
     * 类型：0:用户自己领取;1:管理后台发放
     */
    private Integer type;
    /**
     * 状态：0:未使用;1:已使用
     */
    private Integer status;
    private Date startTime;
    private Date endTime;
    private Boolean deleted;
    private String modifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponId", insertable = false, updatable = false)
    private Coupon coupon;
}
