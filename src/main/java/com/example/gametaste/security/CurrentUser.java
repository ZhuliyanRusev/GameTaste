package com.example.gametaste.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class CurrentUser {
    private String username;
    private Long id;

    public CurrentUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
