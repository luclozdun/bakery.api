package com.bakery.bakery.cake.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.cake.dto.SizeCakeRequest;
import com.bakery.bakery.cake.dto.SizeCakeResponse;
import com.bakery.bakery.cake.model.SizeCake;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SizeCakeConvert implements ConvertImpl<SizeCake, SizeCakeRequest, SizeCakeResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public SizeCake convertToEntity(SizeCakeRequest request) {
        return mapper.map(request, SizeCake.class);
    }

    @Override
    public SizeCakeResponse convertToResponse(SizeCake entity) {
        return mapper.map(entity, SizeCakeResponse.class);
    }

    @Override
    public List<SizeCakeResponse> convertToListResponse(List<SizeCake> entities) {
        return entities.stream().map(entity -> mapper.map(entity, SizeCakeResponse.class)).collect(Collectors.toList());
    }
    
}
