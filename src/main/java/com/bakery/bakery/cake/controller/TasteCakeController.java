package com.bakery.bakery.cake.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.cake.dto.TasteCakeRequest;
import com.bakery.bakery.cake.dto.TasteCakeResponse;
import com.bakery.bakery.cake.service.TasteCakeService;
import com.bakery.bakery.cake.util.TasteCakeConvert;
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
@RequestMapping("/tastecakes")
public class TasteCakeController {
    
    @Autowired
    private TasteCakeService tasteCakeService;

    @Autowired
    private TasteCakeConvert convert;

    @GetMapping
    private ResponseEntity<List<TasteCakeResponse>> getAll() throws Exception{
        var entities = tasteCakeService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TasteCakeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = tasteCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Taste cake not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<TasteCakeResponse> createTasteCake(@Valid @RequestBody TasteCakeRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        tasteCakeService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TasteCakeResponse> updateTasteCake(@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody TasteCakeRequest request) throws Exception{
        var entity = tasteCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Taste not found"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        tasteCakeService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTasteCake(@Valid @PathVariable(name = "id") Long id) throws Exception{
        tasteCakeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
