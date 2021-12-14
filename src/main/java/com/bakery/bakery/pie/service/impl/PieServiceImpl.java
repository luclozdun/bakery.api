package com.bakery.bakery.pie.service.impl;

import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.pie.repository.PieRepository;
import com.bakery.bakery.pie.service.PieService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PieServiceImpl extends CrudServiceImpl<Pie, Long> implements PieService{

    @Autowired
    private PieRepository repository;

    @Override
    protected GenericRepository<Pie, Long> repository() {
        return repository;
    }
    
}
