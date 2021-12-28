package com.bakery.bakery.security.dto;

import lombok.Data;

@Data
public class CustomerSubscriptionRequest {
    private Long customerId;
    private Long subscriptionId;
}
