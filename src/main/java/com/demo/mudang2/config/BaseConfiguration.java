package com.demo.mudang2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/admins/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PATCH", "POST")
                .allowedHeaders("*");
    }
}
