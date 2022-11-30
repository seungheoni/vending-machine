package com.example.order.service;

import com.example.mongo.model.Order;
import com.example.order.dto.OrderPayLoad;

public interface OrderService {
    Order registerBy(OrderPayLoad orderPayLoad);
}
