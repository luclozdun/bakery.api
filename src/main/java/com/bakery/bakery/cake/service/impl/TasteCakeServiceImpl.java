package com.bakery.bakery.cake.service.impl;

import com.bakery.bakery.cake.model.TasteCake;
import com.bakery.bakery.cake.repository.TasteCakeRepository;
import com.bakery.bakery.cake.service.TasteCakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasteCakeServiceImpl extends CrudServiceImpl<TasteCake, Long> implements TasteCakeService{

    @Autowired
    private TasteCakeRepository repository;

    @Override
    protected GenericRepository<TasteCake, Long> repository() {
        return repository;
    }
    
}
