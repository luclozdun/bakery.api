package com.bakery.bakery.cake.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.cake.dto.TypeCakeRequest;
import com.bakery.bakery.cake.dto.TypeCakeResponse;
import com.bakery.bakery.cake.model.TypeCake;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeCakeConvert implements ConvertImpl<TypeCake, TypeCakeRequest, TypeCakeResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public TypeCake convertToEntity(TypeCakeRequest request) {
        return mapper.map(request, TypeCake.class);
    }

    @Override
    public TypeCakeResponse convertToResponse(TypeCake entity) {
        return mapper.map(entity, TypeCakeResponse.class);
    }

    @Override
    public List<TypeCakeResponse> convertToListResponse(List<TypeCake> entities) {
        return entities.stream().map(entity -> mapper.map(entity, TypeCakeResponse.class)).collect(Collectors.toList());
    }
    
}
