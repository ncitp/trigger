package com.ruqu.trigger.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruqu.trigger.entity.ConfigEntity;
import com.ruqu.trigger.mapper.ConfigMapper;
import com.ruqu.trigger.result.Result;
import com.ruqu.trigger.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * @Project TriggerTools
 * @Package com.ruqu.trigger.service
 * @Author reny
 * @Date 2020 12 2020/12/19 18:28
 * @Description
 */
@Slf4j
@Service
public class ConfigService {

    @Resource
    ConfigMapper configMapper;

    /**
     * 查找配置信息
     * @return
     */
    public ConfigEntity findConfigInfo(){
        log.info("查找配置信息");
        List<ConfigEntity> entityList = configMapper.selectList(new QueryWrapper<ConfigEntity>());
        if(entityList!=null && entityList.size()>0){
            return entityList.get(0);
        }else{
            return null;
        }
    }

    /**
     * 编辑配置信息
     * @param entity
     * @return
     */
    public Result editConfig(ConfigEntity entity){
        log.info("编辑配置信息");
        if(ObjectUtil.isNotNull(entity)){
            if(StringUtils.isNotBlank(entity.getId())){
                if(configMapper.updateById(entity)>0){
                    log.info("编辑配置信息成功");
                    return Result.successData(entity);
                }else{
                    log.error("编辑配置信息失败");
                    return Result.error();
                }
            }else{
                if(configMapper.insert(entity)>0){
                    log.info("添加配置信息成功");
                    return Result.successData(entity);
                }else{
                    log.error("添加配置信息失败");
                    return Result.error();
                }
            }
        }else{
            log.error("需要编辑的配置信息不能为空，添加失败");
            return Result.errorEnumAndInfo(ResultEnum.PARAM_ERROR,"需要编辑的配置信息不能为空，添加失败");
        }
    }

    /**
     * 发送相关信息
     * @param id
     * @param pin
     * @return
     */
    public boolean sendMsg(String id,String pin){
        ConfigEntity entity = this.findConfigInfo();
        String url = entity.getUrl();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("pin", pin);
        String str = null;
        switch (entity.getMethod()){
            case "get":
                str = HttpUtil.get(url, paramMap);
                break;
            case "post":
                str = HttpUtil.post(url, paramMap);
                break;
            case "tcp":
                Socket socket = null;
                OutputStream os = null;
                try {
                    //服务器端口号
                    int port = entity.getPost();
                    //创建socket连接
                    socket = new Socket(url, port);
                    //发送消息IO流
                    os = socket.getOutputStream();
                    os.write(pin.getBytes("gbk"));
                    log.info("发送成功："+ pin.getBytes("gbk").toString());
                } catch (Exception e) {
                    log.error("发送失败：", e.getMessage());
                    e.printStackTrace();
                } finally {
                    if(os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            log.error("关闭流失败：", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    if(socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            log.error("关闭连接失败：", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:break;
        }
        log.info(str);
        log.info("信息发送完成");
        return true;
    }
}
