package com.lmy.antelope.config.mybatis;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author yangmeiliang
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyMapper<T> {

    @DeleteProvider(type = MyProvider.class, method = "dynamicSQL")
    int deleteAll();

    @SelectProvider(type = MyProvider.class, method = "dynamicSQL")
    int count();

    @Options(useGeneratedKeys = true)
    @InsertProvider(type = MyProvider.class, method = "dynamicSQL")
    int insertListSelective(List<T> recordList);
}
