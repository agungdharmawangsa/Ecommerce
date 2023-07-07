package com.example.ecommercebackend.service;

import com.example.ecommercebackend.model.User;
import com.example.ecommercebackend.model.WebOrder;
import com.example.ecommercebackend.repository.WebOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private WebOrderRepository webOrderRepository;

    public OrderService(WebOrderRepository webOrderRepository) {
        this.webOrderRepository = webOrderRepository;
    }

    public List<WebOrder> getOrders(User user){
        return webOrderRepository.findByUser(user);
    }

}
