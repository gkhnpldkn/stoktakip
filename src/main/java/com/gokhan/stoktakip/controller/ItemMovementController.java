package com.gokhan.stoktakip.controller;

import com.gokhan.stoktakip.dto.ItemMovementDTO;
import com.gokhan.stoktakip.entity.MovementType;
import com.gokhan.stoktakip.service.ItemMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
public class ItemMovementController {

    private final ItemMovementService itemMovementService;

    @GetMapping("/{itemCode}")
    public List<ItemMovementDTO> listMovements(
            @PathVariable String itemCode,
            @RequestParam(required = false) MovementType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return itemMovementService.listMovements(itemCode, type, from, to);
    }
} 