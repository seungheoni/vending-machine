package com.example.order.service;

import com.example.mongo.model.Order;
import com.example.mongo.model.entitymapper.OrderMapper;
import com.example.order.dto.OrderPayLoad;
import com.example.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public Order registerBy(OrderPayLoad orderPayLoad) {
        Order order = orderMapper.orderPayLoadToOrder(orderPayLoad);
        return orderRepository.save(order);
    }
}
