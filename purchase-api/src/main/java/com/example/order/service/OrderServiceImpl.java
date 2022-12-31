package com.example.order.service;

import com.example.mongo.model.Drink;
import com.example.mongo.model.Order;
import com.example.mongo.model.Transaction;
import com.example.mongo.model.entitymapper.OrderMapper;
import com.example.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public Order registerBy(Drink drink, Transaction transaction) {
        Order order = orderMapper.drinkTransactionToOrder(drink,transaction);
        return orderRepository.save(order);
    }
}
