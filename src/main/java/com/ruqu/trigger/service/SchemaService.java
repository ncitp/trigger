package com.ruqu.trigger.service;

import com.ruqu.trigger.mapper.SchemaMapper;
import com.ruqu.trigger.schema.TableSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Project ydmis
 * @Package cn.net.ydesign.ydmis.service.impl
 * @Author reny
 * @Date 2020 09 2020/9/12 20:58
 * @Description 数据表Service实现类
 */
@Slf4j
@Service
public class SchemaService {

    @Value("${DBType}")
    private String dbType;

    @Resource
    SchemaMapper schemaMapper;

    /**
     * 根据表名判断表是否存在
     *
     * @param tableName
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean existsByTableName(String tableName) {
        log.info("根据表名判断表是否存在");
        Integer res = 0;
        try{
            res = schemaMapper.findTableByTableName(dbType,tableName);
            if(res > 0){
                log.info("数据表已存在");
                return true;
            }else{
                log.info("数据表不存在");
                return false;
            }
        }catch (Exception e){
            log.error("根据表名判断表是否存在错误："+e.getMessage());
            return false;
        }
    }

    /**
     * 根据sql语句创建表
     *
     * @param sql
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean createTable(String sql) {
        log.info("根据sql语句创建表");
        try{
            schemaMapper.runSQLShell(sql);
            log.info("创建表成功");
            return true;
        }catch (Exception e){
            log.error("创建表错误："+e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据表名删除表
     *
     * @param tableName
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTable(String tableName) {
        log.info("根据表名删除表");
        if(this.existsByTableName(tableName)){
            String sql;
            try {
                sql = "DROP TABLE " + tableName;
                schemaMapper.runSQLShell(sql);
                log.info("删除" + tableName + "成功");
                return true;
            } catch (Exception e) {
                log.error("删除" + tableName + "表失败，没有找到对应的数据库类型："+dbType);
                return false;
            }
        }else{
            log.info("需要删除的表不存在");
            return false;
        }
    }

    /**
     * 检查数据表（不存在则新建）
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean checkTable() {
        log.info("检查数据表（不存在则新建）");
        Map<String,String> map = TableSchema.getSchemaInfo(dbType);
        for(Map.Entry<String,String> entry : map.entrySet()){
            if(!this.existsByTableName(entry.getKey())){
                log.info(entry.getKey()+" 表不存在，新建数据表："+entry.getKey());
                if(!this.createTable(entry.getValue())){
                    log.error("创建"+entry.getKey()+"表失败");
                    return false;
                }
            }
        }
        return true;
    }
}
