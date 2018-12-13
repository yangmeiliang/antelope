package com.lmy.antelope.support.exception;

import com.lmy.antelope.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class ExceptionFactory {

    private static ExceptionDefinitions exceptionDefinitions;

    static {
        exceptionDefinitions = SpringUtil.getBean(ExceptionDefinitions.class);
    }

    public static BusinessException create(String errMsg) {
        if (StringUtils.isNumeric(errMsg)) {
            //如果是错误码， 从配置文件中获取错误信息
            return new BusinessException(Integer.valueOf(errMsg), exceptionDefinitions.valueOf(errMsg));
        }
        return new BusinessException(errMsg);
    }

    public static BusinessException create(String message, Object... args) {
        return new BusinessException(String.format(message, args));
    }
}
