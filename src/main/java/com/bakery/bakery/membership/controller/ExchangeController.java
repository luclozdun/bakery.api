package com.bakery.bakery.membership.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.membership.dto.ExchangeRequest;
import com.bakery.bakery.membership.dto.ExchangeResponse;
import com.bakery.bakery.membership.service.ExchangeService;
import com.bakery.bakery.membership.util.ExchangeConvert;

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
@RequestMapping("/exchanges")
public class ExchangeController {
    
    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private ExchangeConvert convert;

    @GetMapping
    private ResponseEntity<List<ExchangeResponse>> getAll() throws Exception{
        var entities = exchangeService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ExchangeResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = exchangeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Exchange not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ExchangeResponse> createExchange(@Valid @RequestBody ExchangeRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        exchangeService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ExchangeResponse> update(@Valid @RequestBody ExchangeRequest request, @Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = exchangeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Exchange not found"));
        entity.setName(request.getName());
        entity.setSymbol(request.getSymbol());
        exchangeService.update(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@Valid @PathVariable(name = "id") Long id) throws Exception{
        exchangeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
