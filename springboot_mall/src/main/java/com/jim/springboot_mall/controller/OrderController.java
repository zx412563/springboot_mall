package com.jim.springboot_mall.controller;

import com.jim.springboot_mall.dto.CreateOrderRequest;
import com.jim.springboot_mall.model.Order;
import com.jim.springboot_mall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        Integer orderId = orderService.createOrder(userId, createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

}
