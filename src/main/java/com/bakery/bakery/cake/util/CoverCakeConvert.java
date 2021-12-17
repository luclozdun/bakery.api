package com.bakery.bakery.cake.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.cake.dto.CoverCakeRequest;
import com.bakery.bakery.cake.dto.CoverCakeResponse;
import com.bakery.bakery.cake.model.CoverCake;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoverCakeConvert implements ConvertImpl<CoverCake, CoverCakeRequest, CoverCakeResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public CoverCake convertToEntity(CoverCakeRequest request) {
        return mapper.map(request, CoverCake.class);
    }

    @Override
    public CoverCakeResponse convertToResponse(CoverCake entity) {
        return mapper.map(entity, CoverCakeResponse.class);
    }

    @Override
    public List<CoverCakeResponse> convertToListResponse(List<CoverCake> entities) {
        return entities.stream().map(entity -> mapper.map(entity, CoverCakeResponse.class)).collect(Collectors.toList());
    }
    
    
}
