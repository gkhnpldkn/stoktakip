package com.gokhan.stoktakip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDTO {
   private Long id;
   private Double quantity;
   private String name;
   private String itemCode;
   private String description;
   private Long criticalAmount;
   private String birim;
   private Double price;

   @JsonProperty("cap")
   private Double cap;

   @JsonProperty("boy")
   private Double boy;



}
