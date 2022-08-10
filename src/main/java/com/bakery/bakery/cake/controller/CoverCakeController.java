package com.bakery.bakery.cake.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.cake.dto.CoverCakeRequest;
import com.bakery.bakery.cake.dto.CoverCakeResponse;
import com.bakery.bakery.cake.service.CoverCakeService;
import com.bakery.bakery.cake.util.CoverCakeConvert;
import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;

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
@RequestMapping("/covercakes")
public class CoverCakeController {
    
    @Autowired
    private CoverCakeService coverCakeService;

    @Autowired
    private CoverCakeConvert convert;

    @GetMapping
    private ResponseEntity<List<CoverCakeResponse>> getAll() throws Exception{
        var entities = coverCakeService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CoverCakeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = coverCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cover Cake not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<CoverCakeResponse> createCoverCake(@Valid @RequestBody CoverCakeRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        coverCakeService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CoverCakeResponse> updateCoverCake(@Valid @RequestBody CoverCakeRequest request, @Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = coverCakeService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cover cake not found"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        coverCakeService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<CoverCakeResponse> deleteCoverCake(@Valid @PathVariable(name = "id") Long id) throws Exception{
        coverCakeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
