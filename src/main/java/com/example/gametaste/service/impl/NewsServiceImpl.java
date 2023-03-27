package com.example.gametaste.service.impl;

import com.example.gametaste.model.entity.News;
import com.example.gametaste.model.service.NewsServiceModel;
import com.example.gametaste.model.view.NewsViewModel;
import com.example.gametaste.repository.NewsRepository;
import com.example.gametaste.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NewsServiceModel saveNews(NewsServiceModel newsServiceModel) {
        News news = modelMapper.map(newsServiceModel,News.class);
        return modelMapper.map(newsRepository.save(news), NewsServiceModel.class);
    }

    @Override
    public List<NewsViewModel> findAllNewsSortByReleaseDateDesc() {
        return newsRepository.findAllByOrderByFromDateDesc()
                .stream()
                .map(news -> modelMapper.map(news, NewsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
