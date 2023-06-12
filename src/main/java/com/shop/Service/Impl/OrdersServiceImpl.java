package com.shop.Service.Impl;

import com.shop.Entity.Order;
import com.shop.Repository.OrdersRepository;
import com.shop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Override
    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return ordersRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Integer Id) {
        return ordersRepository.findById(Id);
    }


}
