package com.lmy.antelope.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

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
@Accessors(chain = true)
@Table(name = "coupon_apply_course")
public class CouponApplyCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer couponId;
    private Integer courseId;
    private Integer courseType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponId", insertable = false, updatable = false)
    private Coupon coupon;
}
