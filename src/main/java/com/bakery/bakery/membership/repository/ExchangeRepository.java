package com.bakery.bakery.membership.repository;

import com.bakery.bakery.membership.model.Exchange;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends GenericRepository<Exchange, Long>{
    
}
