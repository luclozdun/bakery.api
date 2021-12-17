package com.bakery.bakery.cake.service.impl;

import com.bakery.bakery.cake.model.TypeCake;
import com.bakery.bakery.cake.repository.TypeCakeRepository;
import com.bakery.bakery.cake.service.TypeCakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeCakeServiceImpl extends CrudServiceImpl<TypeCake, Long> implements TypeCakeService{

    @Autowired
    private TypeCakeRepository repository;

    @Override
    protected GenericRepository<TypeCake, Long> repository() {
        return repository;
    }
    
}
