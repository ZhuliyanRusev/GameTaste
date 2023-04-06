package com.example.gametaste.service;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.enums.GameCategory;
import com.example.gametaste.repository.CategoryRepository;
import com.example.gametaste.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    private CategoryServiceImpl testCSI;
    @Mock
    private CategoryRepository mockCategoryRepository;

    private Category testCategory;
    private Category secondTestCategory;

    @BeforeEach
    void setup() {
        testCSI = new CategoryServiceImpl(mockCategoryRepository);
        testCategory = new Category();
        testCategory.setCategoryEnum(GameCategory.ACTION);
        secondTestCategory = new Category();
    }

    @Test
    public void initializeCategoriesTest() {
        when(mockCategoryRepository.count()).thenReturn(0L).thenReturn(1L);
        testCSI.initializeCategories();
        verify(mockCategoryRepository, times(GameCategory.values().length)).save(any(Category.class));
    }

    @Test
    public void initializeCategoriesShouldNotEnterTheIfBlockWhenRepositoryIsFilled() {
        when(mockCategoryRepository.count()).thenReturn(1L);
        testCSI.initializeCategories();
        verify(mockCategoryRepository, never()).save(any(Category.class));
    }


    @Test
    public void findByEnumNameTest() {

        when(mockCategoryRepository.findByCategoryEnum(testCategory.getCategoryEnum()))
                .thenReturn(Optional.of(testCategory));

        secondTestCategory = testCSI.findByEnumName(GameCategory.ACTION);

        Assertions.assertEquals(secondTestCategory.getCategoryEnum(), testCategory.getCategoryEnum());
    }

    @Test
    public void findByCategoryEnumShouldThrowNullPointerException() {

        when(mockCategoryRepository.findByCategoryEnum(null))
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> testCSI.findByEnumName(null));
    }
}
