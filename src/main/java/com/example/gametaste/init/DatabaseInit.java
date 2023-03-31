package com.example.gametaste.init;

import com.example.gametaste.service.CategoryService;
import com.example.gametaste.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;

    public DatabaseInit(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initializeCategories();
        userService.initializeAdmin();
    }
}
