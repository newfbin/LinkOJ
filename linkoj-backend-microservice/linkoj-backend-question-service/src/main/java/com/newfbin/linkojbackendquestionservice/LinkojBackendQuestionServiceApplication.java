package com.newfbin.linkojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.newfbin.linkojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.newfbin")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.newfbin.linkojbackendserviceclient")
public class LinkojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkojBackendQuestionServiceApplication.class, args);
    }

}
