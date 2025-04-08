package org.tasks.myshop.service.mapper;

import org.mapstruct.Mapper;
import org.tasks.myshop.dao.model.ItemEntity;
import org.tasks.myshop.dto.ItemDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(ItemEntity entity);
    List<ItemDto> toDto(List<ItemEntity> entity);
    ItemEntity toEntity(ItemDto dto);
    List<ItemEntity> toEntity(List<ItemDto> dto);

}
