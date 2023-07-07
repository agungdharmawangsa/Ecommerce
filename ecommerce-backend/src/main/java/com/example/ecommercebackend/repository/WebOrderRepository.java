package com.example.ecommercebackend.repository;

import com.example.ecommercebackend.model.User;
import com.example.ecommercebackend.model.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {

    List<WebOrder> findByUser(User user);
}
