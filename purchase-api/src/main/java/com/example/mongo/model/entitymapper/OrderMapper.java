package com.example.mongo.model.entitymapper;

import com.example.mongo.model.Order;
import com.example.order.dto.OrderPayLoad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper {
    Order orderPayLoadToOrder (OrderPayLoad orderPayLoad);
}
