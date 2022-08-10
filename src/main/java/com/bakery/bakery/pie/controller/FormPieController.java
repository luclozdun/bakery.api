package com.bakery.bakery.pie.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.pie.dto.FormPieRequest;
import com.bakery.bakery.pie.dto.FormPieResponse;
import com.bakery.bakery.pie.service.FormPieService;
import com.bakery.bakery.pie.util.FormPieConvert;

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
@RequestMapping("/formpies")
public class FormPieController {
    
    @Autowired
    private FormPieService formPieService;

    @Autowired
    private FormPieConvert convert;

    @GetMapping
    private ResponseEntity<List<FormPieResponse>> getList() throws Exception{
        var formPies = formPieService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(formPies), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<FormPieResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var formPie = formPieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Form pie not found"));
        return new ResponseEntity<>(convert.convertToResponse(formPie), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<FormPieResponse> createFormPie(@Valid @RequestBody FormPieRequest request) throws Exception{
        var entity = convert.convertToEntity(request);
        formPieService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<FormPieResponse> updateFormPie(@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody FormPieRequest request) throws Exception{
        var formPie = formPieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Form pie not found"));
        formPie.setDescription(request.getDescription());
        formPie.setName(request.getName());
        formPieService.update(formPie);
        return new ResponseEntity<>(convert.convertToResponse(formPie), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFormPie(@Valid @PathVariable(name = "id") Long id) throws Exception{
        formPieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
