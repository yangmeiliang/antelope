package com.lmy.antelope;

import com.alibaba.fastjson.JSON;
import com.lmy.antelope.domain.entities.Coupon;
import com.lmy.antelope.domain.entities.CouponApplyCourse;
import com.lmy.antelope.mapper.CouponApplyCourseMapper;
import com.lmy.antelope.mapper.CouponMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AntelopeApplicationTests {

    @Resource
    private CouponMapper couponMapper;
    @Resource
    private CouponApplyCourseMapper couponApplyCourseMapper;


    @Test
    @Transactional(rollbackFor = Exception.class)
    public void test02() {

        Coupon coupon = new Coupon()
                .setAmount(100)
                .setName("测试");
        int insert = couponMapper.insertSelective(coupon);
        System.out.println(JSON.toJSONString(coupon));
        System.out.println(insert);

        Example example = new Example(Coupon.class);
        example.createCriteria().andEqualTo("id", 1);
        List<Coupon> coupons = couponMapper.selectByExample(example);

        System.out.println(JSON.toJSONString(coupons));

        CouponApplyCourse couponApplyCourse = new CouponApplyCourse().setCouponId(1).setCourseType(2);
        insert = couponApplyCourseMapper.insertSelective(couponApplyCourse);
        System.out.println(JSON.toJSONString(couponApplyCourse));

        List<CouponApplyCourse> couponApplyCourses = couponApplyCourseMapper.selectByIds("1");
        System.out.println(JSON.toJSONString(couponApplyCourses));

        CouponApplyCourse couponApplyCourse1 = couponApplyCourseMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(couponApplyCourse1));

//        throw new RuntimeException("sdfsf");

//        String o = Reflect.on("java.lang.String")
//                .create("Hello World")
//                .call("substring", 6)
//                .call("toString")
//                .get();
//
//        System.out.println(o);


//        List<Coupon> coupons = couponMapper.selectByIds("1,2,3,4");
//        System.out.println(JSON.toJSONString(coupons));

    }

}
