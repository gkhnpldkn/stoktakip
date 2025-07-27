package com.gokhan.stoktakip.service;

import com.gokhan.stoktakip.entity.Item;
import com.gokhan.stoktakip.entity.ItemTree;
import com.gokhan.stoktakip.repository.ItemTreeRepository;
import com.gokhan.stoktakip.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final StockRepository stockRepository;
    private final ItemTreeRepository itemTreeRepository;

    public List<String> produceItem(String itemCode, int count) {
        List<String> warnings = new ArrayList<>();

        Item parentItem = stockRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı: " + itemCode));

        // Gerekli tüm malzemeleri hesapla (recursive)
        Map<Item, Double> requiredMaterials = new HashMap<>();
        resolveRequiredMaterials(parentItem, count, requiredMaterials);

        // Yeterli stok kontrolü
        for (Map.Entry<Item, Double> entry : requiredMaterials.entrySet()) {
            Item item = entry.getKey();
            Double required = entry.getValue();

            if (item.getQuantity() < required) {
                throw new RuntimeException("Yetersiz stok: " + item.getName() +
                        " (" + item.getItemCode() + ") Gerekli: " + required + ", Mevcut: " + item.getQuantity());
            }
        }

        // Stoklardan düş ve kritik kontrolü
        for (Map.Entry<Item, Double> entry : requiredMaterials.entrySet()) {
            Item item = entry.getKey();
            Double required = entry.getValue();

            item.setQuantity(item.getQuantity() - required);
            stockRepository.save(item);

            if (item.getQuantity() <= item.getCriticalAmount()) {
                warnings.add("⚠️ Kritik stok: " + item.getName() + " (" + item.getItemCode() + ") → Mevcut: "
                        + item.getQuantity() + ", Kritik Seviye: " + item.getCriticalAmount());
            }
        }

        // Üretilen parent ürünü artır
        parentItem.setQuantity(parentItem.getQuantity() + count);
        stockRepository.save(parentItem);

        return warnings;
    }

    private void resolveRequiredMaterials(Item parent, int count, Map<Item, Double> materialMap) {
        List<ItemTree> children = itemTreeRepository.findByParentItem(parent);

        for (ItemTree childRelation : children) {
            Item child = childRelation.getChildItem();
            double totalRequired = count * childRelation.getQuantity();

            materialMap.merge(child, totalRequired, Double::sum);

            boolean isAlsoParent = itemTreeRepository.existsByParentItem(child);
            if (isAlsoParent) {
                resolveRequiredMaterials(child, (int) totalRequired, materialMap);
            }
        }
    }
}
