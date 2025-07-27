package com.gokhan.stoktakip.controller;

import com.gokhan.stoktakip.dto.ItemTreeBulkCreateDTO;
import com.gokhan.stoktakip.entity.ItemTree;
import com.gokhan.stoktakip.service.ItemTreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-tree")
@RequiredArgsConstructor
public class ItemTreeController {

    private final ItemTreeService itemTreeService;

    // Tek seferde tüm bileşenleri eklemek için
    @PostMapping("/bulk")
    public List<ItemTree> createTreeInBulk(@RequestBody ItemTreeBulkCreateDTO dto) {
        return itemTreeService.createTreeBulk(dto);
    }

    // Belirli bir ürünün bileşenlerini listelemek için
    @GetMapping("/{itemCode}")
    public List<ItemTree> getTreeByItemCode(@PathVariable String itemCode) {
        return itemTreeService.getTreeByItemCode(itemCode);
    }

    // Bir ilişkiyi silmek için (ID ile)
    @DeleteMapping("/{itemCode}")
    public void deleteRelation(@PathVariable String itemCode) {
        itemTreeService.deleteRelationsByItemCode(itemCode);
    }
    @GetMapping("/{itemCode}/total-cost")
    public Double getTotalCost(@PathVariable String itemCode) {
        return itemTreeService.calculateTotalCostByItemCode( itemCode);
    }
}