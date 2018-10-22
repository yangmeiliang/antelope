package com.lmy.antelope.domain.entities;

import com.alibaba.fastjson.annotation.JSONField;
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

/**
 * @author yangmeiliang
 * @date 2018/7/23
 */
@Getter
@Setter
@Entity
@Table(name = "coupon_apply_category")
public class CouponApplyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer couponId;
    private Integer category;

    @JSONField()
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "couponId", insertable = false, updatable = false)
    private Coupon coupon;
}
