package com.bakery.bakery.profile.service;

import java.util.List;
import java.util.Optional;

import com.bakery.bakery.profile.model.ProfileReview;
import com.bakery.bakery.service.CrudService;

public interface ProfileReviewService extends CrudService<ProfileReview, Long> {
    Optional<ProfileReview> getByCustomerId(Long id);

    List<ProfileReview> getAllByProcessId(Long process);
}
