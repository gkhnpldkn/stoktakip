package com.gokhan.stoktakip.service;

import com.gokhan.stoktakip.dto.ItemMovementDTO;
import com.gokhan.stoktakip.entity.Item;
import com.gokhan.stoktakip.entity.ItemMovement;
import com.gokhan.stoktakip.entity.MovementType;
import com.gokhan.stoktakip.repository.ItemMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemMovementService {
    private final ItemMovementRepository movementRepository;

    public ItemMovement recordMovement(Item item, MovementType type, Double change, Double previous, Double current, String reason, String reference) {
        ItemMovement movement = new ItemMovement();
        movement.setItem(item);
        movement.setMovementType(type);
        movement.setQuantityChange(change);
        movement.setPreviousQuantity(previous);
        movement.setNewQuantity(current);
        movement.setReason(reason);
        movement.setReference(reference);
        return movementRepository.save(movement);
    }

    public List<ItemMovementDTO> listMovements(String itemCode, MovementType type, LocalDateTime from, LocalDateTime to) {
        List<ItemMovement> movements;
        if (from != null && to != null && type != null) {
            movements = movementRepository.findByItem_ItemCodeAndMovementTypeAndCreatedAtBetweenOrderByCreatedAtDesc(itemCode, type, from, to);
        } else if (from != null && to != null) {
            movements = movementRepository.findByItem_ItemCodeAndCreatedAtBetweenOrderByCreatedAtDesc(itemCode, from, to);
        } else if (type != null) {
            movements = movementRepository.findByItem_ItemCodeAndMovementTypeOrderByCreatedAtDesc(itemCode, type);
        } else {
            movements = movementRepository.findByItem_ItemCodeOrderByCreatedAtDesc(itemCode);
        }
        return movements.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ItemMovementDTO toDto(ItemMovement m) {
        ItemMovementDTO dto = new ItemMovementDTO();
        dto.setId(m.getId());
        dto.setItemId(m.getItem().getId());
        dto.setItemCode(m.getItem().getItemCode());
        dto.setItemName(m.getItem().getName());
        dto.setMovementType(m.getMovementType());
        dto.setQuantityChange(m.getQuantityChange());
        dto.setPreviousQuantity(m.getPreviousQuantity());
        dto.setNewQuantity(m.getNewQuantity());
        dto.setReason(m.getReason());
        dto.setReference(m.getReference());
        dto.setCreatedAt(m.getCreatedAt());
        return dto;
    }
} 