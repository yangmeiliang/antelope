package com.lmy.antelope.utils;

import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yangmeiliang
 */
public class MapperUtils {

    public static Mapper MAPPER;

    static {
        MAPPER = MapperFactory.getCopyByRefMapper();
        MAPPER.mapClass(Integer.class, Boolean.class).customMapping((a, b) -> b = Objects.equals(a, 1)).register();
        MAPPER.mapClass(Boolean.class, Integer.class).customMapping((a, b) -> b = (a != null && a) ? 1 : 0).register();
    }

    private static <A, B> Mapper register(Class<A> sourceClass, Class<B> targetClass) {
        return register(sourceClass, targetClass, false);
    }

    private static <A, B> Mapper register(Class<A> sourceClass, Class<B> targetClass, boolean nullable) {
        List<String> excludes = new ArrayList<>();
        List<Field> sourceFieldsList = FieldUtils.getAllFieldsList(sourceClass);
        List<Field> targetFieldsList = FieldUtils.getAllFieldsList(targetClass);
        //排除字段名字一样，类型不一致的字段
        for (Field field : sourceFieldsList) {
            String fieldName = field.getName();
            for (Field tField : targetFieldsList) {
                String tFieldName = tField.getName();
                if(Objects.equals(fieldName, tFieldName) && !Objects.equals(field.getType(), tField.getType())){
                    excludes.add(fieldName);
                    break;
                }
            }
        }
        return MAPPER.mapClass(sourceClass, targetClass).exclude(excludes.toArray(new String[0])).mapOnNull(nullable).register();
    }

    /**
     * 对象数据转换
     *
     * @param sourceObj  源对象
     * @param targetType 目标对象类型
     * @param <A>        源对象范型
     * @param <B>        目标对象范型
     * @return 目标对象
     */
    public static <A, B> B map(A sourceObj, Class<B> targetType) {
        if (sourceObj == null) {
            return null;
        }
        return register(sourceObj.getClass(), targetType).map(sourceObj, targetType);
    }

    /**
     * 将a对象的值赋给b对象
     *
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     */
    public static void map(Object sourceObj, Object targetObj) {
        map(sourceObj, targetObj, false);
    }

    /**
     * 将a对象的值赋给b对象
     *
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     * @param nullable  是否强制赋空值
     */
    public static void map(Object sourceObj, Object targetObj, boolean nullable) {
        register(sourceObj.getClass(), targetObj.getClass(), nullable).map(sourceObj, targetObj);
    }

    /**
     * 对象数据转换
     *
     * @param list       源对象list
     * @param targetType 目标对象类型
     * @param <A>        源对象范型
     * @param <B>        目标对象范型
     * @return 目标对象list
     */
    public static <A, B> List<B> map(List<A> list, Class<B> targetType) {
        if (list == null || list.size() == 0) {
            return Collections.emptyList();
        }
        register(list.get(0).getClass(), targetType);
        return list.stream()
                .map(entity -> MAPPER.map(entity, targetType))
                .collect(Collectors.toList());
    }


}
