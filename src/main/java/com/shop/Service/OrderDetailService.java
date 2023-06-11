package com.shop.Service;

import com.shop.Entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    List<OrderDetail> findAllByOrderId(int id);
    OrderDetail save(OrderDetail orderDetail);

}
