package com.gokhan.stoktakip.repository;

import com.gokhan.stoktakip.entity.Item;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Item, Long> {
  
    Item findByName(String name);

    Optional<Item> findByItemCode(String itemCode);
}
