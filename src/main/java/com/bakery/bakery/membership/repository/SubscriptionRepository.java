package com.bakery.bakery.membership.repository;

import com.bakery.bakery.membership.model.Subscription;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends GenericRepository<Subscription, Long>{
    
}
