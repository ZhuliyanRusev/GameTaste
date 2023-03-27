package com.example.gametaste.model.view;

import com.example.gametaste.model.entity.enums.GameCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GamesViewModel {
    private Long id;
    private String imageUrl;
    private String gameName;
    private GameCategory category;
    private LocalDate releaseDate;
    private BigDecimal price;
    private BigDecimal gameSize;

    public GamesViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGameSize() {
        return gameSize;
    }

    public void setGameSize(BigDecimal gameSize) {
        this.gameSize = gameSize;
    }
}
