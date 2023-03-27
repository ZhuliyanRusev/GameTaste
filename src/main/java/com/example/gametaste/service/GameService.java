package com.example.gametaste.service;

import com.example.gametaste.model.entity.Game;
import com.example.gametaste.model.service.GameServiceModel;
import com.example.gametaste.model.view.GamesViewModel;

import java.util.List;

public interface GameService {
    GameServiceModel addGame(GameServiceModel gameServiceModel);

    List<GamesViewModel> findAllGamesSortByPriceDescending();

    void deleteGameById(Long id);

    Game findGameById(Long id);
}
