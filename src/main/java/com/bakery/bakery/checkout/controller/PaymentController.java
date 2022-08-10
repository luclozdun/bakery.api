package com.bakery.bakery.checkout.controller;

import com.bakery.bakery.checkout.dto.PaymentResponse;
import com.bakery.bakery.checkout.service.PaymentService;
import com.bakery.bakery.order.dto.OrderRequest;
import com.bakery.bakery.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody OrderRequest request) {
        var payment = orderService.paymentDetail(request);
        var response = paymentService.authorizePayment(payment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
