package com.bakery.bakery.security.controller;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.security.dto.AuthRequest;
import com.bakery.bakery.security.service.BCryptService;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.service.CustomerService;
import com.bakery.bakery.security.service.OwnerService;
import com.bakery.bakery.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentications")
public class AuthenticationController {

    @Autowired
    private BakerService bakerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptService cryptService;

    @PostMapping("/baker")
    public ResponseEntity<String> authenticationBaker(@Valid @RequestBody AuthRequest authRequest)
            throws Exception {
        var baker = bakerService.getBakerByUsername(authRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Invalid Credentials"));
        var validPassword = cryptService.verifyPassword(authRequest.getPassword(), baker.getPassword());

        if (!validPassword) {
            throw new ResourceNotFoundExceptionRequest("Invalid Credentials");
        }

        var userDetails = bakerService.loadUserByUsername(baker.getUsername(), baker.getPassword());
        var token = jwtUtil.generateToken(userDetails, "BAKER", baker.getId());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<String> authenticationCustomer(@Valid @RequestBody AuthRequest authRequest)
            throws Exception {
        var customer = customerService.getByUsername(authRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Invalid Credentials"));
        var validPassword = cryptService.verifyPassword(authRequest.getPassword(), customer.getPassword());

        if (!validPassword) {
            throw new ResourceNotFoundExceptionRequest("Invalid Credentials");
        }

        var userDetails = customerService.loadUserByUsername(customer.getUsername(), customer.getPassword());
        var token = jwtUtil.generateToken(userDetails, "CUSTOMER", customer.getId());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/owner")
    public String authenticationOwner(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        var owner = ownerService.getByUsername(authRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Invalid Credentials"));
        var validPassword = cryptService.verifyPassword(authRequest.getPassword(), owner.getPassword());

        if (!validPassword) {
            throw new ResourceNotFoundExceptionRequest("Invalid Credentials");
        }

        var userDetails = ownerService.loadUserByUsername(owner.getUsername(), owner.getPassword());
        var token = jwtUtil.generateToken(userDetails, "OWNER", owner.getId());
        return token;
    }
}
