package com.bakery.bakery.security.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.membership.service.SubscriptionService;
import com.bakery.bakery.security.dto.CustomerResponse;
import com.bakery.bakery.security.dto.CustomerSubscriptionRequest;
import com.bakery.bakery.security.service.CustomerService;
import com.bakery.bakery.security.util.CustomerConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() throws Exception{
        var entities = customerService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities), HttpStatus.OK);
    }

    @PostMapping("/subscription")
    private ResponseEntity<CustomerResponse> associateCustomerSubscription(@Valid @RequestBody CustomerSubscriptionRequest request) throws Exception{
        var customer = customerService.getById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        var subscription = subscriptionService.getById(request.getSubscriptionId()).orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));

        customer.setSubscription(subscription);
        customerService.update(customer);
        return new ResponseEntity<>(convert.convertToResponse(customer), HttpStatus.OK);
    }
}
