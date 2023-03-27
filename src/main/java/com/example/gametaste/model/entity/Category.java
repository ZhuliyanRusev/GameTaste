package com.example.gametaste.model.entity;

import com.example.gametaste.model.entity.enums.GameCategory;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    private GameCategory categoryEnum;
    public Category() {
    }

    @Enumerated(EnumType.STRING)
    public GameCategory getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(GameCategory categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
