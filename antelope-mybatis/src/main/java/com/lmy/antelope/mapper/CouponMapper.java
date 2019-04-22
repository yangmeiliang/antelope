package com.lmy.antelope.mapper;

import com.lmy.antelope.config.mybatis.BaseMapper;
import com.lmy.antelope.domain.entities.Coupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CouponMapper extends BaseMapper<Coupon> {

    @Select("select * from coupon where id=#{id}")
    Coupon findById(Integer id);

    int updateTest(Coupon param);

    @Update({"<script>",
            "UPDATE coupon",
            "<set>id = id,",
            "<if test='name != null'>name = #{name},</if>",
            "<if test='amount != null'>amount = #{amount},</if>",
            "<if test='quantity != null'>quantity = #{quantity},</if>",
            "<if test='receiveLimit != null'>receive_limit = #{receiveLimit},</if>",
            "<if test='applyTimeType != null'>apply_time_type = #{applyTimeType},</if>",
            "<if test='applyTimeDuration != null'>apply_time_duration = #{applyTimeDuration},</if>",
            "<if test='startTime != null'>start_time = #{startTime},</if>",
            "<if test='endTime != null'>end_time = #{endTime},</if>",
            "<if test='creator != null'>creator = #{creator},</if>",
            "<if test='modifier != null'>modifier = #{modifier},</if>",
            "<if test='deleted != null'>deleted = #{deleted},</if>",
            "</set>",
            "<where>AND id = #{id}</where>",
            "</script>"})
    int updateTest2(Coupon param);


}