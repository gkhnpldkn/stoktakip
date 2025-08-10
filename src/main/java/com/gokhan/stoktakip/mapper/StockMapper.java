package com.gokhan.stoktakip.mapper;

import com.gokhan.stoktakip.dto.ItemDTO;
import com.gokhan.stoktakip.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(target = "cap", source = "cap")
    @Mapping(target = "boy", source = "boy")
    ItemDTO toDto(Item item);

    @Mapping(target = "cap", source = "cap")
    @Mapping(target = "boy", source = "boy")
    Item toEntity(ItemDTO itemDTO);

    List<ItemDTO> toDtoList(List<Item> items);
    List<Item> toEntityList(List<ItemDTO> dtoList);
}
