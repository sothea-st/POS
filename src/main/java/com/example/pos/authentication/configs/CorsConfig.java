package com.example.pos.authentication.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// @Configuration
public class CorsConfig implements WebMvcConfigurer {
     // @Override
     // public void addCorsMappings(CorsRegistry registry) {
     //     registry.addMapping("/**")
     //             .allowedOrigins("*")
     //             .allowedMethods("*")
     //             .allowedHeaders("*")
     //             .allowCredentials(false)
     //             .maxAge(3600);
     // }

     // @Override
     // public void addCorsMappings(CorsRegistry registry) {
     //     registry.addMapping("/swagger-ui/**")
     //             .allowedOrigins("http://localhost:8090") // Replace with your frontend URL
     //             .allowedMethods("GET", "POST", "PUT", "DELETE")
     //             .allowedHeaders("*")
     //             .allowCredentials(true);
     // }
}
