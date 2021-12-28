package com.bakery.bakery.membership.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.membership.dto.ExchangeRequest;
import com.bakery.bakery.membership.dto.ExchangeResponse;
import com.bakery.bakery.membership.model.Exchange;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeConvert implements ConvertImpl<Exchange, ExchangeRequest, ExchangeResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public Exchange convertToEntity(ExchangeRequest request) {
        return mapper.map(request, Exchange.class);
    }

    @Override
    public ExchangeResponse convertToResponse(Exchange entity) {
        return mapper.map(entity, ExchangeResponse.class);
    }

    @Override
    public List<ExchangeResponse> convertToListResponse(List<Exchange> entities) {
        return entities.stream().map(entity -> mapper.map(entity, ExchangeResponse.class)).collect(Collectors.toList());
    }
    
}
