package com.bakery.bakery.security.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.security.dto.OwnerRequest;
import com.bakery.bakery.security.dto.OwnerResponse;
import com.bakery.bakery.security.model.Owner;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerConvert implements ConvertImpl<Owner, OwnerRequest, OwnerResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public Owner convertToEntity(OwnerRequest request) {
        return mapper.map(request, Owner.class);
    }

    @Override
    public OwnerResponse convertToResponse(Owner entity) {
        return mapper.map(entity, OwnerResponse.class);
    }

    @Override
    public List<OwnerResponse> convertToListResponse(List<Owner> entities) {
        return entities.stream().map(entity -> mapper.map(entity, OwnerResponse.class)).collect(Collectors.toList());
    }
    
}
