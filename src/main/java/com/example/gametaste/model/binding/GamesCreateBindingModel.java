package com.example.gametaste.model.binding;

import com.example.gametaste.model.entity.enums.GameCategory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GamesCreateBindingModel {
    private String imageUrl;
    private String gameName;
    private GameCategory category;
    private LocalDate releaseDate;
    private BigDecimal gameSize;
    private BigDecimal price;

    public GamesCreateBindingModel() {
    }

    @NotEmpty(message = "Image URL must not be empty.")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotEmpty(message = "The name of the game must not be left empty!")
    @Size(min = 3 , message = "Game name must be at least 3 characters long.")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    @NotNull(message = "You must select a category!")
    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "You must select a release date of the game!")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Positive(message = "Game size must be positive.")
    @NotNull(message = "Game size must not be empty!")
    public BigDecimal getGameSize() {
        return gameSize;
    }

    public void setGameSize(BigDecimal gameSize) {
        this.gameSize = gameSize;
    }

    @Positive(message = "Price of the game must be positive.")
    @NotNull(message = "Price must not be empty!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
