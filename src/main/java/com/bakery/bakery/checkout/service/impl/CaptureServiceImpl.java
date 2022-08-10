package com.bakery.bakery.checkout.service.impl;

import com.bakery.bakery.checkout.model.Capture;
import com.bakery.bakery.checkout.repository.CaptureRepository;
import com.bakery.bakery.checkout.service.CaptureService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptureServiceImpl extends CrudServiceImpl<Capture, String> implements CaptureService {

    @Autowired
    private CaptureRepository repository;

    @Override
    protected GenericRepository<Capture, String> repository() {
        return repository;
    }

}
