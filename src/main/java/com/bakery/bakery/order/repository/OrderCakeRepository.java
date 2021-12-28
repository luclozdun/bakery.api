package com.bakery.bakery.order.repository;

import com.bakery.bakery.order.model.OrderCake;
import com.bakery.bakery.order.model.OrderCakeId;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderCakeRepository extends GenericRepository<OrderCake, OrderCakeId>{
    
}
