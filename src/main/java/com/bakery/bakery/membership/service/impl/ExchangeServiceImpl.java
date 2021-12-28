package com.bakery.bakery.membership.service.impl;

import com.bakery.bakery.membership.model.Exchange;
import com.bakery.bakery.membership.repository.ExchangeRepository;
import com.bakery.bakery.membership.service.ExchangeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl extends CrudServiceImpl<Exchange, Long> implements ExchangeService{

    @Autowired
    private ExchangeRepository repository;

    @Override
    protected GenericRepository<Exchange, Long> repository() {
        return repository;
    }
    
}
