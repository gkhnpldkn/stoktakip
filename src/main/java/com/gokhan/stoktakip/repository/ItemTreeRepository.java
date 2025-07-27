package com.gokhan.stoktakip.repository;

import com.gokhan.stoktakip.entity.Item;
import com.gokhan.stoktakip.entity.ItemTree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTreeRepository extends JpaRepository<ItemTree, Long> {
    List<ItemTree> findByParentItem_ItemCode(String itemCode);
    List<ItemTree> findByParentItem(Item parentItem);

    boolean existsByParentItem(Item parentItem);
    void deleteByParentItem_ItemCode(String itemCode);

}
