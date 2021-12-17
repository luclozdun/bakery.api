package com.bakery.bakery.cake.service.impl;

import com.bakery.bakery.cake.model.FillerCake;
import com.bakery.bakery.cake.repository.FillerCakeRepository;
import com.bakery.bakery.cake.service.FillerCakeService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillerCakeServiceImpl extends CrudServiceImpl<FillerCake, Long> implements FillerCakeService{

    @Autowired
    private FillerCakeRepository repository;

    @Override
    protected GenericRepository<FillerCake, Long> repository() {
        return repository;
    }
    
}
