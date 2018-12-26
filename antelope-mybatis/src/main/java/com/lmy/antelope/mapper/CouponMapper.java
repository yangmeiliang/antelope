package com.lmy.antelope.mapper;

import com.lmy.antelope.BaseMapper;
import com.lmy.antelope.domain.entities.Coupon;
import org.apache.ibatis.annotations.Select;

public interface CouponMapper extends BaseMapper<Coupon> {

    @Select("select * from coupon where id=#{id}")
    Coupon findById(Integer id);


}