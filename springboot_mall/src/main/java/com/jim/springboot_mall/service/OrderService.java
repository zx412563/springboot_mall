package com.jim.springboot_mall.service;

import com.jim.springboot_mall.dto.CreateOrderRequest;
import com.jim.springboot_mall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
