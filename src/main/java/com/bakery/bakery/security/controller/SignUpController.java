package com.bakery.bakery.security.controller;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.security.dto.BakerRequest;
import com.bakery.bakery.security.dto.BakerResponse;
import com.bakery.bakery.security.dto.CustomerRequest;
import com.bakery.bakery.security.dto.CustomerResponse;
import com.bakery.bakery.security.dto.OwnerRequest;
import com.bakery.bakery.security.dto.OwnerResponse;
import com.bakery.bakery.security.service.BCryptService;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.service.CustomerService;
import com.bakery.bakery.security.service.OwnerService;
import com.bakery.bakery.security.util.BakerConvert;
import com.bakery.bakery.security.util.CustomerConvert;
import com.bakery.bakery.security.util.OwnerConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signups")
public class SignUpController {
    
    @Autowired
    private BakerService bakerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private BCryptService cryptService;

    @Autowired
    private BakerConvert bakerConvert;

    @Autowired
    private CustomerConvert customerConvert;

    @Autowired
    private OwnerConvert ownerConvert;

    @PostMapping("/baker")
    private ResponseEntity<BakerResponse> createBaker(@Valid @RequestBody BakerRequest request) throws Exception{
        var existUsername = bakerService.getBakerByUsername(request.getUsername()).orElse(null);
        var existEmail = bakerService.getBakerByEmail(request.getEmail()).orElse(null);
        var existNumber = bakerService.getBakerByNumber(request.getNumber()).orElse(null);
        var existDNI = bakerService.getBakerByDni(request.getDni()).orElse(null);
        var existRUC = bakerService.getBakerByRuc(request.getRuc()).orElse(null);

        if (existEmail != null) { throw new ResourceNotFoundException("Email exist"); }
        if (existUsername != null) { throw new ResourceNotFoundException("Username exist"); }
        if (existNumber != null) { throw new ResourceNotFoundException("Number exist"); }
        if (existDNI != null) { throw new ResourceNotFoundException("DNI exist"); }
        if (existRUC != null) { throw new ResourceNotFoundException("RUC exist"); }

        var entity = bakerConvert.convertToEntity(request);
        entity.setPassword(cryptService.encryptPassword(entity.getPassword()));
        //entity.setCakes();
        bakerService.create(entity);
        return new ResponseEntity<>(bakerConvert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping("/owner")
    private ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody OwnerRequest request) throws Exception{
        var existUsername = ownerService.getByUsername(request.getUsername()).orElse(null);
        var existEmail = ownerService.getByEmail(request.getEmail()).orElse(null);
        var existNumber = ownerService.getByNumber(request.getNumber()).orElse(null);

        if (existEmail != null) { throw new ResourceNotFoundException("Email exist"); }
        if (existUsername != null) { throw new ResourceNotFoundException("Username exist"); }
        if (existNumber != null) { throw new ResourceNotFoundException("Number exist"); }

        var entity = ownerConvert.convertToEntity(request);
        entity.setPassword(cryptService.encryptPassword(entity.getPassword()));
        ownerService.create(entity);
        return new ResponseEntity<>(ownerConvert.convertToResponse(entity) ,HttpStatus.OK);
    }

    @PostMapping("/customer")
    private ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) throws Exception{
        var existUsername = customerService.getByUsername(request.getUsername()).orElse(null);
        var existEmail = customerService.getByEmail(request.getEmail()).orElse(null);
        var existNumber = customerService.getByNumber(request.getNumber()).orElse(null);

        if (existEmail != null) { throw new ResourceNotFoundException("Email exist"); }
        if (existUsername != null) { throw new ResourceNotFoundException("Username exist"); }
        if (existNumber != null) { throw new ResourceNotFoundException("Number exist"); }

        var entity = customerConvert.convertToEntity(request);
        entity.setPassword(cryptService.encryptPassword(entity.getPassword()));
        customerService.create(entity);
        return new ResponseEntity<>(customerConvert.convertToResponse(entity), HttpStatus.OK);
    }
}