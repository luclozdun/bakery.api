package com.bakery.bakery.membership.service.impl;

import com.bakery.bakery.membership.model.Subscription;
import com.bakery.bakery.membership.repository.SubscriptionRepository;
import com.bakery.bakery.membership.service.SubscriptionService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl extends CrudServiceImpl<Subscription, Long> implements SubscriptionService{

    @Autowired
    private SubscriptionRepository repository;

    @Override
    protected GenericRepository<Subscription, Long> repository() {
        return repository;
    }
    
}
