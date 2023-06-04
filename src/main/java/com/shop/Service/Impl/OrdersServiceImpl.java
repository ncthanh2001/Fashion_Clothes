package com.shop.Service.Impl;

import com.shop.Entity.Order;
import com.shop.Repository.OrdersRepository;
import com.shop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Override
    public List<Order> findAll() {
        return ordersRepository.findAll();
    }
}
