package com.yunzainfo.oa.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.servlet.http.HttpServletResponse;


@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "order";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
         /*   // @formatter:off
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().anyRequest()
                    .and()
                    .anonymous()
                    .and()
                    .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                    .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
            // @formatter:on*/
            http
                    .csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .and()
                    .authorizeRequests()
                    .antMatchers("/swagger*/**",  "/webjars/**","/v2/**")
                    .permitAll()
                    .anyRequest().authenticated()
//                    .antMatchers("/user").authenticated()
                    .and()
                    .httpBasic();
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory().withClient("test")
                    .secret("password")
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
                    .and().withClient("webapp")
                    .authorizedGrantTypes("password","refresh_token")
                    .scopes("webapp")
                    .accessTokenValiditySeconds(60*60*12) // 1 hour
                    .refreshTokenValiditySeconds(60*60*12) // 30 days
                    .secret("123456");
        }
        @Primary
        @Bean
        public AuthorizationServerTokenServices tokenServices() {
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            //defaultTokenServices.setAccessTokenValiditySeconds(60*60*12);
            defaultTokenServices.setRefreshTokenValiditySeconds(60*60*12);
            defaultTokenServices.setSupportRefreshToken(true);
            defaultTokenServices.setReuseRefreshToken(false);
            defaultTokenServices.setTokenStore(new RedisTokenStore(redisConnectionFactory));
            return defaultTokenServices;
        }
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints/*.tokenServices(tokenServices())*/
                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    //.userDetailsService(userDetailsService)
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients()
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

    }

}
