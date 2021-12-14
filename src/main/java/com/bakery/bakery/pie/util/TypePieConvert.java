package com.bakery.bakery.pie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.pie.dto.TypePieRequest;
import com.bakery.bakery.pie.dto.TypePieResponse;
import com.bakery.bakery.pie.model.TypePie;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypePieConvert implements ConvertImpl<TypePie, TypePieRequest, TypePieResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public TypePie convertToEntity(TypePieRequest request) {
        return mapper.map(request, TypePie.class);
    }

    @Override
    public TypePieResponse convertToResponse(TypePie entity) {
        return mapper.map(entity, TypePieResponse.class);
    }

    @Override
    public List<TypePieResponse> convertToListResponse(List<TypePie> entities) {
        return entities.stream().map(entity -> mapper.map(entity, TypePieResponse.class)).collect(Collectors.toList());
    }
    
}
