package com.example.gametaste.model.binding;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class NewsCreateBindingModel {
    private String imageUrl;
    private LocalDate fromDate;
    private String title;
    private String description;

    public NewsCreateBindingModel() {
    }

    @NotEmpty(message = "ImageUrl must not be left empty!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotEmpty(message = "Title must not be left empty!")
    @Size(min = 3 , message = "Title must be at least 3 characters long.")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = "Description must not be left empty!")
    @Size(min = 20, message = "Description must be at least 20 characters long.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
}
