package com.ruqu.trigger.schema;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project ydimp
 * @Package cn.net.ydesign.ydimp.ydupm.schema
 * @Author reny
 * @Date 2020 12 2020/12/12 18:26
 * @Description 建表方法
 */
public class TableSchema {

    /** 单体表 */
    static final String configTable = "config";

    /**
     * 根据数据库返回建表语句集合
     * @param dbType
     * @return
     */
    public static Map<String,String> getSchemaInfo(String dbType){
        Map<String,String> map = new HashMap<>();
        switch (dbType){
            case "h2":
                map.put(configTable,h2_config);
                break;
            case "sqlite":break;
            case "mysql":break;
            case "postgre":break;
            default:break;
        }
        return map;
    }

    /********************* H2 建表语句 *********************/
    static String h2_config = "create table config (" +
            " id varchar(32) NOT NULL COMMENT '主键ID'," +
            " method varchar(50) COMMENT '方法'," +
            " url varchar(255) COMMENT '地址'," +
            " post int COMMENT '端口号'," +
            " create_date TIMESTAMP COMMENT '创建时间'," +
            " update_date TIMESTAMP COMMENT '修改时间'," +
            " PRIMARY KEY (id));";

    /********************* Sqlite 建表语句 *********************/

    /********************* MySQL 建表语句 *********************/

    /********************* PostgreSQL 建表语句 *********************/


}
