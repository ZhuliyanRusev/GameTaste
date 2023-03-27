package com.example.gametaste.model.entity;

import com.example.gametaste.model.entity.enums.GameCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    private String imageUrl;
    private String gameName;
    private Category category;
    private LocalDate releaseDate;
    private BigDecimal gameSize;
    private BigDecimal price;

    public Game() {
    }

    @Column(nullable = false, name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(unique = true,nullable = false,name = "game_name")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(nullable = false,name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(nullable = false)
    public BigDecimal getGameSize() {
        return gameSize;
    }

    public void setGameSize(BigDecimal gameSize) {
        this.gameSize = gameSize;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
