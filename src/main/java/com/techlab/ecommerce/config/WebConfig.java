package com.techlab.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 1. Permite acceso a TODAS las rutas (/api/...)
                .allowedOrigins("*") // 2. Permite peticiones desde CUALQUIER origen (Frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. Métodos permitidos
                .allowedHeaders("*"); // 4. Cabeceras permitidas
    }
}