package com.bakery.bakery.security.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.security.dto.AuthRequest;
import com.bakery.bakery.security.dto.CustomerRequest;
import com.bakery.bakery.security.dto.CustomerResponse;
import com.bakery.bakery.security.service.BCryptService;
import com.bakery.bakery.security.service.CustomerService;
import com.bakery.bakery.security.util.CustomerConvert;
import com.bakery.bakery.util.JwtUtil;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerConvert convert;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptService cryptService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() throws Exception{
        var entities = customerService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CustomerResponse> authenticate(@Valid @RequestBody CustomerRequest request) throws Exception{
        var existUsername = customerService.getByUsername(request.getUsername()).orElse(null);
        var existEmail = customerService.getByEmail(request.getEmail()).orElse(null);
        var existNumber = customerService.getByNumber(request.getNumber()).orElse(null);

        if (existEmail != null) { throw new ResourceNotFoundException("The email exist"); }
        if (existUsername != null) { throw new ResourceNotFoundException("The username exist"); }
        if (existNumber != null) { throw new ResourceNotFoundException("The number exist"); }

        var entity = convert.convertToEntity(request);
        entity.setPassword(cryptService.encryptPassword(entity.getPassword()));
        customerService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);
    }


    @PostMapping("/authentication")
    public String authentication(@Valid @RequestBody AuthRequest jwtRequest) throws Exception{
        var customer = customerService.getByUsername(jwtRequest.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));
        var validPassword = cryptService.verifyPassword(jwtRequest.getPassword(), customer.getPassword());

        if(!validPassword){
           throw new ResourceNotFoundException("Invalid Credentials") ;
        }
        
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        var userDetails = new User(customer.getUsername(), customer.getPassword(), authorities);
        String token = jwtUtil.generateToken(userDetails);
        return token;
    }

}
