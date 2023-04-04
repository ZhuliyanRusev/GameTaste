//package com.example.gametaste.service;
//
//import com.example.gametaste.model.entity.Category;
//import com.example.gametaste.model.entity.enums.GameCategory;
//import com.example.gametaste.repository.CategoryRepository;
//import com.example.gametaste.service.impl.CategoryServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CategoryServiceImplTest {
//
//
//    @Mock
//    private CategoryRepository mockCategoryRepository;
//    @Mock
//    private CategoryServiceImpl mockCategoryServiceImpl;
//
////    @Test
////    public void initializeCategories_MustWork() {
////
////        CategoryService categoryService = new CategoryServiceImpl(mockCategoryRepository);
////
////        when(mockCategoryRepository.count()).thenReturn(0L);
////
////        categoryService.initializeCategories();
////
////        verify(mockCategoryRepository, times(GameCategory.values()
////                .length)).save(any(Category.class));
////
////        Assertions.assertEquals(10, mockCategoryRepository.count());
////    }
//    @Test
//    public void findByEnumName_Correctly(){
//        Category category = new Category();
//
//        category.setCategoryEnum(GameCategory.ADVENTURE);
//
//
//
//
//
//        Category foundCategory = mockCategoryServiceImpl.findByEnumName(category.getCategoryEnum());
//
//        assertEquals(category, foundCategory);
//    }
//}