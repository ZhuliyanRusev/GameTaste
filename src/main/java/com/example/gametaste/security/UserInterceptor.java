package com.example.gametaste.security;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Pre-handle логика, изпълнява се преди метода в контролера да бъде повикан
        System.out.println("Inside preHandle method of UserInterceptor");
        return true; // връща true за да позволи на метода на контролера да бъде повикан или false, за да прекрати заявката
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Post-handle логиката се изпълнява след метода на контролера да бъде повикан, но преди отговора да бъде върнат към клиента
        System.out.println("Inside postHandle method of UserInterceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // After-completion логиката се изпълнпва след като отговорът е върнат към клиента
        System.out.println("Inside afterCompletion method of UserInterceptor");
    }
}
