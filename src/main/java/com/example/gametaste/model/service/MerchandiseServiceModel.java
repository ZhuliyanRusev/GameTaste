package com.example.gametaste.model.service;

import com.example.gametaste.model.entity.enums.MerchandiseEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MerchandiseServiceModel {

    private Long id;
    private String imageUrl;
    private String productTitle;
    private MerchandiseEnum category;
    private BigDecimal price;
    private LocalDate releaseDate;

    public MerchandiseServiceModel() {
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

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public MerchandiseEnum getCategory() {
        return category;
    }

    public void setCategory(MerchandiseEnum category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
