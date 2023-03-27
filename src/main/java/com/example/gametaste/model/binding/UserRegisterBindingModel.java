package com.example.gametaste.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }


    @Size(min = 3,max = 20,message = "Enter a username between 3 and 20 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 3,max = 20,message = "Enter a First name between 3 and 20 characters.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email(message = "Email must contain @.")
    @NotEmpty(message = "Email must not be left empty!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Size(min = 3,max = 20,message = "Enter a password between 3 and 20 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(min = 3,max = 20,message = "Enter a confirm password between 3 and 20 characters.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
