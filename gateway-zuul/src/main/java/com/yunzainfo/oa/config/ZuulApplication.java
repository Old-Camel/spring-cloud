package com.yunzainfo.oa.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/19
 * Email:old_camel@126.com
 */
@EnableZuulProxy
@SpringCloudApplication
@EnableOAuth2Sso
@EnableDiscoveryClient
public class ZuulApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }
}
