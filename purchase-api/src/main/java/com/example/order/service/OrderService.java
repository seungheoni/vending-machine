package com.example.order.service;

import com.example.order.dto.OrderPayLoad;
import com.example.order.dto.OrderView;

public interface OrderService {
    OrderView orderWrite(OrderPayLoad orderPayLoad);
}
