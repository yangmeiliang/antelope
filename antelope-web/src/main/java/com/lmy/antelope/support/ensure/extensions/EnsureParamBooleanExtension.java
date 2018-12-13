package com.lmy.antelope.support.ensure.extensions;

import com.lmy.antelope.support.ensure.EnsureParam;
import com.lmy.antelope.support.exception.ExceptionFactory;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class EnsureParamBooleanExtension extends EnsureParam<Boolean> {

    private Boolean condition;

    public EnsureParamBooleanExtension(Boolean condition) {
        super(condition);
        this.condition = condition;
    }

    public EnsureParamBooleanExtension isFalse(String errMsg) {
        if (condition) {
            throw ExceptionFactory.create(errMsg);
        } else {
            return this;
        }
    }

    public EnsureParamBooleanExtension isTrue(String errMsg) {
        if (!condition) {
            throw ExceptionFactory.create(errMsg);
        } else {
            return this;
        }
    }

}
