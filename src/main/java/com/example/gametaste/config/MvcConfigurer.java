package com.example.gametaste.config;

import com.example.gametaste.security.UserInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfigurer implements WebMvcConfigurer {
    private final UserInterceptor userInterceptor;

    public MvcConfigurer(UserInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
    }

}
