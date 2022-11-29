package com.example.order.service;

import com.example.mongo.model.Order;
import com.example.order.dto.OrderPayLoad;
import com.example.order.dto.OrderView;
import com.example.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderView orderWrite(OrderPayLoad orderPayLoad) {
        Order order = orderPayLoad.toOrder();
        return orderRepository.save(order).toOrderView();
    }
}
