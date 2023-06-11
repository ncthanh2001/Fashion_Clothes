package com.shop.Service.Impl;

import com.shop.Entity.OrderDetail;
import com.shop.Repository.OrderDetailsRepository;
import com.shop.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Override
    public List<OrderDetail> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public List<OrderDetail> findAllByOrderId(int id) {
        return orderDetailsRepository.findAllByOrderId(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailsRepository.save(orderDetail);
    }
}
