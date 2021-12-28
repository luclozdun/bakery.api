package com.bakery.bakery.membership.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.membership.dto.SubscriptionRequest;
import com.bakery.bakery.membership.dto.SubscriptionResponse;
import com.bakery.bakery.membership.model.Subscription;
import com.bakery.bakery.membership.service.ExchangeService;
import com.bakery.bakery.membership.service.SubscriptionService;
import com.bakery.bakery.membership.util.SubscriptionConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private SubscriptionConvert convert;

    @GetMapping
    private ResponseEntity<List<SubscriptionResponse>> getAll() throws Exception{
        var entities = subscriptionService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities) ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SubscriptionResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = subscriptionService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest request) throws Exception{
        Subscription subscription = new Subscription();
        var existExchange = exchangeService.getById(request.getExchangeId()).orElseThrow(() -> new ResourceNotFoundException("Exchange not found"));
        
        Date start = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);        
        int monthAdd = request.getMonth().intValue();
        calendar.add((Calendar.MONTH), monthAdd);
        Date finish = calendar.getTime();

        subscription.setMonth(request.getMonth());
        subscription.setExchange(existExchange);
        subscription.setDateExpirence(finish);
        subscription.setDateStart(start);
        subscription.setName(request.getName());
        subscription.setDescription(request.getDescription());

        subscriptionService.create(subscription);
        return new ResponseEntity<>(convert.convertToResponse(subscription), HttpStatus.OK);
    }
}
