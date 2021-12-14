package com.bakery.bakery.pie.service.impl;

import com.bakery.bakery.pie.model.SizePie;
import com.bakery.bakery.pie.repository.SizePieRepository;
import com.bakery.bakery.pie.service.SizePieService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizePieServiceImpl extends CrudServiceImpl<SizePie, Long> implements SizePieService{

    @Autowired
    private SizePieRepository repository;

    @Override
    protected GenericRepository<SizePie, Long> repository() {
        return repository;
    }
    
}
