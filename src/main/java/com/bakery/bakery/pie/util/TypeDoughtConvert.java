package com.bakery.bakery.pie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.pie.dto.TypeDoughtRequest;
import com.bakery.bakery.pie.dto.TypeDoughtResponse;
import com.bakery.bakery.pie.model.TypeDought;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeDoughtConvert implements ConvertImpl<TypeDought, TypeDoughtRequest, TypeDoughtResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public TypeDought convertToEntity(TypeDoughtRequest request) {
        return mapper.map(request, TypeDought.class);
    }

    @Override
    public TypeDoughtResponse convertToResponse(TypeDought entity) {
        return mapper.map(entity, TypeDoughtResponse.class);
    }

    @Override
    public List<TypeDoughtResponse> convertToListResponse(List<TypeDought> entities) {
        return entities.stream().map(entity -> mapper.map(entity, TypeDoughtResponse.class)).collect(Collectors.toList());
    }
    
}
    