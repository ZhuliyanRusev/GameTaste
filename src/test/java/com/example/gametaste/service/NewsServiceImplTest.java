package com.example.gametaste.service;

import com.example.gametaste.model.entity.News;
import com.example.gametaste.model.service.NewsServiceModel;
import com.example.gametaste.repository.NewsRepository;
import com.example.gametaste.service.impl.NewsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsServiceImplTest {

    private NewsServiceImpl testNSI;
    private ModelMapper modelMapper = new ModelMapper();
    @Mock
    NewsRepository mockNewsRepository;

    @BeforeEach
    void setup(){
        testNSI = new NewsServiceImpl(mockNewsRepository, modelMapper);
    }

    @Test
    public void saveNewsTest(){
        NewsServiceModel exampleServiceModel = new NewsServiceModel();
        exampleServiceModel.setDescription("Some description in here.");
        exampleServiceModel.setFromDate(LocalDate.parse("2023-04-15"));
        exampleServiceModel.setTitle("Some Title in here");
        exampleServiceModel.setImageUrl("https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61+yI8cp94L._AC_SL1500_.jpg");

        News news = new News();
        news.setDescription(exampleServiceModel.getDescription());
        news.setFromDate(exampleServiceModel.getFromDate());
        news.setTitle(exampleServiceModel.getTitle());
        news.setImageUrl(exampleServiceModel.getImageUrl());

        when(mockNewsRepository.save(any(News.class))).thenReturn(news);

        NewsServiceModel newsServiceModel = testNSI.saveNews(exampleServiceModel);

        Assertions.assertEquals(newsServiceModel.getDescription(),exampleServiceModel.getDescription());
    }
}
