package com.bakery.bakery.order.service;

import java.util.List;

import com.bakery.bakery.order.model.OrderPie;
import com.bakery.bakery.order.model.OrderPieId;
import com.bakery.bakery.service.CrudService;

public interface OrderPieService extends CrudService<OrderPie, OrderPieId>{
    void saveAll(List<OrderPie> orderPies) throws Exception;
}
