package com.hyx.demo.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 声名这是一个配置类
public class SwaggerConfig {
    // controller接口所在的包
    private String basePackage = "com.hyx.demo.core.controller";
    // 当前文档的标题
    private String title = "core接口文档";
    // 当前文档的详细描述
    private String description = "core接口文档";
    // 当前文档的版本
    private String version = "1.0";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(basePackage))
                    .paths(PathSelectors.any())
                    .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                    .title(title)
                    .description(description)
                    .version(version)
                    .build();
    }
}