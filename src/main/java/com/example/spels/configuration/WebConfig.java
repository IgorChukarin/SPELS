package com.example.spels.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/uploads/products/**")
                .addResourceLocations("file:/app/uploads/products/");

        registry
                .addResourceHandler("/uploads/photos/**")
                .addResourceLocations("file:/app/uploads/photos/");

        registry
                .addResourceHandler("/uploads/documents/**")
                .addResourceLocations("file:/app/uploads/documents/");
    }
}

