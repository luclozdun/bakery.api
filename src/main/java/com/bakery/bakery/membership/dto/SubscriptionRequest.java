package com.bakery.bakery.membership.dto;

import lombok.Data;

@Data
public class SubscriptionRequest {
    private String name;
    private String description;
    private Long month;
    private Long exchangeId;
    private Double price;
}
