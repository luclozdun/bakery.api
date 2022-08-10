package com.bakery.bakery.order.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bakery.bakery.order.dto.ItemDetail;
import com.bakery.bakery.order.dto.OrderRequest;
import com.bakery.bakery.order.dto.OrderResponse;
import com.bakery.bakery.order.service.OrderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<OrderResponse>> getAll() throws Exception {
        var entities = orderService.getAll();
        var response = entities.stream().map(entity -> mapper.map(entity, OrderResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ItemDetail> create(@Valid @RequestBody OrderRequest request) throws Exception {
        var response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/valid")
    public ResponseEntity<Boolean> valid(@Valid @RequestBody OrderRequest request) throws Exception {
        var response = orderService.validStock(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
