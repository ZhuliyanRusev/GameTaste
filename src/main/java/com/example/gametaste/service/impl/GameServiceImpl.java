package com.example.gametaste.service.impl;

import com.example.gametaste.model.entity.Game;
import com.example.gametaste.model.service.GameServiceModel;
import com.example.gametaste.model.view.GamesViewModel;
import com.example.gametaste.repository.GameRepository;
import com.example.gametaste.service.CategoryService;
import com.example.gametaste.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final GameRepository gameRepository;

    public GameServiceImpl(ModelMapper modelMapper, CategoryService categoryService, GameRepository gameRepository) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.gameRepository = gameRepository;
    }

    @Override
    public GameServiceModel addGame(GameServiceModel gameServiceModel) {
        Game game = modelMapper.map(gameServiceModel,Game.class);
        game.setCategory(categoryService.findByEnumName(gameServiceModel.getCategory()));

        return modelMapper.map(gameRepository.save(game), GameServiceModel.class);

    }

    @Override
    public List<GamesViewModel> findAllGamesSortByPriceDescending() {
       return gameRepository.findAllByOrderByReleaseDateDescPriceDescGameSizeDesc()
               .stream()
               .map(game -> modelMapper.map(game, GamesViewModel.class))
               .collect(Collectors.toList());
    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }
}
