package com.example.gametaste.model.binding;

import com.example.gametaste.model.entity.enums.MerchandiseEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MerchandiseCreateBindingModel{
    private String imageUrl;
    private String productTitle;
    private MerchandiseEnum category;
    private BigDecimal price;
    private LocalDate releaseDate;

    public MerchandiseCreateBindingModel() {
    }


    @NotEmpty(message = "Image URL must not be empty.")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @NotEmpty(message = "The name of the merchandise must not be left empty!")
    @Size(min = 3 , message = "Title of the merchandise must be at least 3 characters long.")
    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    @NotNull(message = "You must select a category!")
    public MerchandiseEnum getCategory() {
        return category;
    }

    public void setCategory(MerchandiseEnum category) {
        this.category = category;
    }

    @Positive(message = "Price of the merchandise must be positive.")
    @NotNull(message = "Price must not be empty!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "You must select a release date of the merchandise!")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
