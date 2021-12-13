package com.bakery.bakery.pie.service.impl;

import com.bakery.bakery.pie.model.CoverPie;
import com.bakery.bakery.pie.repository.CoverPieRepository;
import com.bakery.bakery.pie.service.CoverPieService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverPieServiceImpl extends CrudServiceImpl<CoverPie, Long> implements CoverPieService{

    @Autowired
    private CoverPieRepository repository;

    @Override
    protected GenericRepository<CoverPie, Long> repository() {
        return repository;
    }
    
}
