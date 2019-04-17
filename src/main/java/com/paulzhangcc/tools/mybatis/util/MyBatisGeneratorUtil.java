package com.paulzhangcc.tools.mybatis.util;

import com.paulzhangcc.tools.mybatis.generator.CustomMyBatisGenerator;
import com.paulzhangcc.tools.mybatis.util.compiler.Compiler;
import com.paulzhangcc.tools.mybatis.util.compiler.JdkCompiler;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author paul
 * @description
 * @date 2019/4/17
 */
public class MyBatisGeneratorUtil {
    public static List<Class> getGeneratorModelClass(MybatisGeneratorConfigModel mybatisGeneratorConfigModel) {
        mybatisGeneratorConfigModel.onlyModel();

        ObjectRefUtil<CustomMyBatisGenerator> objectRefUtil = new ObjectRefUtil<>();

        generator(mybatisGeneratorConfigModel, (v) -> {
            try {
                v.generateAndNoWriteFiles();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, objectRefUtil);
        CustomMyBatisGenerator value = objectRefUtil.getValue();
        List<GeneratedJavaFile> generatedJavaFiles = value.getGeneratedJavaFiles();
        MyBatisGeneratorClassLoader myBatisGeneratorClassLoader = new MyBatisGeneratorClassLoader(Thread.currentThread().getContextClassLoader());
        List<Class> classes = new ArrayList<>();
        if (generatedJavaFiles == null || generatedJavaFiles.size() == 0) {
            return classes;
        }

        Compiler compiler = new JdkCompiler();
        for (GeneratedJavaFile generatedJavaFile : generatedJavaFiles) {
            classes.add(compiler.compile(generatedJavaFile.getFormattedContent(), myBatisGeneratorClassLoader));
        }
        return classes;
    }


    public static boolean generator(MybatisGeneratorConfigModel mybatisGeneratorConfigModel) {
        return generator(mybatisGeneratorConfigModel, (v) -> {
            try {
                v.generate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, null);
    }

    public static boolean generateAndNoWriteFiles(MybatisGeneratorConfigModel mybatisGeneratorConfigModel) {

        return generator(mybatisGeneratorConfigModel, (v) -> {
            try {
                v.generateAndNoWriteFiles();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, null);
    }


    private static boolean generator(MybatisGeneratorConfigModel mybatisGeneratorConfigModel, Consumer<CustomMyBatisGenerator> consumer, ObjectRefUtil<CustomMyBatisGenerator> objectRefUtil) {
        mybatisGeneratorConfigModel.genCheck();
        try {
            List<String> warnings = new ArrayList<String>();
            String generatorConfig = MybatisGeneratorConfigUtil.generator(mybatisGeneratorConfigModel);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(new StringReader(generatorConfig));
            DefaultShellCallback callback = new DefaultShellCallback(true);
            CustomMyBatisGenerator myBatisGenerator = new CustomMyBatisGenerator(config, callback, warnings);
            consumer.accept(myBatisGenerator);
            for (String warning : warnings) {
                System.out.println(warning);
            }
            if (objectRefUtil != null) {
                objectRefUtil.setValue(myBatisGenerator);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
