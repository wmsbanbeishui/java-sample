package com.drhs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Knife4jConfig {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30)
                .groupName("adminApi")
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.drhs"))
                //.paths(PathSelectors.regex("/admin/.*"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("接口文档")
                .version("1.0.0")
                .contact(new Contact("", "", ""))
                .build();
    }
}
