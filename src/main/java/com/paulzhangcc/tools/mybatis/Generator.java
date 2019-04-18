package com.paulzhangcc.tools.mybatis;

import com.paulzhangcc.tools.mybatis.generator.CustomMyBatisGenerator;
import com.paulzhangcc.tools.mybatis.util.MyBatisGeneratorUtil;
import com.paulzhangcc.tools.mybatis.util.MybatisGeneratorConfigModel;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Paul Zhang
 * @date: 16:21 2017/12/27
 */
public class Generator {

    public static boolean run(MybatisGeneratorConfigModel model) {
        if (model == null) {
            return false;
        }
        return MyBatisGeneratorUtil.generator(model);
    }

    public static void main(String[] args) throws Exception {
        MybatisGeneratorConfigModel mysqlGeneratorModel = MybatisGeneratorConfigModel.builder()
                .targetPackage("com.meimeitech.video.rest")
                .targetProject("F:\\shipin\\mmt-code-generator\\src\\main\\java")
                .dbDriverClass("com.mysql.jdbc.Driver")
                .dbConnectionURL("jdbc:mysql://127.0.0.1:3306/ApolloConfigDB?useUnicode=true&characterEncoding=UTF-8&useSSL=false")
                .dbUsername("root")
                .dbPassword("root")
                //.templateName("tk-generatorConfig-mysql.ftl")
                .templateName("generatorConfig-mysql.ftl")
                .build();

        mysqlGeneratorModel.getTableList().add(
                MybatisGeneratorConfigModel.TableInfo.builder()
                        .tableName("ReleaseHistory")
                        .domainObjectName("ReleaseHistory")
                        .generatedKey("Id")
                        .build()
        );
        MyBatisGeneratorUtil.generator(mysqlGeneratorModel);
    }


    /**
     * 读取resource目录下的配置文件  注意生成是xml 要删除  否则生成的会追加报错
     */
    private static final String CONFIG_MYSQL = "generatorConfig-mysql.xml";
    private static final String CONFIG_ORACLE = "generatorConfig-oracle.xml";

    private static CustomMyBatisGenerator test(boolean isWriteFile) {
        CustomMyBatisGenerator myBatisGenerator = null;
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            //属性进行判断，是否合并（即追加）
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(CONFIG_MYSQL);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            myBatisGenerator = new CustomMyBatisGenerator(config, callback, warnings);

            if (isWriteFile) {
                myBatisGenerator.generate();
            } else {
                myBatisGenerator.generateAndNoWriteFiles();
            }
            for (String warning : warnings) {
                System.out.println(warning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("success!!!");
        return myBatisGenerator;
    }

}
