package com.yunzainfo.oa.filecenter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static com.google.common.base.Predicates.or;


/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/27
 * Email:old_camel@126.com
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.show}")
    private boolean swaggerShow;
    /*@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xc.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("徐成的ss三sssssss测试")
                .termsOfServiceUrl("http://xucheng.space/")
                .contact("徐成")
                .version("1.0")
                .build();
    }*/
    /*



再通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
    * */
    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("Authorization", "accessToken", "header");
    }
    @Bean
    public Docket fileApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .groupName("文件测试")

                //.genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select().
                        apis(RequestHandlerSelectors.basePackage("com.yunzainfo.oa.filecenter.web"))
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(fileApiInfo());
    }



    private ApiInfo fileApiInfo() {
        return new ApiInfoBuilder()
                .title("PitcherCloud文件服务接口测试")//大标题
                .description("目前功能实现:文件上传,下载,删除.具体使用详情看接口描述")//详细描述
                .version("1.0")//版本
                .contact("研发一组:徐成")//作者
                //.license("The Apache License, Version 2.0")
                //.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

}

