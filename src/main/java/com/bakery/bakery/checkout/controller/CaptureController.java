package com.bakery.bakery.checkout.controller;

import java.util.Date;

import com.bakery.bakery.checkout.dto.CaptureStatus;
import com.bakery.bakery.checkout.model.Capture;
import com.bakery.bakery.checkout.service.CaptureService;
import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captures")
public class CaptureController {

    @Autowired
    private CaptureService captureService;

    @GetMapping("/paymentid/{id}")
    private ResponseEntity<Capture> getById(@PathVariable(name = "id") String id) throws Exception {
        var response = captureService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Checkout not found"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/canceled")
    private ResponseEntity<Capture> canceled(@RequestParam(name = "token") String token) throws Exception {
        Capture entity = new Capture();
        entity.setCreateAt(new Date());
        entity.setPayerId("");
        entity.setPaymentId("");
        entity.setStatus(CaptureStatus.CANCELED);
        entity.setToken(token);

        var response = captureService.create(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/created")
    private ResponseEntity<Capture> created(@RequestParam(name = "paymentId") String paymentId,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "PayerID") String payerId) throws Exception {
        Capture entity = new Capture();
        entity.setCreateAt(new Date());
        entity.setPayerId(payerId);
        entity.setPaymentId(paymentId);
        entity.setStatus(CaptureStatus.CREATED);
        entity.setToken(token);

        var response = captureService.create(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/approved")
    private ResponseEntity<Capture> approved(@RequestParam(name = "paymentId") String paymentId,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "PayerID") String payerId) throws Exception {
        Capture entity = new Capture();
        entity.setCreateAt(new Date());
        entity.setPayerId(payerId);
        entity.setPaymentId(paymentId);
        entity.setToken(token);
        entity.setStatus(CaptureStatus.APPROVED);

        var response = captureService.create(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
