package com.example.spels.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String productsPath = Paths.get("uploads", "products").toAbsolutePath().toUri().toString();
        String photosPath = Paths.get("uploads", "photos").toAbsolutePath().toUri().toString();
        String documentsPath = Paths.get("uploads", "documents").toAbsolutePath().toUri().toString();

        registry
                .addResourceHandler("/uploads/products/**")
                .addResourceLocations(productsPath);
        registry
                .addResourceHandler("/uploads/photos/**")
                .addResourceLocations(photosPath);
        registry
                .addResourceHandler("/uploads/documents/**")
                .addResourceLocations(documentsPath);
    }

}
