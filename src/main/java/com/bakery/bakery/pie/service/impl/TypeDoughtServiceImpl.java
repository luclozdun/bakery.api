package com.bakery.bakery.pie.service.impl;

import com.bakery.bakery.pie.model.TypeDought;
import com.bakery.bakery.pie.repository.TypeDoughtRepository;
import com.bakery.bakery.pie.service.TypeDoughtService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeDoughtServiceImpl extends CrudServiceImpl<TypeDought, Long> implements TypeDoughtService{

    @Autowired
    private TypeDoughtRepository repository;

    @Override
    protected GenericRepository<TypeDought, Long> repository() {
        return repository;
    }
    
}
