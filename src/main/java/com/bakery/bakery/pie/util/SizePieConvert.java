package com.bakery.bakery.pie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.pie.dto.SizePieRequest;
import com.bakery.bakery.pie.dto.SizePieResponse;
import com.bakery.bakery.pie.model.SizePie;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SizePieConvert implements ConvertImpl<SizePie, SizePieRequest, SizePieResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public SizePie convertToEntity(SizePieRequest request) {
        return mapper.map(request, SizePie.class);
    }

    @Override
    public SizePieResponse convertToResponse(SizePie entity) {
        return mapper.map(entity, SizePieResponse.class);
    }

    @Override
    public List<SizePieResponse> convertToListResponse(List<SizePie> entities) {
        return entities.stream().map(entity -> mapper.map(entity, SizePieResponse.class)).collect(Collectors.toList());
    }
    
}
