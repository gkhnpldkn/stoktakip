package com.gokhan.stoktakip.dto;

import com.gokhan.stoktakip.entity.MovementType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemMovementDTO {
    private Long id;
    private Long itemId;
    private String itemCode;
    private String itemName;
    private MovementType movementType;
    private Double quantityChange;
    private Double previousQuantity;
    private Double newQuantity;
    private String reason;
    private String reference;
    private LocalDateTime createdAt;
} 