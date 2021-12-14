package com.bakery.bakery.pie.service.impl;

import com.bakery.bakery.pie.model.TypePie;
import com.bakery.bakery.pie.repository.TypePieRepository;
import com.bakery.bakery.pie.service.TypePieService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypePieServiceImpl extends CrudServiceImpl<TypePie, Long> implements TypePieService{

    @Autowired
    private TypePieRepository repository;

    @Override
    protected GenericRepository<TypePie, Long> repository() {
        return repository; 
    }
    
}
