package com.lmy.antelope.support.ensure.extensions;

import com.lmy.antelope.support.ensure.EnsureParam;
import com.lmy.antelope.support.exception.ExceptionFactory;

import java.util.Objects;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class EnsureParamObjectExtension extends EnsureParam<Object> {

    public EnsureParamObjectExtension(Object o) {
        super(o);
    }

    public EnsureParamObjectExtension isNull(String errMsg) {
        if (Objects.nonNull(this.tObjct)) {
            throw ExceptionFactory.create(errMsg);
        } else {
            return this;
        }
    }

    public EnsureParamObjectExtension isNotNull(String errMsg) {
        if (Objects.isNull(this.tObjct)) {
            throw ExceptionFactory.create(errMsg);
        } else {
            return this;
        }
    }


}
