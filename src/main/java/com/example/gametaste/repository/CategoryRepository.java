package com.example.gametaste.repository;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.enums.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByCategoryEnum(GameCategory categoryEnum);
}
