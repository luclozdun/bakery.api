package com.bakery.bakery.profile.repository;

import java.util.List;
import java.util.Optional;

import com.bakery.bakery.profile.model.ProfileReview;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProfileReviewRepository extends GenericRepository<ProfileReview, Long> {
    Optional<ProfileReview> getProfileReviewByCustomerId(Long id);

    List<ProfileReview> getProfileReviewsByProcess(Long process);
}
