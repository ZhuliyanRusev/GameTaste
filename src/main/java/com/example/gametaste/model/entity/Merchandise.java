package com.example.gametaste.model.entity;

import com.example.gametaste.model.entity.enums.MerchandiseEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "merchandises")
public class Merchandise extends BaseEntity{

    private String imageUrl;
    private String productTitle;
    private MerchandiseEnum category;
    private BigDecimal price;
    private LocalDate releaseDate;

    //todo Може да сложа и оценка на продукта

    public Merchandise() {
    }

    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Column(nullable = false)
    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    @Enumerated(EnumType.STRING)
    public MerchandiseEnum getCategory() {
        return category;
    }

    public void setCategory(MerchandiseEnum category) {
        this.category = category;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
