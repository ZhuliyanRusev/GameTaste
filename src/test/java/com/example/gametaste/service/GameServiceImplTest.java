package com.example.gametaste.service;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.Game;
import com.example.gametaste.model.entity.enums.GameCategory;
import com.example.gametaste.model.service.GameServiceModel;
import com.example.gametaste.model.view.GamesViewModel;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    private Game fourthTestGame;
    private Category category;

    @BeforeEach
    void setup() {
        testGSI = new GameServiceImpl(modelMapper, testCSI, mockGameRepository);

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
        fourthTestGame = new Game();
    }

    @Test
    public void addGameTest() {

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
        Assertions.assertNotEquals(testGameServiceModel.getId(), convertGameServiceModel.getId());
        Assertions.assertEquals(testGameServiceModel.getImageUrl(), testGame.getImageUrl());
        Assertions.assertEquals(testGameServiceModel.getCategory(), testGame.getCategory().getCategoryEnum());
        Assertions.assertEquals(testGameServiceModel.getPrice(), testGame.getPrice());
    }

    @Test
    public void findAllGamesSortByReleaseDateThenByPriceThenByGameSizeDescTest() {

        secondTestGame.setId(2L);
        secondTestGame.setGameName(GAME_NAME);
        secondTestGame.setImageUrl(GAME_IMAGE_URL);

        category.setCategoryEnum(GameCategory.ACTION);
        secondTestGame.setCategory(category);

        secondTestGame.setPrice(BigDecimal.valueOf(60));
        secondTestGame.setReleaseDate(LocalDate.parse("2023-04-15"));
        secondTestGame.setGameSize(BigDecimal.valueOf(79.59));


        thirdTestGame.setId(3L);
        thirdTestGame.setGameName(GAME_NAME);
        thirdTestGame.setImageUrl(GAME_IMAGE_URL);

        category.setCategoryEnum(GameCategory.FPS);
        thirdTestGame.setCategory(category);

        thirdTestGame.setPrice(BigDecimal.valueOf(60));
        thirdTestGame.setReleaseDate(LocalDate.parse("2023-04-15"));
        thirdTestGame.setGameSize(BigDecimal.valueOf(20));


        fourthTestGame.setId(4L);
        fourthTestGame.setGameName(GAME_NAME);
        fourthTestGame.setImageUrl(GAME_IMAGE_URL);

        category.setCategoryEnum(GameCategory.RPG);
        fourthTestGame.setCategory(category);

        fourthTestGame.setPrice(BigDecimal.valueOf(60));
        fourthTestGame.setReleaseDate(LocalDate.parse("2021-04-15"));
        fourthTestGame.setGameSize(BigDecimal.valueOf(50));

        when(mockGameRepository.findAllByOrderByReleaseDateDescPriceDescGameSizeDesc())
                .thenReturn(Arrays.asList(testGame, secondTestGame, fourthTestGame, thirdTestGame));

        List<GamesViewModel> gamesViewModels = testGSI.findAllGamesSortByReleaseDateThenByPriceThenByGameSizeDesc();

        Assertions.assertNotNull(gamesViewModels);
        Assertions.assertEquals(4, gamesViewModels.size());
        Assertions.assertEquals(thirdTestGame.getGameSize(), gamesViewModels.get(3).getGameSize());
    }

    @Test
    public void deleteGameById() {
        Long id1 = 1L;
        Long id2 = 2L;
        Long id3 = 3L;

        testGSI.deleteGameById(id1);
        testGSI.deleteGameById(id2);
        testGSI.deleteGameById(id3);

        verify(mockGameRepository, times(1)).deleteById(id1);
        verify(mockGameRepository, times(1)).deleteById(id2);
        verify(mockGameRepository, times(1)).deleteById(id3);
    }

    @Test
    public void findGameById() {
        when(mockGameRepository.findById(1L)).thenReturn(Optional.ofNullable(testGame));
        secondTestGame = testGSI.findGameById(1L);
        Assertions.assertNotNull(secondTestGame);
        Assertions.assertEquals(secondTestGame.getId(), testGame.getId());
        Assertions.assertEquals(secondTestGame.getCategory(), testGame.getCategory());
    }
}

