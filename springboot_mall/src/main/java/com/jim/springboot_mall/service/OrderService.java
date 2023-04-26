package com.jim.springboot_mall.service;

import com.jim.springboot_mall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
