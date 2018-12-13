package com.lmy.antelope;

import com.alibaba.fastjson.JSON;
import com.lmy.antelope.domain.entities.Coupon;
import com.lmy.antelope.repository.CouponRepository;
import com.lmy.antelope.service.CouponService;
import org.hibernate.criterion.Restrictions;
import org.joor.Reflect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AntelopeApplicationTests {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void findOne() {
//        Coupon one = couponService.findOne(1);
//        System.out.println(JSON.toJSONString(one));
//
//        System.out.println(JSON.toJSONString(one.getApplyCategories()));
//        Coupon coupon = one.getApplyCategories().stream().findFirst().get().getCoupon();
//        String str = JSON.toJSONString(coupon);
//        coupon = JSONObject.parseObject(str, Coupon.class);
//        System.out.println(JSON.toJSONString(coupon));
//
//        CouponApplyCategory couponApplyCategory = couponService.findCouponApplyCategory(1);
//        System.out.println(couponApplyCategory);
//        System.out.println(JSON.toJSONString(couponApplyCategory));

        List<Coupon> byNameLike = couponRepository.findByNameLike("券%");
        System.out.println(JSON.toJSONString(byNameLike));
        byNameLike = couponRepository.findByNameLike("券%", new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "id")));
        System.out.println(JSON.toJSONString(byNameLike));
    }

    @Test
    public void test01() throws Exception {

//        MemcachedUtil.del("aaa");
//        MemcachedUtil.del("bbb");
//
//        System.out.println(MemcachedUtil.incr("aaa", 10, 1));
//        System.out.println(MemcachedUtil.incr("aaa", 20, 1));
//        System.out.println(MemcachedUtil.incr("aaa", 30, 1));
//        System.out.println(MemcachedUtil.incr("aaa", 40, 1));
//
//        System.out.println(MemcachedUtil.incr("bbb", 40, 1));

        Specification<Coupon> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.like(root.get("startTime").as(String.class), "%-07-24 %"));
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        List<Coupon> all = couponRepository.findAll(specification);
        System.out.println(JSON.toJSONString(all));

    }

    @Test
    public void test02() {

        String o = Reflect.on("java.lang.String")
                .create("Hello World")
                .call("substring", 6)
                .call("toString")
                .get();

        System.out.println(o);

    }

}
