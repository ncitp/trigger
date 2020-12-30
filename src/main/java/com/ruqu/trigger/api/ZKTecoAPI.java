package com.ruqu.trigger.api;

import com.ruqu.trigger.service.ZKTecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Project trigger
 * @Package com.ruqu.trigger.api
 * @Author reny
 * @Date 2020/12/28 14:41
 * @Description 设备API-ZKTeco
 */
@RestController
@RequestMapping(value = "/iclock")
@Api(tags = "设备", value = "equipment-api", protocols = "http")
public class ZKTecoAPI {

    @Resource
    ZKTecoService zkTecoService;

    /**
     * 设备配置信息
     * @param SN 表示客户端的序列号
     * @param options 表示获取服务器配置参数，目前值只有all
     * @param pushver 表示设备当前最新的push协议版本，新开发的客户端必须支持且必须大于等于2.2.14 版本
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/cdata")
    @ApiOperation(value = "设备握手", httpMethod = "GET", notes = "设备握手")
    public String online(@RequestParam("SN") String SN, @RequestParam("options") String options, @RequestParam("pushver") String pushver){
        return zkTecoService.init(SN, options, pushver);
    }

    /**
     * 交换因子
     * @param SN
     * @param type
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/exchange")
    @ApiOperation(value = "交换因子", httpMethod = "POST", notes = "交换因子")
    public String exchange(@RequestParam("SN") String SN, @RequestParam("type") String type, @RequestParam("Factors") String Factors){
        return zkTecoService.exchange(SN, type, Factors);
    }

    /**
     * 心跳
     * @param SN
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/ping")
    @ApiOperation(value = "心跳", httpMethod = "POST", notes = "心跳")
    public String ping(@RequestParam("SN") String SN){
        return zkTecoService.ping(SN);
    }

    /**
     * 上传记录
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/cdata")
    @ApiOperation(value = "上传记录", httpMethod = "POST", notes = "上传记录")
    public String add(@RequestParam("SN") String SN, @RequestParam("table") String table, @RequestBody String DataRecord){
        return zkTecoService.sendPin(SN, table, DataRecord);
    }

}
