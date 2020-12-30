package com.ruqu.trigger.controller;

import com.ruqu.trigger.entity.ConfigEntity;
import com.ruqu.trigger.service.ConfigService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @Project BSIMS
 * @Package cn.net.ydesign.bsims.controller
 * @Date 2020/5/6 13:30
 * @Author reny
 */
@Controller
@ComponentScan
@ApiIgnore
public class BaseController {

    @Resource
    ConfigService configService;

    /**
     * 系统首页
     * @return
     */
    @GetMapping(value = "/")
    public ModelAndView index(){
        ConfigEntity entity = configService.findConfigInfo();
        return new ModelAndView("setting","data",entity);
    }

}
