package com.bakery.bakery.security.dto;

import com.bakery.bakery.membership.dto.SubscriptionResponse;
import com.bakery.bakery.profile.dto.ProfileReviewResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerResponse extends PersonResponse{
    private SubscriptionResponse subscription;
    private ProfileReviewResponse profileReview;
}
