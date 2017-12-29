package com.yunzainfo.oa.registry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/18
 * Email:old_camel@126.com
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RegistryApplication.class).web(true).run(args);
    }
}