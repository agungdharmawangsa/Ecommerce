package com.example.ecommercebackend.api.controller.order;

import com.example.ecommercebackend.model.User;
import com.example.ecommercebackend.model.WebOrder;
import com.example.ecommercebackend.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal User user){
        return orderService.getOrders(user);
    }
}
