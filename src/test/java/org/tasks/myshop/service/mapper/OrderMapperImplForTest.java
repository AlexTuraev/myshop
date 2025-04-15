package org.tasks.myshop.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.dto.OrderDto;

@Component
public class OrderMapperImplForTest implements OrderMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public OrderDto toDto(OrderEntity order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId( order.getOrderId() );
        orderDto.setItemId( order.getItemId() );
        orderDto.setCountItem( order.getCountItem() );
        orderDto.setPrice( order.getPrice() );
        orderDto.setItem( itemMapper.toDto( order.getItem() ) );

        return orderDto;
    }
}

