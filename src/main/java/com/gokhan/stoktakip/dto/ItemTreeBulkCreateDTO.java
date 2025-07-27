package com.gokhan.stoktakip.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemTreeBulkCreateDTO {
    private String ParentItemCode;
    private List<ComponentDTO> components;

    @Data
    public static class ComponentDTO {
        private String childItemCode;
        private double quantity;
    }
}
