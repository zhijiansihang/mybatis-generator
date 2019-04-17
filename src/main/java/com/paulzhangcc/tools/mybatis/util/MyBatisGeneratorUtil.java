package com.paulzhangcc.tools.mybatis.util;

import com.paulzhangcc.tools.mybatis.generator.CustomMyBatisGenerator;
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
