package com.example.gametaste.service;

import com.example.gametaste.model.entity.News;
import com.example.gametaste.model.service.NewsServiceModel;
import com.example.gametaste.model.view.NewsViewModel;
import com.example.gametaste.repository.NewsRepository;
import com.example.gametaste.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceImplTest {

    private static final String NEWS_DESCRIPTION = "Some description in here.";
    private static final String NEWS_DATE = "2023-04-15";
    private static final String NEWS_TITLE = "Some Title in here";
    private static final String NEWS_IMAGE_URL =
            "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61+yI8cp94L._AC_SL1500_.jpg";
    private NewsServiceImpl testNSI;
    private final ModelMapper modelMapper = new ModelMapper();
    @Mock
    NewsRepository mockNewsRepository;

    private News newsTest;
    private News secondNewsTest;
    private News thirdNewsTest;

    @BeforeEach
    void setup() {
        testNSI = new NewsServiceImpl(mockNewsRepository, modelMapper);
        newsTest = new News();
        secondNewsTest = new News();
        thirdNewsTest = new News();
    }

    @Test
    public void saveNewsTest() {
        NewsServiceModel exampleServiceModel = new NewsServiceModel();
        exampleServiceModel.setId(1L);
        exampleServiceModel.setDescription(NEWS_DESCRIPTION);
        exampleServiceModel.setFromDate(LocalDate.parse(NEWS_DATE));
        exampleServiceModel.setTitle(NEWS_TITLE);
        exampleServiceModel.setImageUrl(NEWS_IMAGE_URL);


        newsTest.setDescription(exampleServiceModel.getDescription());
        newsTest.setId(1L);
        newsTest.setFromDate(exampleServiceModel.getFromDate());
        newsTest.setTitle(exampleServiceModel.getTitle());
        newsTest.setImageUrl(exampleServiceModel.getImageUrl());

        when(mockNewsRepository.save(any(News.class))).thenReturn(newsTest);

        NewsServiceModel newsServiceModel = testNSI.saveNews(exampleServiceModel);

        Assertions.assertEquals(newsServiceModel.getId(), exampleServiceModel.getId());
        Assertions.assertEquals(newsServiceModel.getDescription(), exampleServiceModel.getDescription());
        Assertions.assertEquals(newsServiceModel.getFromDate(), exampleServiceModel.getFromDate());
        Assertions.assertEquals(newsServiceModel.getTitle(), exampleServiceModel.getTitle());
        Assertions.assertEquals(newsServiceModel.getImageUrl(), exampleServiceModel.getImageUrl());
    }

    @Test
    public void findAllNewsSortByReleaseDateDescTest() {
        NewsRepository mockNewsRepository = mock(NewsRepository.class);

        newsTest.setId(1L);
        newsTest.setDescription(NEWS_DESCRIPTION);
        newsTest.setTitle(NEWS_TITLE);
        newsTest.setImageUrl(NEWS_IMAGE_URL);
        newsTest.setFromDate(LocalDate.parse(NEWS_DATE));

        secondNewsTest.setId(2L);
        secondNewsTest.setDescription(NEWS_DESCRIPTION);
        secondNewsTest.setTitle(NEWS_TITLE);
        secondNewsTest.setImageUrl(NEWS_IMAGE_URL);
        secondNewsTest.setFromDate(LocalDate.parse("2028-05-25"));

        thirdNewsTest.setId(2L);
        thirdNewsTest.setDescription(NEWS_DESCRIPTION);
        thirdNewsTest.setTitle(NEWS_TITLE);
        thirdNewsTest.setImageUrl(NEWS_IMAGE_URL);
        thirdNewsTest.setFromDate(LocalDate.parse("2025-05-25"));

        when(mockNewsRepository.findAllByOrderByFromDateDesc())
                .thenReturn(Arrays.asList(secondNewsTest, thirdNewsTest, newsTest));


        NewsServiceImpl testNSI = new NewsServiceImpl(mockNewsRepository, modelMapper);

        List<NewsViewModel> newsViewModelsTest = testNSI.findAllNewsSortByReleaseDateDesc();
        Assertions.assertFalse(newsViewModelsTest.isEmpty());
        Assertions.assertEquals(secondNewsTest.getFromDate(), newsViewModelsTest.get(0).getFromDate());
    }
    @Test
    public void deleteByIdTest() {
        NewsRepository mockNewsRepository = mock(NewsRepository.class);
        NewsServiceImpl testNSI = new NewsServiceImpl(mockNewsRepository, modelMapper);
        
        newsTest.setId(1L);
        newsTest.setDescription(NEWS_DESCRIPTION);
        newsTest.setTitle(NEWS_TITLE);
        newsTest.setImageUrl(NEWS_IMAGE_URL);
        newsTest.setFromDate(LocalDate.parse(NEWS_DATE));

        when(mockNewsRepository.findById(1L)).thenReturn(Optional.of(newsTest));
        mockNewsRepository.save(newsTest);

        Assertions.assertEquals(1, mockNewsRepository.count());

        testNSI.deleteById(1L);

        Assertions.assertEquals(0, mockNewsRepository.count());
    }
}
