package com.bakery.bakery.cake.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.cake.dto.TypeCakeRequest;
import com.bakery.bakery.cake.dto.TypeCakeResponse;
import com.bakery.bakery.cake.service.TypeCakeService;
import com.bakery.bakery.cake.util.TypeCakeConvert;
import com.bakery.bakery.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("typecakes")
public class TypeCakeController {
    
    @Autowired
    private TypeCakeService typeCakeService;

    @Autowired
    private TypeCakeConvert convert;

    @GetMapping
    private ResponseEntity<List<TypeCakeResponse>> getAll() throws Exception{
        var entities = typeCakeService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TypeCakeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = typeCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Type cake not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity),HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<TypeCakeResponse> createTypeCake(@Valid @RequestBody TypeCakeRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        typeCakeService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TypeCakeResponse> updateTypeCake(@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody TypeCakeRequest request) throws Exception{
        var entity = typeCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Type cake not found"));
        entity.setDescription(request.getDescription());
        entity.setImage(request.getImage());
        entity.setName(request.getName());
        typeCakeService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTypeCake(@Valid @PathVariable(name = "id") Long id) throws Exception{
        typeCakeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
