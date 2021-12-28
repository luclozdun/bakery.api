package com.bakery.bakery.order.repository;

import com.bakery.bakery.order.model.Order;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order, Long>{
    
}
