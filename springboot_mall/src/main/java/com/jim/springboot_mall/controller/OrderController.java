package com.jim.springboot_mall.controller;

import com.jim.springboot_mall.dto.CreateOrderRequest;
import com.jim.springboot_mall.dto.OrderQueryParams;
import com.jim.springboot_mall.model.Order;
import com.jim.springboot_mall.service.OrderService;
import com.jim.springboot_mall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "10") @Max(100) @Min(0) Integer limit,
                                                 @RequestParam(defaultValue = "0") @Min(0) Integer offest) {
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offest);
        orderQueryParams.setUserId(userId);

        //取得orderList
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        //取得order總數
        Integer count = orderService.countOrder(orderQueryParams);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offest);
        page.setTotal(count);
        page.setResult(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);


    }

}
