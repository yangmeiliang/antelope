package com.lmy.antelope.config.mybatis;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * @author yangmeiliang
 */
public class MyProvider extends MapperTemplate {

    public MyProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     */
    public String deleteAll(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        return "truncate " + tableName(entityClass);
    }

    /**
     * 查询总数量
     */
    public String count(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        return "select count(*) from " + tableName(entityClass);
    }

    /**
     * 批量插入
     */
    public String insertListSelective(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass)));
        boolean existsId = columnList.stream().anyMatch(column -> column.isId() && column.getColumnHolder("id") != null);
        if (existsId) {
            sql.append(insertColumns(entityClass, false));
        } else {
            sql.append(insertColumns(entityClass, true));
        }
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnList) {
            if (column.isId() && !existsId) {
                continue;
            }
            if (column.isInsertable()) {
                String record = column.getColumnHolder("record");
                String property = column.getProperty();
                String noNull = "<if test='record." + property + "==null'>default,</if><if test='record." + property + "!=null'>" + record + ",</if>";
                sql.append(noNull);
            }
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }


    private static String insertColumns(Class<?> entityClass, boolean skipId) {
        StringBuilder sql = new StringBuilder();
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //获取全部列
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnSet) {
            if (!column.isInsertable()) {
                continue;
            }
            if (skipId) {
                continue;
            }
            sql.append(column.getColumn()).append(",");
        }
        sql.append("</trim>");
        return sql.toString();
    }
}
