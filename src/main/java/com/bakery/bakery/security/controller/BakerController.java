package com.bakery.bakery.security.controller;

import java.util.List;

import com.bakery.bakery.security.dto.BakerResponse;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.util.BakerConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bakers")
public class BakerController {
    
    @Autowired
    private BakerService bakerService;

    @Autowired
    private BakerConvert convert;

    @GetMapping
    private ResponseEntity<List<BakerResponse>> getAll() throws Exception{
        var entities = bakerService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }
}
