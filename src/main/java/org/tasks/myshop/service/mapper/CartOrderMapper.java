package org.tasks.myshop.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.tasks.myshop.dao.model.CartEntity;
import org.tasks.myshop.dao.model.OrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartOrderMapper {

    @Mapping(target = "price", source = "cart.item.price")
    OrderEntity CartToOrderEntity(CartEntity cart);

}
