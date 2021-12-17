package com.bakery.bakery.cake.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.cake.dto.TasteCakeRequest;
import com.bakery.bakery.cake.dto.TasteCakeResponse;
import com.bakery.bakery.cake.model.TasteCake;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasteCakeConvert implements ConvertImpl<TasteCake, TasteCakeRequest, TasteCakeResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public TasteCake convertToEntity(TasteCakeRequest request) {
        return mapper.map(request, TasteCake.class);
    }

    @Override
    public TasteCakeResponse convertToResponse(TasteCake entity) {
        return mapper.map(entity, TasteCakeResponse.class);
    }

    @Override
    public List<TasteCakeResponse> convertToListResponse(List<TasteCake> entities) {
        return entities.stream().map(entity -> mapper.map(entity, TasteCakeResponse.class)).collect(Collectors.toList());
    }
    
}
