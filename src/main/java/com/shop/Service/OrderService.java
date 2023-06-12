package com.shop.Service;

import com.shop.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Order save(Order order);
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Integer Id);


}
