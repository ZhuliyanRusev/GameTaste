package com.example.gametaste.service.impl;

import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.entity.enums.UserRoleEnum;
import com.example.gametaste.model.service.UserServiceModel;
import com.example.gametaste.repository.UserRepository;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;

    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel,User.class);
        user.setUserRoleEnum(UserRoleEnum.USER);
        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public boolean doesUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean doesEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public User findUserById(Long id) {
      return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User currentUser) {
        userRepository.save(currentUser);
    }

    @Override
    public void initializeAdmin() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("Admin");
            user.setEmail("admin@admin");
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setPassword("111");
            user.setUserRoleEnum(UserRoleEnum.ADMIN);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
