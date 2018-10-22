package com.lmy.antelope.service;

import com.lmy.antelope.domain.entities.Coupon;
import com.lmy.antelope.domain.entities.CouponApplyCategory;
import com.lmy.antelope.repository.CouponApplyCategoryRepository;
import com.lmy.antelope.repository.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangmeiliang
 * @date 2018/7/24
 */
@Service
@AllArgsConstructor
public class CouponService {

    private CouponRepository couponRepository;
    private CouponApplyCategoryRepository applyCategoryRepository;

    public Coupon findOne(Integer id){
        return couponRepository.getOne(id);
    }

    public CouponApplyCategory findCouponApplyCategory(Integer id){
        return applyCategoryRepository.getOne(id);
    }


}
