package com.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;
import java.util.Set;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * @author yangmeiliang
 * @date 2018/5/10
 */
public class MyCommentGenerator implements CommentGenerator {

    /**
     * The properties.
     */
    private Properties properties;

    /**
     * The suppress all comments.
     */
    private boolean suppressAllComments;

    /**
     * Instantiates a new default comment generator.
     */
    public MyCommentGenerator() {
        super();
        properties = new Properties();
        suppressAllComments = false;
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    /**
     * Adds a suitable comment to warn users that the element was generated, and when it was generated.
     *
     * @param xmlElement the xml element
     */
    @Override
    public void addComment(XmlElement xmlElement) {

    }


    @Override
    public void addRootComment(XmlElement rootElement) {
        rootElement.addElement(
                new TextElement("<!-- This element is automatically generated by MyBatis Generator, do not modify. -->"));
    }


    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }


    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);

        suppressAllComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        classComment(innerClass, introspectedTable);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        classComment(topLevelClass, introspectedTable);
    }


    @Override
    public void addEnumComment(InnerEnum innerEnum,
                               IntrospectedTable introspectedTable) {

    }


    @Override
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        String remarks = introspectedColumn.getRemarks();
        if (!StringUtility.stringHasValue(remarks)) {
            return;
        }
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }


    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }


    @Override
    public void addGeneralMethodComment(Method method,
                                        IntrospectedTable introspectedTable) {

    }


    @Override
    public void addGetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }


    @Override
    public void addSetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable,
                                boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
        classComment(innerClass, introspectedTable);
    }

    private void classComment(JavaElement javaElement, IntrospectedTable introspectedTable) {
        javaElement.addJavaDocLine("/**");
        String remarks = introspectedTable.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            javaElement.addJavaDocLine(" * " + remarks);
            javaElement.addJavaDocLine(" *");
        }
        javaElement.addJavaDocLine(" * tableName: " + introspectedTable.getFullyQualifiedTable());
        javaElement.addJavaDocLine(" */");
    }
}
