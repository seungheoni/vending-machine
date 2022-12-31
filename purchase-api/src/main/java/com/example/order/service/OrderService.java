package com.example.order.service;

import com.example.mongo.model.Drink;
import com.example.mongo.model.Order;
import com.example.mongo.model.Transaction;

public interface OrderService {

    Order registerBy(Drink drink, Transaction transaction);
}
