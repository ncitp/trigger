package com.ruqu.trigger.api;

import com.ruqu.trigger.entity.ConfigEntity;
import com.ruqu.trigger.result.Result;
import com.ruqu.trigger.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Project TriggerTools
 * @Package com.ruqu.trigger.api
 * @Author ncitp
 * @Date 2020 12 2020-12-20 13:40
 * @Description
 */
@RestController
@RequestMapping(value = "/api/v1/config")
@Api(tags = "配置", value = "config-api", protocols = "http")
public class ConfigAPI {

    @Resource
    ConfigService configService;

    /**
     * 获取配置信息
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/findConfig")
    @ApiOperation(value = "获取配置信息", httpMethod = "GET", notes = "获取配置信息")
    public Result findConfig(){
        ConfigEntity entity = configService.findConfigInfo();
        return Result.successData(entity);
    }

    /**
     * 编辑配置信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/editConfig")
    @ApiOperation(value = "编辑配置信息", httpMethod = "POST", notes = "编辑配置信息")
    public Result editConfig(ConfigEntity entity){
        System.out.println(entity);
        Result result = configService.editConfig(entity);
        return Result.success();
    }
}
