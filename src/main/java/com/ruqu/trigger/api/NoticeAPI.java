package com.ruqu.trigger.api;

import com.ruqu.trigger.result.Result;
import com.ruqu.trigger.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Project TriggerTools
 * @Package com.ruqu.trigger.api
 * @Author reny
 * @Date 2020 12 2020/12/19 10:28
 * @Description
 */
@RestController
@RequestMapping(value = "/api/v1/notice")
@Api(tags = "通知", value = "notice-api", protocols = "http")
public class NoticeAPI {

    @Resource
    ConfigService configService;

    /**
     * 获取数据库消息
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/pull")
    @ApiOperation(value = "获取消息", httpMethod = "GET", notes = "获取消息")
    public Result notice(@RequestParam("params") String params){
        String[] str = params.split(",");
        if(configService.sendMsg(str[0],str[1])){
            return Result.success();
        }else{
            return Result.error();
        }
    }

}
