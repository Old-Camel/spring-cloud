package com.yunzainfo.oa.filecenter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/19
 * Email:old_camel@126.com
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FileServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FileServiceApplication.class).web(true).run(args);
    }
}
