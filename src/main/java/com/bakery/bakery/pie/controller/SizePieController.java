package com.bakery.bakery.pie.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.pie.dto.SizePieRequest;
import com.bakery.bakery.pie.dto.SizePieResponse;
import com.bakery.bakery.pie.service.SizePieService;
import com.bakery.bakery.pie.util.SizePieConvert;

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
@RequestMapping("/sizepies")
public class SizePieController {
    
    @Autowired
    private SizePieService sizePieService;

    @Autowired
    private SizePieConvert convert;

    @GetMapping
    private ResponseEntity<List<SizePieResponse>> getList() throws Exception{
        var entities = sizePieService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SizePieResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = sizePieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Size pie not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<SizePieResponse> createSizePie(@Valid @RequestBody SizePieRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        sizePieService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SizePieResponse> updateSizePie(@Valid @RequestBody SizePieRequest request, @Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = sizePieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Size pie not found"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteSizePie(@Valid @PathVariable(name = "id") Long id) throws Exception{
        sizePieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
