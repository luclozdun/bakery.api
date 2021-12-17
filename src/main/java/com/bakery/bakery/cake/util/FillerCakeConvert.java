package com.bakery.bakery.cake.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.cake.dto.FillerCakeRequest;
import com.bakery.bakery.cake.dto.FillerCakeResponse;
import com.bakery.bakery.cake.model.FillerCake;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FillerCakeConvert implements ConvertImpl<FillerCake, FillerCakeRequest, FillerCakeResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public FillerCake convertToEntity(FillerCakeRequest request) {
        return mapper.map(request, FillerCake.class);
    }

    @Override
    public FillerCakeResponse convertToResponse(FillerCake entity) {
        return mapper.map(entity, FillerCakeResponse.class);
    }

    @Override
    public List<FillerCakeResponse> convertToListResponse(List<FillerCake> entities) {
        return entities.stream().map(entity -> mapper.map(entity, FillerCakeResponse.class)).collect(Collectors.toList());
    }
    
}
