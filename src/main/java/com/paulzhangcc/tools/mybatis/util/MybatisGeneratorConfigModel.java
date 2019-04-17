package com.paulzhangcc.tools.mybatis.util;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paul
 * @description
 * @date 2019/4/16
 */
@Data
@Builder
public class MybatisGeneratorConfigModel {

    /**
     * 是否生成vo
     */
    @Builder.Default
    private String javaVoGeneratorFlag = "true";
    /**
     * 是否生成model
     */
    @Builder.Default
    private String javaModelGeneratorFlag = "true";
    /**
     * 是否生成sqlMap
     */
    @Builder.Default
    private String sqlMapGeneratorFlag = "true";
    /**
     * 是否生成javaClient
     */
    @Builder.Default
    private String javaClientGeneratorFlag = "true";
    private String targetPackage;
    private String targetProject;

    private String dbUsername;
    private String dbPassword;
    @Builder.Default
    private String dbDriverClass = "com.mysql.jdbc.Driver";
    private String dbConnectionURL;
    @Builder.Default
    private List<TableInfo> tableList = new ArrayList<>();

    @Data
    @Builder
    public static class TableInfo {
        private String tableName;
        private String domainObjectName;
        private String generatedKey;
        @Builder.Default
        private List<String> ignoreColumns = new ArrayList<>();
    }
}
