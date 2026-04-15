package com.leadtrans.dictservice.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfiguration {

    @Value(("${server.version}"))
    private String serverVersion;

    @Value(("${swagger.enable}"))
    private boolean enable;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("数据字典API")
                                .description("系统数据字典管理接口")
                                .version("1.0.0")
                                .build()
                )
                .select()
                // 方式1：精确控制包路径（推荐）
                .apis(RequestHandlerSelectors.basePackage("com.leadtrans.dictservice"))
                // 方式2：通过注解过滤
                // .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}