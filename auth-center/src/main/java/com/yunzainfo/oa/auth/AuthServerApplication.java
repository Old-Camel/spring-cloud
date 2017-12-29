package com.yunzainfo.oa.auth;


import com.yunzainfo.oa.auth.security.SecurityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuthServerApplication {
    @Bean(name = "auditorAware")
    public AuditorAware<String> auditorAware() {
        return ()-> SecurityUtils.getCurrentUserUsername();
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}