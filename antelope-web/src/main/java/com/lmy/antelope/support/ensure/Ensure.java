package com.lmy.antelope.support.ensure;

import com.lmy.antelope.support.ensure.extensions.EnsureParamBooleanExtension;
import com.lmy.antelope.support.ensure.extensions.EnsureParamCollectionExtension;
import com.lmy.antelope.support.ensure.extensions.EnsureParamObjectExtension;
import com.lmy.antelope.support.ensure.extensions.EnsureParamStringExtension;

import java.util.Collection;

/**
 * 断言工具类
 *
 * @author yangmeiliang
 * @date 2018/6/11
 */
public class Ensure {

    public static EnsureParamObjectExtension that(Object tObject) {
        return new EnsureParamObjectExtension(tObject);
    }

    public static EnsureParamBooleanExtension that(boolean tObject) {
        return new EnsureParamBooleanExtension(tObject);
    }


    public static <T extends Boolean> EnsureParamBooleanExtension that(T tObject) {
        return new EnsureParamBooleanExtension(tObject);
    }

    public static EnsureParamStringExtension that(String tObject) {
        return new EnsureParamStringExtension(tObject);
    }

    public static EnsureParamCollectionExtension that(Collection collection) {
        return new EnsureParamCollectionExtension(collection);
    }

}
