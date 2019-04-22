package com.lmy.antelope.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

/**
 * @author ccy
 */
public class PriceUtil {

    public static String priceFormat(BigDecimal price) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(price);
    }

    public static String priceFormat(String price) {
        return priceFormat(new BigDecimal(price));
    }

    public static String cent2Yuan(Integer cent) {
        return Optional.ofNullable(cent).map(c -> cent.doubleValue() / 100).map(String::valueOf).map(PriceUtil::priceFormat).orElse("0.00");
    }


    public static Integer yuan2Cent(String yuan) {
        if (StringUtils.isEmpty(yuan)) {
            return 0;
        }
        return new BigDecimal(yuan).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static String minus(String minuend, String subtrahend) {
        int amount = yuan2Cent(minuend) - yuan2Cent(subtrahend);
        if (amount < 0) {
            return "0";
        }
        return cent2Yuan(amount);
    }

    public static String add(String... nums) {
        int init = 0;
        for (String num : nums) {
            init += yuan2Cent(num);
        }
        return cent2Yuan(init);
    }
}
