package com.lmy.antelope.config;

import com.lmy.antelope.util.PriceUtil;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author yangmeiliang
 */
@Component
public class PriceFormatter implements Formatter<Integer>, Serializable {

    @Override
    public Integer parse(String text, Locale locale) {
        return PriceUtil.yuan2Cent(text);
    }

    @Override
    public String print(Integer value, Locale locale) {
        return PriceUtil.cent2Yuan(value);
    }
}