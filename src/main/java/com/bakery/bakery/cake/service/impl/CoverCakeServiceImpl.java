package com.bakery.bakery.cake.service.impl;

import com.bakery.bakery.cake.model.CoverCake;
import com.bakery.bakery.cake.repository.CoverCakeRepository;
import com.bakery.bakery.cake.service.CoverCakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverCakeServiceImpl extends CrudServiceImpl<CoverCake, Long> implements CoverCakeService{

    @Autowired
    private CoverCakeRepository repository;

    @Override
    protected GenericRepository<CoverCake, Long> repository() {
        return repository;
    }
    
}
