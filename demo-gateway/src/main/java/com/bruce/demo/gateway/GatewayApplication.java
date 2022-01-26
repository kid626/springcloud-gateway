package com.bruce.demo.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName springcloud-gateway
 * @Date 2022/1/25 14:24
 * @Author fzh
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.bruce.demo.gateway.mapper")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
