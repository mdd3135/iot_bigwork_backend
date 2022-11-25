package com.example.iot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 配置跨域访问
@Configuration
class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
        // .exposedHeaders("Content_Type")
        // .exposedHeaders("X-Requested-With")
        // .exposedHeaders("accept")
        // .exposedHeaders("Origin")
        // .exposedHeaders("Access-Control-Request-Method")
        // .exposedHeaders("Access-Control-Request-Headers");
        // .allowCredentials(true);
    }
}
