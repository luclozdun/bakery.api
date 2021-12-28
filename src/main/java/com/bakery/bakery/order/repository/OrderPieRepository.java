package com.bakery.bakery.order.repository;

import com.bakery.bakery.order.model.OrderPie;
import com.bakery.bakery.order.model.OrderPieId;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderPieRepository extends GenericRepository<OrderPie, OrderPieId>{
    
}
