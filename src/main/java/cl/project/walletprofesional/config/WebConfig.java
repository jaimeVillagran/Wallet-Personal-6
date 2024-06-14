package cl.project.walletprofesional.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/css/**")
                .addResourceLocations("classpath:/static/assets/css/");
        registry.addResourceHandler("/assets/css/**")
                .addResourceLocations("/assets/css/");
    }*/
}
