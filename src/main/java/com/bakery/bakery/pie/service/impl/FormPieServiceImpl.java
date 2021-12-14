package com.bakery.bakery.pie.service.impl;

import com.bakery.bakery.pie.model.FormPie;
import com.bakery.bakery.pie.repository.FormPieRepository;
import com.bakery.bakery.pie.service.FormPieService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormPieServiceImpl extends CrudServiceImpl<FormPie, Long> implements FormPieService{

    @Autowired
    private FormPieRepository repository;

    @Override
    protected GenericRepository<FormPie, Long> repository() {
        return repository;
    }
    
}
