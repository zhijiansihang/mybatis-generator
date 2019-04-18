package com.paulzhangcc.tools.mybatis.plugin;


import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Butler
 */
public class SampleVOGenerator extends AbstractJavaGenerator {

    private FullyQualifiedJavaType fullyQualifiedJavaType;

    public SampleVOGenerator(FullyQualifiedJavaType fullyQualifiedJavaType) {
        super();
        this.fullyQualifiedJavaType = fullyQualifiedJavaType;
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        CommentGenerator commentGenerator = context.getCommentGenerator();

        TopLevelClass topLevelClass = new TopLevelClass(fullyQualifiedJavaType);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        if (superClass != null) {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }

        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().modelBaseRecordClassGenerated(
                topLevelClass, introspectedTable)) {
            topLevelClass.getAnnotations().clear();
            answer.add(topLevelClass);
        }
        return answer;
    }
}

