package com.leadtrans.dictservice.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class CorsConfig implements  WebMvcConfigurer {


        
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            // 允许所有路径
            registry.addMapping("/**")
                    // 允许所有域名
                    .allowedOriginPatterns("*")
                    // 允许的 HTTP 方法
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                    // 允许所有请求头
                    .allowedHeaders("*")
                    // 允许携带凭证
                    .allowCredentials(true)
                    // 预检请求的缓存时间
                    .maxAge(3600);
        }


}


