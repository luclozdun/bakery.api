package com.bakery.bakery.pie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.pie.dto.CoverPieRequest;
import com.bakery.bakery.pie.dto.CoverPieResponse;
import com.bakery.bakery.pie.model.CoverPie;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoverPieConvert implements ConvertImpl<CoverPie, CoverPieRequest, CoverPieResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public CoverPie convertToEntity(CoverPieRequest request) {
        var entity = mapper.map(request, CoverPie.class);
        return entity;
    }

    @Override
    public CoverPieResponse convertToResponse(CoverPie entity) {
        var response = mapper.map(entity, CoverPieResponse.class);
        return response;
    }

    @Override
    public List<CoverPieResponse> convertToListResponse(List<CoverPie> entities) {
        var list = entities.stream().map(entity -> mapper.map(entity, CoverPieResponse.class)).collect(Collectors.toList());
        return list;
    }
}
