package com.lmy.antelope.support.ensure.extensions;

import com.lmy.antelope.support.ensure.EnsureParam;
import com.lmy.antelope.support.exception.ExceptionFactory;

import java.util.Collection;

/**
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class EnsureParamCollectionExtension extends EnsureParam<Collection> {

    private Collection collection;

    public EnsureParamCollectionExtension(Collection collection) {
        super(collection);
        this.collection = collection;
    }

    public EnsureParamCollectionExtension isNotEmpty(String errMsg) {
        if (null == collection || collection.isEmpty()) {
            throw ExceptionFactory.create(errMsg);
        } else {
            return this;
        }
    }



}
