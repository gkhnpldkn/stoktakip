package com.gokhan.stoktakip.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    private String name;

    private String itemCode;

    private String description;
    private Long criticalAmount;
    //ÇAP-BOY
    private String birim;
    //MALİYET
private Double price;

}
