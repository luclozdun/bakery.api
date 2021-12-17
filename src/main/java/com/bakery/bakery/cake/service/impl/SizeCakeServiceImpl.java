package com.bakery.bakery.cake.service.impl;

import com.bakery.bakery.cake.model.SizeCake;
import com.bakery.bakery.cake.repository.SizeCakeRepository;
import com.bakery.bakery.cake.service.SizeCakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeCakeServiceImpl extends CrudServiceImpl<SizeCake, Long> implements SizeCakeService{

    @Autowired
    private SizeCakeRepository repository;

    @Override
    protected GenericRepository<SizeCake, Long> repository() {
        return repository;
    }
    
}
