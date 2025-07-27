package com.gokhan.stoktakip.controller;


import com.gokhan.stoktakip.dto.ItemDTO;
import com.gokhan.stoktakip.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    @PostMapping
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
        return stockService.addItem(itemDTO);
    }

    @PutMapping("/{itemCode}")
    public ItemDTO updateItem(@PathVariable String itemCode, @RequestBody ItemDTO itemDTO) {
        return stockService.updateItem(itemCode, itemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        stockService.deleteItem(id);
    }

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable Long id) {
        return stockService.getItemById(id);
    }

    @GetMapping(value = "/itemCode")
    public ItemDTO getByItemCode(@RequestParam String itemCode) {
        return stockService.getByItemCode(itemCode);
    }

    @GetMapping("/all")
    public Iterable<ItemDTO> getAllItems() {
        return stockService.getAllItems();
    }

}
