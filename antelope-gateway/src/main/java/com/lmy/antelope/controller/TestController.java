package com.lmy.antelope.controller;

import com.lmy.antelope.config.PriceStyle;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yangmeiliang
 */
@RestController
public class TestController {

    @Data
    public static class TestDTO {
        @PriceStyle
        private String amount;

        private Integer percent;

        private Date time;
    }

    @Data
    public static class TestVO {
        @PriceStyle
        private Integer amount;
        @PriceStyle
        private Integer price;
        @DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
        private Date time;
        @NumberFormat(style = NumberFormat.Style.PERCENT)
        private Integer percent;
    }

    @PostMapping("/test")
    public TestVO test(@RequestBody TestDTO param) {
        TestVO testVO = new TestVO();
        testVO.setTime(param.getTime());
        testVO.setPrice(19999);
        return testVO;
    }

    public static void main(String[] args) {
        String value = "1.9";

        double v = Double.valueOf(value);


        System.out.println(v);
        System.out.println((int)(v*100));
        System.out.println(Double.valueOf("1.90"));
    }
}
