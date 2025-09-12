package com.gokhan.stoktakip.repository;

import com.gokhan.stoktakip.entity.ItemMovement;
import com.gokhan.stoktakip.entity.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemMovementRepository extends JpaRepository<ItemMovement, Long> {
    List<ItemMovement> findByItem_ItemCodeOrderByCreatedAtDesc(String itemCode);
    List<ItemMovement> findByItem_ItemCodeAndMovementTypeOrderByCreatedAtDesc(String itemCode, MovementType movementType);
    List<ItemMovement> findByItem_ItemCodeAndCreatedAtBetweenOrderByCreatedAtDesc(String itemCode, LocalDateTime from, LocalDateTime to);
    List<ItemMovement> findByItem_ItemCodeAndMovementTypeAndCreatedAtBetweenOrderByCreatedAtDesc(String itemCode, MovementType movementType, LocalDateTime from, LocalDateTime to);
} 