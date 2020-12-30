package com.ruqu.trigger.provider;

/**
 * @Project ydimp
 * @Package cn.net.ydesign.ydimp.ydupm.provider
 * @Author reny
 * @Date 2020 12 2020/12/12 23:03
 * @Description
 */
public class SchemaProvider {

    public String findTableByTableName(String dbType, String tableName){
        String sql = null;
        switch (dbType){
            case "h2": sql = "select count(*) from INFORMATION_SCHEMA.TABLES where TABLE_NAME = '"+tableName+"';";break;
            case "sqlite": sql = "select count(*) from sqlite_master where type='table' and name = '"+tableName+"';";break;
            case "mysql": sql = "select count(*) from information_schema.TABLES where TABLE_NAME = '"+tableName+"';";break;
            case "postgre": sql = "select count(*) from pg_tables where schemaname = 'public' and tablename = '"+tableName+"';";break;
            default:break;
        }
        return sql;
    }
}
