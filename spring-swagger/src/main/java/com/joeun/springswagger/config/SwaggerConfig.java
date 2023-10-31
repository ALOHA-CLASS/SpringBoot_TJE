package com.joeun.springswagger.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
 
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("group-joeun") // 그룹명 설정
                .pathsToMatch("/api/**", "/board/**") // 경로 설정
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Test Proejct API")
                        .description("테스트 프로젝트 API 입니다.")
                        .version("v0.0.1"));
    }

    
}
