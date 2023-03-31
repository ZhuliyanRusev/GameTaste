//package com.example.gametaste.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ApplicationSecurityConfiguration{
//
//
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http.
//                            authorizeRequests().
//                            requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
//                            antMatchers("/", "/users/login", "/users/register").permitAll().
//                            anyRequest().
//                    authenticated().
//                    and().
//                    formLogin().
//                    loginPage("/users/login").
//
//                    defaultSuccessUrl("/").
//                    failureForwardUrl("/users/login-error").
//                    and().
//                    logout().
//                    logoutUrl("/users/logout").
//                    logoutSuccessUrl("/").
//                    invalidateHttpSession(true);
//            return http.build();
//        }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}