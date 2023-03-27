package com.example.gametaste.service;

import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.service.UserServiceModel;

public interface UserService {

    boolean doesUsernameExist(String username);

    boolean doesEmailExist(String email);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findUserById(Long id);

    void saveUser(User currentUser);
}
