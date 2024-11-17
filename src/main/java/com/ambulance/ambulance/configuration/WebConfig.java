package com.ambulance.ambulance.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins("*") // Allow requests from this origin, all
                .allowedMethods("*") // Allow specific HTTP methods
                .allowedHeaders("*"); // Allow credentials like cookies or Authorization headers
    }
}