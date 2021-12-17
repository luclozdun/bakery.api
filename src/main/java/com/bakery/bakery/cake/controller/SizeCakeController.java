package com.bakery.bakery.cake.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.cake.dto.SizeCakeRequest;
import com.bakery.bakery.cake.dto.SizeCakeResponse;
import com.bakery.bakery.cake.service.SizeCakeService;
import com.bakery.bakery.cake.util.SizeCakeConvert;
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
@RequestMapping("/sizecakes")
public class SizeCakeController {
    
    @Autowired
    private SizeCakeService sizeCakeService;

    @Autowired
    private SizeCakeConvert convert;

    @GetMapping
    private ResponseEntity<List<SizeCakeResponse>> getAll() throws Exception{
        var entities = sizeCakeService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SizeCakeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = sizeCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Size cake not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<SizeCakeResponse> createSizeCake(@Valid @RequestBody SizeCakeRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        sizeCakeService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SizeCakeResponse> updateSizeCake(@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody SizeCakeRequest request) throws Exception{
        var entity = sizeCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Size cake not found"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        sizeCakeService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteSizeCake(@Valid @PathVariable(name = "id") Long id) throws Exception{
        sizeCakeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
