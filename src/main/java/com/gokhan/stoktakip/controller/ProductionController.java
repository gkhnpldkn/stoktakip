package com.gokhan.stoktakip.controller;

import com.gokhan.stoktakip.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/production")
@RequiredArgsConstructor
public class ProductionController {
 private final ProductionService productionService;

    @PostMapping("/{itemCode}")
    public ResponseEntity<List<String>> produceItem(
            @PathVariable String itemCode,
            @RequestParam int count) {

        List<String> warnings = productionService.produceItem(itemCode, count);
        return ResponseEntity.ok(warnings);
    }


}
