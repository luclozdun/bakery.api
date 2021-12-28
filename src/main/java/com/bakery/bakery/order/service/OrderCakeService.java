package com.bakery.bakery.order.service;

import java.util.List;

import com.bakery.bakery.order.model.OrderCake;
import com.bakery.bakery.order.model.OrderCakeId;
import com.bakery.bakery.service.CrudService;

public interface OrderCakeService extends CrudService<OrderCake, OrderCakeId>{
    void saveAll(List<OrderCake> orderCakes) throws Exception;
}
