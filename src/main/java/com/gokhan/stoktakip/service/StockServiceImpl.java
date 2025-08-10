package com.gokhan.stoktakip.service;

import com.gokhan.stoktakip.dto.ItemDTO;
import com.gokhan.stoktakip.entity.Item;
import com.gokhan.stoktakip.mapper.StockMapper;
import com.gokhan.stoktakip.repository.StockRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class StockServiceImpl implements StockService {
    private StockRepository stockRepository;
    private StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }


    @Override
    public List<ItemDTO> getAllItems() {
        return stockMapper.toDtoList(stockRepository.findAll());
    }

    @Override
    public ItemDTO getItemById(Long id) {
        return stockRepository.findById(id).
                map(stockMapper::toDto).orElse(null);
    }

    @Override
    public ItemDTO getByItemCode(String itemCode) {
        if (itemCode == null || itemCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Ürün kodu boş veya null olamaz.");
        }

        return stockRepository.findByItemCode(itemCode)
                .map(stockMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Bu koda sahip ürün yoktur: " + itemCode));
    }


    @Override
    public ItemDTO addItem(ItemDTO dto) {
        Item item = stockMapper.toEntity(dto);
        Item saved = stockRepository.save(item);
        return stockMapper.toDto(saved);
    }

    @Override
    public ItemDTO updateItem(String itemCode, ItemDTO dto) {
        // itemCode ile ara
        Item item = stockRepository.findByItemCode(itemCode).orElse(null);

        if (item != null) {
            // itemCode güncellenmesi genelde önerilmez, ama istersen dto'dan da alabilirsin
            // item.setItemCode(dto.getItemCode());

            item.setName(dto.getName());
            item.setQuantity(dto.getQuantity());
            item.setDescription(dto.getDescription());
            item.setCriticalAmount(dto.getCriticalAmount());
            item.setBirim(dto.getBirim());
            item.setPrice(dto.getPrice());
            item.setCap(dto.getCap());
            item.setBoy(dto.getBoy());

            Item updated = stockRepository.save(item);
            return stockMapper.toDto(updated);
        }

        return null;
    }

    @Override
    public void deleteItem(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new RuntimeException("Item not found");
        }
        stockRepository.deleteById(id);
    }
    //ÜRÜNAĞACI OLUŞTURMA (HANGİ ÜRÜNLERDEN OLUŞMUŞ ONU BELİRLEYECEĞİZ)

}
