package com.example.gametaste.model.binding;

import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }
    @Size(min = 3,max = 20,message = "Enter a username between 3 and 20 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Size(min = 3,max = 20,message = "Enter a password between 3 and 20 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
