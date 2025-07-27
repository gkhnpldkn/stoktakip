package com.gokhan.stoktakip.service;

import com.gokhan.stoktakip.dto.ItemDTO;

import java.util.List;

public interface StockService {
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(Long id);
    ItemDTO getByItemCode(String itemCode);
    ItemDTO addItem(ItemDTO itemDTO);
    ItemDTO updateItem(String itemCode,ItemDTO itemDTO);
    void deleteItem(Long id);

}
