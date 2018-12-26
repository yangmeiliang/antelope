package com.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

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


//    @Override
//    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
//        return false;
//    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        List<GeneratedJavaFile> generatedJavaFiles = introspectedTable.getGeneratedJavaFiles();

        return super.contextGenerateAdditionalJavaFiles(introspectedTable);
    }
}

