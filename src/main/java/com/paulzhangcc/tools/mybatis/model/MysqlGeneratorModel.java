package com.paulzhangcc.tools.mybatis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paul
 * @description
 * @date 2019/4/16
 */
public class MysqlGeneratorModel {
    private String targetPackage;
    private String targetProject;
    private JdbcInfo jdbc;
    private List<TableInfo> tableList = new ArrayList<>();

    public static class JdbcInfo {
        private String ip;
        private String port;
        private String dbName;
        private String userId;
        private String password;
        private String driverClass;

        public String getDriverClass() {
            return driverClass;
        }

        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class TableInfo {
        private String tableName;
        private String domainObjectName;
        private String generatedKey;

        public List<String> getIgnoreColumns() {
            return ignoreColumns;
        }

        public void setIgnoreColumns(List<String> ignoreColumns) {
            this.ignoreColumns = ignoreColumns;
        }

        private List<String> ignoreColumns = new ArrayList<>();

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getDomainObjectName() {
            return domainObjectName;
        }

        public void setDomainObjectName(String domainObjectName) {
            this.domainObjectName = domainObjectName;
        }

        public String getGeneratedKey() {
            return generatedKey;
        }

        public void setGeneratedKey(String generatedKey) {
            this.generatedKey = generatedKey;
        }

    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public JdbcInfo getJdbc() {
        return jdbc;
    }

    public void setJdbc(JdbcInfo jdbc) {
        this.jdbc = jdbc;
    }

    public List<TableInfo> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableInfo> tableList) {
        this.tableList = tableList;
    }
}
