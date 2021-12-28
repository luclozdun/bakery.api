package com.bakery.bakery.profile.repository;

import com.bakery.bakery.profile.model.ProfileReview;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProfileReviewRepository extends GenericRepository<ProfileReview, Long>{
    
}
