package com.bakery.bakery.membership.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.membership.dto.SubscriptionRequest;
import com.bakery.bakery.membership.dto.SubscriptionResponse;
import com.bakery.bakery.membership.model.Subscription;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConvert implements ConvertImpl<Subscription, SubscriptionRequest, SubscriptionResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public Subscription convertToEntity(SubscriptionRequest request) {
        return mapper.map(request, Subscription.class);
    }

    @Override
    public SubscriptionResponse convertToResponse(Subscription entity) {
        return mapper.map(entity, SubscriptionResponse.class);
    }

    @Override
    public List<SubscriptionResponse> convertToListResponse(List<Subscription> entities) {
        return entities.stream().map(entity -> mapper.map(entity, SubscriptionResponse.class)).collect(Collectors.toList());
    }
    
}
