package com.bakery.bakery.pie.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.pie.dto.TypeDoughtRequest;
import com.bakery.bakery.pie.dto.TypeDoughtResponse;
import com.bakery.bakery.pie.service.TypeDoughtService;
import com.bakery.bakery.pie.util.TypeDoughtConvert;

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
@RequestMapping("/typedoughts")
public class TypeDoughtController {
    

    @Autowired
    private TypeDoughtService typeDoughtService;

    @Autowired
    private TypeDoughtConvert convert;

    @GetMapping
    private ResponseEntity<List<TypeDoughtResponse>> getAll() throws Exception{
        var entities = typeDoughtService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TypeDoughtResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = typeDoughtService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Type dought not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<TypeDoughtResponse> createTypeDought(@Valid @RequestBody TypeDoughtRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        typeDoughtService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TypeDoughtResponse> updateTypeDought(@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody TypeDoughtRequest request) throws Exception{
        var entity = typeDoughtService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Type dought not found"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTypeDought(@Valid @PathVariable(name = "id") Long id) throws Exception{
        typeDoughtService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
