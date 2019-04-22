package com.generator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.ParseException;
import java.util.List;

/**
 * @author yangmeiliang
 */
public class MyPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
//        topLevelClass.addImportedTypes(new HashSet<>(Arrays.asList("lombok.Getter", "lombok.Setter", "lombok.experimental.Accessors")));
        topLevelClass.addImportedType("lombok.Getter");
        topLevelClass.addImportedType("lombok.Setter");
        topLevelClass.addImportedType("lombok.experimental.Accessors");
        topLevelClass.addImportedType("javax.persistence.Table");
        //该代码表示在生成class的时候，向topLevelClass添加一个@Setter和@Getter注解
        topLevelClass.addAnnotation("@Getter\n@Setter\n@Accessors(chain = true)");

        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");
        return super.modelBaseRecordClassGenerated(topLevelClass,
                introspectedTable);
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        String name = field.getName();
        if (name.equals("id")) {
            topLevelClass.addImportedType("javax.persistence.Id");
            topLevelClass.addImportedType("javax.persistence.GenerationType");
            topLevelClass.addImportedType("javax.persistence.GeneratedValue");
            String annotation = "@GeneratedValue(strategy = GenerationType.AUTO)";
            if (StringUtils.containsAny(field.getType().getShortName(), "Integer", "Long")) {
                annotation = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
            }
            field.addAnnotation("@Id");
            field.addAnnotation(annotation);
        } else {
            topLevelClass.addImportedType("javax.persistence.Column");
            String columnName = introspectedColumn.getActualColumnName();
            String annotation = "@Column(name = \"" + columnName + "\")";
            field.addAnnotation(annotation);
        }
        String defaultValue = introspectedColumn.getDefaultValue();
        if (field.getType().getShortName().contains("Integer")) {
            field.setInitializationString(defaultValue);
        } else if (field.getType().getShortName().contains("String")) {
            topLevelClass.addImportedType("cn.dxy.openclass.ms.domain.Strings");
            field.setInitializationString("Strings.EMPTY");
        } else if (field.getType().getShortName().contains("Date")) {
            if (defaultValue.contains(":")) {
                try {
                    long time = DateUtils.parseDate(defaultValue, "yyyy-MM-dd HH:mm:ss").getTime();
                    field.setInitializationString("new Date(" + time + ")");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                field.setInitializationString("new Date()");
            }
        }

        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    //该方法在生成每一个属性的getter方法时候调用，如果我们不想生成getter，直接返回false即可；
    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    //该方法在生成每一个属性的setter方法时候调用，如果我们不想生成setter，直接返回false即可；
    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        return super.contextGenerateAdditionalJavaFiles(introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String shortName = interfaze.getType().getShortName();
        String entityName = shortName.replace("Mapper", "");
        String fullName = "cn.dxy.openclass.ms.support.mybatis.BaseMapper<" + entityName + ">";
        interfaze.getSuperInterfaceTypes().clear();
        interfaze.getSuperInterfaceTypes().add(new FullyQualifiedJavaType(fullName));
        interfaze.getMethods().clear();
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {

        return super.contextGenerateAdditionalJavaFiles();
    }
}

