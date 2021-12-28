package com.bakery.bakery.profile.service.impl;

import com.bakery.bakery.profile.model.ProfileReview;
import com.bakery.bakery.profile.repository.ProfileReviewRepository;
import com.bakery.bakery.profile.service.ProfileReviewService;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileReviewServiceImpl extends CrudServiceImpl<ProfileReview, Long> implements ProfileReviewService{

    @Autowired
    private ProfileReviewRepository repository;

    @Override
    protected GenericRepository<ProfileReview, Long> repository() {
        return repository;
    }
    
}
