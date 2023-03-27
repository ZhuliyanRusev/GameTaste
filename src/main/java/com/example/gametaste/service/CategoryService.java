package com.example.gametaste.service;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.enums.GameCategory;

public interface CategoryService {
    void initializeCategories();

    Category findByEnumName(GameCategory category);
}
