package com.bakery.bakery.pie.controller;

import java.util.List;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.pie.dto.CoverPieRequest;
import com.bakery.bakery.pie.dto.CoverPieResponse;
import com.bakery.bakery.pie.service.CoverPieService;
import com.bakery.bakery.pie.util.CoverPieConvert;

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
@RequestMapping("/coverpies")
public class CoverPieController {
    
    @Autowired
    private CoverPieService coverPieService;

    @Autowired
    private CoverPieConvert convert;

    @GetMapping
    private ResponseEntity<List<CoverPieResponse>> getAll() throws Exception{
        var coverPies = coverPieService.getAll();
        return new ResponseEntity<>(convert.convertToListResponse(coverPies), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CoverPieResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var coverPie = coverPieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("coverPie not found"));
        return new ResponseEntity<>(convert.convertToResponse(coverPie), HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<CoverPieResponse> createCoverPie(@RequestBody CoverPieRequest request) throws Exception{
        var coverPie = convert.convertToEntity(request);
        coverPieService.create(coverPie);
        return new ResponseEntity<>(convert.convertToResponse(coverPie), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CoverPieResponse> updateCoverPie(@Valid @PathVariable(name = "id") Long id, @RequestBody CoverPieRequest request) throws Exception{
        var coverPie = coverPieService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoverPie not found"));
        coverPie.setName(request.getName());
        coverPie.setDescription(request.getDescription());
        coverPieService.update(coverPie);
        return new ResponseEntity<>(convert.convertToResponse(coverPie), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteCoverPie(@Valid @PathVariable(name = "id") Long id) throws Exception{
        coverPieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
