package com.gokhan.stoktakip.mapper;

import com.gokhan.stoktakip.dto.ItemDTO;
import com.gokhan.stoktakip.entity.Item;
import org.mapstruct.Mapper;


import java.util.List;


@Mapper(componentModel = "spring")
public interface StockMapper {
    ItemDTO toDto(Item item);

    Item toEntity(ItemDTO itemDTO);

    List<ItemDTO> toDtoList(List<Item> users);
    List<Item> toEntityList(List<ItemDTO> dtoList);
}

