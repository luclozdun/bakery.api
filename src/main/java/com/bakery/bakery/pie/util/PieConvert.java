package com.bakery.bakery.pie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.pie.dto.PieRequest;
import com.bakery.bakery.pie.dto.PieResponse;
import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PieConvert implements ConvertImpl<Pie, PieRequest, PieResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public Pie convertToEntity(PieRequest request) {
        return mapper.map(request, Pie.class);
    }

    @Override
    public PieResponse convertToResponse(Pie entity) {
        return mapper.map(entity, PieResponse.class);
    }

    @Override
    public List<PieResponse> convertToListResponse(List<Pie> entities) {
        return entities.stream().map(entity -> mapper.map(entity, PieResponse.class)).collect(Collectors.toList());
    }
    
}
