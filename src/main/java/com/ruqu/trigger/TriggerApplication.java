package com.ruqu.trigger;

import com.ruqu.trigger.service.SchemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
public class TriggerApplication {


    @Resource
    SchemaService schemaService;

    public static void main(String[] args) {
        SpringApplication.run(TriggerApplication.class, args);
    }

    /**
     * 检查数据表
     */
    @Bean
    public void checkTable(){
        schemaService.checkTable();
        log.info("检查数据表完成");
    }
}
