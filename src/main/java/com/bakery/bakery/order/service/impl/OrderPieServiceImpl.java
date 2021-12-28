package com.bakery.bakery.order.service.impl;

import java.util.List;

import com.bakery.bakery.order.model.OrderPie;
import com.bakery.bakery.order.model.OrderPieId;
import com.bakery.bakery.order.repository.OrderPieRepository;
import com.bakery.bakery.order.service.OrderPieService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPieServiceImpl extends CrudServiceImpl<OrderPie, OrderPieId> implements OrderPieService{

    @Autowired
    private OrderPieRepository repository;

    @Override
    public void saveAll(List<OrderPie> orderPies) throws Exception {
        repository.saveAll(orderPies);
    }

    @Override
    protected GenericRepository<OrderPie, OrderPieId> repository() {
        return repository;
    }
    
}
