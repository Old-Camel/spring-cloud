package com.yunzainfo.oa.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
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


/**
 * Created by Xu Cheng on 2017/3/23.
 */
/*
如上代码所示，通过@Configuration注解，让Spring来加载该类配置。
再通过@EnableSwagger2注解来启用Swagger2。*/
@Configuration
@EnableSwagger2
public class Swagger2 {
//    @Value("${swagger.show}")
    private boolean swaggerShow =true;
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
select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义
，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
    * */
    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("Authorization", "accessToken", "header");
    }
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .groupName("test")

                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)

                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select().
                        apis(RequestHandlerSelectors.basePackage("com.yunzainfo.oa.auth.web"))
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(testApiInfo());
    }

/*    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .groupName("demo")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/test")))//过滤的接口
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(demoApiInfo());
    }*/

    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
                .title("Electronic Health Record(EHR) Platform API")//大标题
                .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("NO terms of service")
                .contact("xucheng")//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfoBuilder()
                .title("Electronic Health Record(EHR) Platform API")//大标题
                .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("NO terms of service")
                .contact("xucheng")//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();

        //return apiInfo;
    }
}

