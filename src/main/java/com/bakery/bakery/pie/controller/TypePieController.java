package com.bakery.bakery.pie.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.pie.dto.TypePieRequest;
import com.bakery.bakery.pie.dto.TypePieResponse;
import com.bakery.bakery.pie.service.TypePieService;
import com.bakery.bakery.pie.util.TypePieConvert;

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
@RequestMapping("/typepies")
public class TypePieController {
    
    @Autowired
    private TypePieService typePieService;

    @Autowired
    private TypePieConvert convert;

    @GetMapping
    private ResponseEntity<List<TypePieResponse>> getAll() throws Exception{
        var entities = typePieService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TypePieResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = typePieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Type pie not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<TypePieResponse> createTypePie(@Valid @RequestBody TypePieRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        typePieService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TypePieResponse> updateTypePie(@Valid @RequestBody TypePieRequest request, @Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = typePieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Type pie not found"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        typePieService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTypePie(@Valid @PathVariable(name = "id") Long id) throws Exception{
        typePieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
