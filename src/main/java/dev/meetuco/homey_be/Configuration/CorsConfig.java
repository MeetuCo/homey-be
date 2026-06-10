package dev.meetuco.homey_be.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

  @Override
  public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/api/**")
    // .allowedOrigins("*")
    .allowedMethods("PUT", "DELETE", "GET", "POST")
    // .allowedHeaders("header1", "header2", "header3")
    // .exposedHeaders("header1", "header2")
    .allowCredentials(false).maxAge(3600);
    
    System.out.println("Cors function activated");
  }
}
