package com.bakery.bakery.pie.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.pie.dto.PieRequest;
import com.bakery.bakery.pie.dto.PieResponse;
import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.pie.service.CoverPieService;
import com.bakery.bakery.pie.service.FormPieService;
import com.bakery.bakery.pie.service.PieService;
import com.bakery.bakery.pie.service.SizePieService;
import com.bakery.bakery.pie.service.TypeDoughtService;
import com.bakery.bakery.pie.service.TypePieService;
import com.bakery.bakery.pie.util.PieConvert;
import com.bakery.bakery.security.service.BakerService;

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
@RequestMapping("/pies")
public class PieController {
    
    @Autowired
    private PieService pieService;

    @Autowired
    private CoverPieService coverPieService;

    @Autowired
    private FormPieService formPieService;

    @Autowired
    private SizePieService sizePieService;

    @Autowired
    private TypeDoughtService typeDoughtService;

    @Autowired
    private TypePieService typePieService;

    @Autowired
    private BakerService bakerService;

    @Autowired
    private PieConvert convert;

    @GetMapping
    private ResponseEntity<List<PieResponse>> getAll() throws Exception{
        var entities = pieService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(entities), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PieResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = pieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Pie not found"));
        return new ResponseEntity<>(convert.convertToResponse(entity), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<PieResponse> createPie(@Valid @RequestBody PieRequest request) throws Exception{
        var existCoverPie = coverPieService.getById(request.getCoverPieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cover Pie not found"));
        var existFormPie = formPieService.getById(request.getFormPieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Form Pie not found"));
        var existSizePie = sizePieService.getById(request.getSizePieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Size Pie not found"));
        var existTypeDought = typeDoughtService.getById(request.getTypeDoughtId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("TypeDought Pie not found"));
        var existTypePie = typePieService.getById(request.getTypePieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("TypePie Pie not found"));
        var existBaker = bakerService.getById(request.getBakerId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Baker Pie not found"));

        var entity = new Pie();
        entity.setCoverPie(existCoverPie);
        entity.setFormPie(existFormPie);
        entity.setSizePie(existSizePie);
        entity.setTypeDought(existTypeDought);
        entity.setTypePie(existTypePie);
        entity.setBaker(existBaker);
        entity.setPrice(request.getPrice());
        entity.setQuantify(request.getQuantify());

        pieService.create(entity);
        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);        
    }

    @PutMapping("/{id}")
    private ResponseEntity<PieResponse> updatePie(@Valid @PathVariable(name = "id") Long id ,@Valid @RequestBody PieRequest request) throws Exception{
        var entity = pieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Pie not found"));
        var existCoverPie = coverPieService.getById(request.getCoverPieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cover Pie not found"));
        var existFormPie = formPieService.getById(request.getFormPieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Form Pie not found"));
        var existSizePie = sizePieService.getById(request.getSizePieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Size Pie not found"));
        var existTypeDought = typeDoughtService.getById(request.getTypeDoughtId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("TypeDought Pie not found"));
        var existTypePie = typePieService.getById(request.getTypePieId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("TypePie Pie not found"));
        var existBaker = bakerService.getById(request.getBakerId()).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Baker Pie not found"));

        entity.setCoverPie(existCoverPie);
        entity.setFormPie(existFormPie);
        entity.setSizePie(existSizePie);
        entity.setTypeDought(existTypeDought);
        entity.setTypePie(existTypePie);
        entity.setBaker(existBaker);
        entity.setPrice(request.getPrice());
        entity.setQuantify(request.getQuantify());

        pieService.update(entity);

        return new ResponseEntity<>(convert.convertToResponse(entity) ,HttpStatus.OK);        
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deletePie(@Valid @PathVariable(name = "id") Long id) throws Exception{
        pieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
