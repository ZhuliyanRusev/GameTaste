package com.example.gametaste.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "news")
public class News extends BaseEntity {

    private String imageUrl;
    private LocalDate fromDate;
    private String title;
    private String description;


    public News() {
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(nullable = false)
    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate released) {
        this.fromDate = released;
    }
}
