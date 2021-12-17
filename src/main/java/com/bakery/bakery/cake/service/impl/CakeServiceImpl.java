package com.bakery.bakery.cake.service.impl;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.cake.repository.CakeRepository;
import com.bakery.bakery.cake.service.CakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeServiceImpl extends CrudServiceImpl<Cake, Long> implements CakeService{

    @Autowired
    private CakeRepository repository;

    @Override
    protected GenericRepository<Cake, Long> repository() {
        return repository;
    }
    
}
