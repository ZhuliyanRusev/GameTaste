package com.example.gametaste.repository;

import com.example.gametaste.model.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise,Long> {

    List<Merchandise> findAllByOrderByReleaseDateDescPriceDesc();
}
