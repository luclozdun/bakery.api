package com.bakery.bakery.order.service.impl;

import com.bakery.bakery.order.model.Order;
import com.bakery.bakery.order.repository.OrderRepository;
import com.bakery.bakery.order.service.OrderService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends CrudServiceImpl<Order, Long> implements OrderService{

    @Autowired
    private OrderRepository repository;

    @Override
    protected GenericRepository<Order, Long> repository() {
        return repository;
    }
    
}
