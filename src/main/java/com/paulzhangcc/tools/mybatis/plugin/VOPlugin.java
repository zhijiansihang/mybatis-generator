package com.paulzhangcc.tools.mybatis.plugin;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 2018/12/22.
 */
public class VOPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<>();

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage(context.getProperty("vo.targetPackage"));
        javaModelGeneratorConfiguration.setTargetProject(context.getProperty("vo.targetProject"));
        javaModelGeneratorConfiguration.addProperty("enableSubPackages",context.getProperty("vo.enableSubPackages"));
        javaModelGeneratorConfiguration.addProperty("trimStrings",context.getProperty("vo.trimStrings"));

        FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(javaModelGeneratorConfiguration.getTargetPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName()+"VO");

        String s = sb.toString();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                s);

        AbstractJavaGenerator javaGenerator = new SampleVOGenerator(type);
        javaGenerator.setContext(context);
        javaGenerator.setIntrospectedTable(introspectedTable);
        List<CompilationUnit> compilationUnits = javaGenerator
                .getCompilationUnits();
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaModelGeneratorConfiguration()
                            .getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                    context.getJavaFormatter());
            generatedJavaFiles.add(gjf);
        }
        return generatedJavaFiles;
    }

    @Override
    //与表无关的生成GeneratedJavaFile
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return null;
    }

}
