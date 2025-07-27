package com.gokhan.stoktakip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_tree")
public class ItemTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Parent ürün (örneğin: FRN-001)
    @ManyToOne
    @JoinColumn(name = "parent_item_id", referencedColumnName = "id")
    private Item parentItem;

    // Alt bileşen (örneğin: CIVA-001)
    @ManyToOne
    @JoinColumn(name = "child_item_id", referencedColumnName = "id")
    private Item childItem;

    // Bu bileşenden kaç tane kullanıldı
    private Double quantity;
}
