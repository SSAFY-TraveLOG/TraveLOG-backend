package com.ssafy.travelog.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_NAME = "TRAVELOG";
    private static final String API_DESCRIPTION = "TRAVELOG API 명세서";
    private static final String API_VERSION = "1.0";

    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title(API_NAME)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
