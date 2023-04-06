package com.example.gametaste.service;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.Game;
import com.example.gametaste.model.entity.enums.GameCategory;
import com.example.gametaste.model.service.GameServiceModel;
import com.example.gametaste.repository.CategoryRepository;
import com.example.gametaste.repository.GameRepository;
import com.example.gametaste.service.impl.CategoryServiceImpl;
import com.example.gametaste.service.impl.GameServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    private final String GAME_IMAGE_URL = "https://images4.alphacoders.com/115/1151767.jpg";
    private static final String GAME_RELEASE_DATE = "2023-04-15";
    private static final String GAME_NAME = "Some game name in here";

    private GameServiceImpl testGSI;

    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private CategoryRepository mockCategoryRepository;

    @InjectMocks
    private CategoryServiceImpl testCSI;

    @Mock
    private GameRepository mockGameRepository;

    private Game testGame;
    private Game secondTestGame;
    private Game thirdTestGame;
    private Category category;

    @BeforeEach
    void setup(){
        testGSI = new GameServiceImpl(modelMapper, testCSI ,mockGameRepository);

        category = new Category();
        category.setCategoryEnum(GameCategory.ADVENTURE);
        category.setId(1L);

        testGame = new Game();
        testGame.setId(1L);
        testGame.setGameName(GAME_NAME);
        testGame.setImageUrl(GAME_IMAGE_URL);
        testGame.setCategory(category);
        testGame.setPrice(BigDecimal.valueOf(70));
        testGame.setReleaseDate(LocalDate.parse(GAME_RELEASE_DATE));
        testGame.setGameSize(BigDecimal.valueOf(79.59));

        secondTestGame = new Game();
        thirdTestGame = new Game();
    }

    @Test
    public void addGameTest(){

        GameServiceModel convertGameServiceModel = new GameServiceModel();

        convertGameServiceModel.setId(2L);
        convertGameServiceModel.setGameName(GAME_NAME);
        convertGameServiceModel.setImageUrl(GAME_IMAGE_URL);
        convertGameServiceModel.setCategory(GameCategory.ADVENTURE);
        convertGameServiceModel.setPrice(BigDecimal.valueOf(70));
        convertGameServiceModel.setReleaseDate(LocalDate.parse(GAME_RELEASE_DATE));
        convertGameServiceModel.setGameSize(BigDecimal.valueOf(79.59));

        when(mockGameRepository.save(any(Game.class))).thenReturn(testGame);
        when(mockCategoryRepository.findByCategoryEnum(any(GameCategory.class))).thenReturn(Optional.of(category));

        GameServiceModel testGameServiceModel = testGSI.addGame(convertGameServiceModel);

        Assertions.assertEquals(testGameServiceModel.getId(), testGame.getId());
        Assertions.assertNotEquals(testGameServiceModel.getId(),convertGameServiceModel.getId());
        Assertions.assertEquals(testGameServiceModel.getImageUrl(), testGame.getImageUrl());
        Assertions.assertEquals(testGameServiceModel.getCategory(), testGame.getCategory().getCategoryEnum());
        Assertions.assertEquals(testGameServiceModel.getPrice(), testGame.getPrice());
    }
}

