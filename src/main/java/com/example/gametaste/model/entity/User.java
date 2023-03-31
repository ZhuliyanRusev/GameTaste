package com.example.gametaste.model.entity;

import com.example.gametaste.model.entity.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRoleEnum userRoleEnum;
    private Set<Game> gamesSet = new LinkedHashSet<>();
    private Set<Merchandise> merchandisesSet = new LinkedHashSet<>();

    public User() {
    }

    @Column(name = "username",nullable = false,unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public void setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Game> getGamesSet() {
        return gamesSet;
    }

    public void setGamesSet(Set<Game> gamesSet) {
        this.gamesSet = gamesSet;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Merchandise> getMerchandisesSet() {
        return merchandisesSet;
    }

    public void setMerchandisesSet(Set<Merchandise> merchandisesSet) {
        this.merchandisesSet = merchandisesSet;
    }

}
