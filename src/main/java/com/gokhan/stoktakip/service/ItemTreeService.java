package com.gokhan.stoktakip.service;

import com.gokhan.stoktakip.dto.ItemTreeBulkCreateDTO;
import com.gokhan.stoktakip.entity.Item;
import com.gokhan.stoktakip.entity.ItemTree;
import com.gokhan.stoktakip.repository.ItemTreeRepository;
import com.gokhan.stoktakip.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemTreeService {
    private final StockRepository stockRepository;
    private final ItemTreeRepository itemTreeRepository;

    public List<ItemTree> createTreeBulk(ItemTreeBulkCreateDTO dto) {
        Item parent = stockRepository.findByItemCode(dto.getParentItemCode())
                .orElseThrow(() -> new RuntimeException("Parent item not found: " + dto.getParentItemCode()));

        List<ItemTree> relations = new ArrayList<>();

        for (ItemTreeBulkCreateDTO.ComponentDTO component : dto.getComponents()) {
            Item child = stockRepository.findByItemCode(component.getChildItemCode())
                    .orElseThrow(() -> new RuntimeException("Child item not found: " + component.getChildItemCode()));

            ItemTree tree = new ItemTree();
            tree.setParentItem(parent);
            tree.setChildItem(child);
            tree.setQuantity(component.getQuantity());

            relations.add(tree);
        }

        return itemTreeRepository.saveAll(relations);
    }

    public List<ItemTree> getTreeByItemCode(String itemCode) {
        return itemTreeRepository.findByParentItem_ItemCode(itemCode);
    }

    public void deleteRelationsByItemCode(String itemCode) {
        itemTreeRepository.deleteByParentItem_ItemCode(itemCode);
    }


    public Double calculateTotalCostByItemCode(String itemCode) {
        List<ItemTree> components = itemTreeRepository.findByParentItem_ItemCode(itemCode);
        return components.stream().map(c-> {
            Double price =c.getChildItem().getPrice();
            Double quantity = c.getQuantity();
            return (price != null ? price : 0.0) * (quantity != null ? quantity : 0.0);
        }).reduce(0.0, Double::sum);
    }
}
