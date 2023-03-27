package com.example.gametaste.service;

import com.example.gametaste.model.service.NewsServiceModel;
import com.example.gametaste.model.view.NewsViewModel;

import java.util.List;

public interface NewsService {
    NewsServiceModel saveNews(NewsServiceModel newsServiceModel);

    List<NewsViewModel> findAllNewsSortByReleaseDateDesc();

    void deleteById(Long id);
}
