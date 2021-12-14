package com.bakery.bakery.pie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.pie.dto.FormPieRequest;
import com.bakery.bakery.pie.dto.FormPieResponse;
import com.bakery.bakery.pie.model.FormPie;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormPieConvert implements ConvertImpl<FormPie, FormPieRequest, FormPieResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public FormPie convertToEntity(FormPieRequest request) {
        return mapper.map(request, FormPie.class);
    }

    @Override
    public FormPieResponse convertToResponse(FormPie entity) {
        return mapper.map(entity, FormPieResponse.class);
    }

    @Override
    public List<FormPieResponse> convertToListResponse(List<FormPie> entities) {
        return entities.stream().map(entity -> mapper.map(entity, FormPieResponse.class)).collect(Collectors.toList());
    }
    
}
