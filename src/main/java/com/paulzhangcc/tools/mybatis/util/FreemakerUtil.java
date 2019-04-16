package com.paulzhangcc.tools.mybatis.util;

import com.paulzhangcc.tools.mybatis.model.MysqlGeneratorModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author paul
 * @description
 * @date 2019/4/16
 */
public class FreemakerUtil {
    public static void main(String[] args) throws IOException, TemplateException {
        FreemakerUtil freemakerUtil = new FreemakerUtil();
        Template template = freemakerUtil.geTemplate("generatorConfig-mysql.freemarker");
        MysqlGeneratorModel mysqlGeneratorModel = new MysqlGeneratorModel();
        mysqlGeneratorModel.setGeneratedVoFlag(true);
        mysqlGeneratorModel.setTargetPackage("com.zhijiansihang.gen");
        mysqlGeneratorModel.setTargetProject("C:\\pengrun\\work\\code\\git\\mybatis-generator\\gen");
        MysqlGeneratorModel.JdbcInfo jdbcInfo = new MysqlGeneratorModel.JdbcInfo();
        {
            jdbcInfo.setDbName("test");
            jdbcInfo.setIp("127.0.0.1");
            jdbcInfo.setPort("3306");
            jdbcInfo.setPassword("root");
            jdbcInfo.setUserId("root");
            jdbcInfo.setDriverClass("com.mysql.jdbc.Driver");
        }
        mysqlGeneratorModel.setJdbc(jdbcInfo);

        List<MysqlGeneratorModel.TableInfo> tableList = mysqlGeneratorModel.getTableList();
        {
            MysqlGeneratorModel.TableInfo tableInfo = new MysqlGeneratorModel.TableInfo();

            tableInfo.setDomainObjectName("User");
            tableInfo.setGeneratedKey("user_id");
            tableInfo.setTableName("user");
            tableList.add(tableInfo);
        }

        {
            MysqlGeneratorModel.TableInfo tableInfo = new MysqlGeneratorModel.TableInfo();

            tableInfo.setDomainObjectName("User1");
            //tableInfo.setGeneratedKey("user_id");
            tableInfo.setTableName("user1");
            tableInfo.getIgnoreColumns().add("hello");
            tableList.add(tableInfo);
        }
        mysqlGeneratorModel.setTableList(tableList);

        template.process(mysqlGeneratorModel, new PrintWriter(System.out));
    }

    public Template geTemplate(String name) {

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
            Template temp = cfg.getTemplate(name);

            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
