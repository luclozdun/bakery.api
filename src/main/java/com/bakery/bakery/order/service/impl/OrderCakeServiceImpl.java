package com.bakery.bakery.order.service.impl;

import java.util.List;

import com.bakery.bakery.order.model.OrderCake;
import com.bakery.bakery.order.model.OrderCakeId;
import com.bakery.bakery.order.repository.OrderCakeRepository;
import com.bakery.bakery.order.service.OrderCakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCakeServiceImpl extends CrudServiceImpl<OrderCake, OrderCakeId> implements OrderCakeService{

    @Autowired
    private OrderCakeRepository repository;

    @Override
    protected GenericRepository<OrderCake, OrderCakeId> repository() {
        return repository;
    }

    @Override
    public void saveAll(List<OrderCake> orderCakes) throws Exception {
        repository.saveAll(orderCakes);
    }

    
}
