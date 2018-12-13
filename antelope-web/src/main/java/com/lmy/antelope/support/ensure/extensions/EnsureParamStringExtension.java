package com.lmy.antelope.support.ensure.extensions;

import com.lmy.antelope.support.ensure.EnsureParam;
import com.lmy.antelope.support.exception.ExceptionFactory;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class EnsureParamStringExtension extends EnsureParam<Object> {
    private String string;

    public EnsureParamStringExtension(String string) {
        super(string);
        this.string = string;
    }

    public EnsureParamStringExtension isNotBlank(String errMsg) {
        if (StringUtils.isBlank(this.string)) {
            throw ExceptionFactory.create(errMsg);
        } else {
            return this;
        }
    }
}
