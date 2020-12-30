package com.ruqu.trigger.mapper;

import com.ruqu.trigger.provider.SchemaProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @Project ydimp
 * @Package cn.net.ydesign.ydimp.ydupm.dao
 * @Author reny
 * @Date 2020/12/11 19:41
 * @Description 数据操作DAO
 */
@Mapper
@Component
public interface SchemaMapper {

    /**
     * 根据表名查询当前数据库内是否存在该表
     * @param tableName
     * @param dbType
     * @return
     */
    @SelectProvider(type = SchemaProvider.class, method = "findTableByTableName")
    Integer findTableByTableName(@Param("dbType") String dbType, @Param("tableName") String tableName);

    /**
     * 执行sql语句
     * @param sql
     * @return
     */
    @Update("${sql}")
    Integer runSQLShell(@Param("sql") String sql);

}
