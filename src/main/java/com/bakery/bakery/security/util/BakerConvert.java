package com.bakery.bakery.security.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.security.dto.BakerRequest;
import com.bakery.bakery.security.dto.BakerResponse;
import com.bakery.bakery.security.model.Baker;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BakerConvert implements ConvertImpl<Baker, BakerRequest, BakerResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public Baker convertToEntity(BakerRequest request) {
        return mapper.map(request, Baker.class);
    }

    @Override
    public BakerResponse convertToResponse(Baker entity) {
        return mapper.map(entity, BakerResponse.class);
    }

    @Override
    public List<BakerResponse> convertToListResponse(List<Baker> entities) {
        return entities.stream().map(entity -> mapper.map(entity, BakerResponse.class)).collect(Collectors.toList());
    }
    
}
