package com.lmy.antelope.repository;

import com.lmy.antelope.domain.entities.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangmeiliang
 * @date 2018/7/24
 */
@Repository
public interface CouponRepository extends BaseRepository<Coupon>{

    List<Coupon> findByNameLike(String name, Pageable pageRequest);

    List<Coupon> findByNameLike(String name);
}
