package com.shop.Config;

import com.shop.Entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:" +System.getProperty("user.dir")+"/src/uploads/");
    }






//    registry.addResourceHandler("/uploads/**")
////            .addResourceLocations("file:./src/main/uploads/");
//            .addResourceLocations("file:" +System.getProperty("user.dir")+"/src/uploads/");
}
