package com.example.gametaste.model.service;

import com.example.gametaste.model.entity.enums.GameCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameServiceModel {
    private Long id;
    private String imageUrl;
    private String gameName;
    private GameCategory category;
    private LocalDate releaseDate;
    private BigDecimal price;
    private BigDecimal gameSize;
    public GameServiceModel() {
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

    public BigDecimal getGameSize() {
        return gameSize;
    }

    public void setGameSize(BigDecimal gameSize) {
        this.gameSize = gameSize;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
