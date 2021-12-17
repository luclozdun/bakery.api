package com.bakery.bakery.cake.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.cake.dto.FillerCakeRequest;
import com.bakery.bakery.cake.dto.FillerCakeResponse;
import com.bakery.bakery.cake.service.FillerCakeService;
import com.bakery.bakery.cake.util.FillerCakeConvert;
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
@RequestMapping("/fillercakes")
public class FillerCakeController {
    
    @Autowired
    private FillerCakeService fillerCakeService;

    @Autowired
    private FillerCakeConvert convert;

    @GetMapping
    private ResponseEntity<List<FillerCakeResponse>> getAll() throws Exception{
        var entities = fillerCakeService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<FillerCakeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = fillerCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Filler cake not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<FillerCakeResponse> createFillerCake(@Valid @RequestBody FillerCakeRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        fillerCakeService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<FillerCakeResponse> updateFillerCake(@Valid @RequestBody FillerCakeRequest request, @Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = fillerCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Filler cake not found"));
        entity.setDescription(request.getDescription());
        entity.setImage(request.getImage());
        entity.setName(request.getName());
        fillerCakeService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFillerCake(@Valid @PathVariable(name = "id") Long id) throws Exception{
        fillerCakeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
