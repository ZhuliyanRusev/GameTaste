package com.example.gametaste.service.impl;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.enums.GameCategory;
import com.example.gametaste.repository.CategoryRepository;
import com.example.gametaste.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {

        if (categoryRepository.count() == 0) {
            Arrays.stream(GameCategory.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setCategoryEnum(categoryName);
                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByEnumName(GameCategory category) {
        return categoryRepository.findByCategoryEnum(category).orElse(null);
    }
}
