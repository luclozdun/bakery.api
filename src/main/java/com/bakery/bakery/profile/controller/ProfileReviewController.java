package com.bakery.bakery.profile.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.profile.dto.ProfileResult;
import com.bakery.bakery.profile.dto.ProfileReviewRequest;
import com.bakery.bakery.profile.dto.ProfileReviewResponse;
import com.bakery.bakery.profile.model.ProfileReview;
import com.bakery.bakery.profile.service.ProfileReviewService;
import com.bakery.bakery.security.dto.CustomerResponse;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.service.CustomerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profilereviews")
public class ProfileReviewController {
    
    @Autowired
    private ProfileReviewService profileReviewService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BakerService bakerService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<ProfileReviewResponse>> getAll() throws Exception{
        var entities = profileReviewService.getAll();
        var response = entities.stream().map(entity -> mapper.map(entity, ProfileReviewResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ProfileReviewResponse> createProfileReview(@Valid @RequestBody ProfileReviewRequest request) throws Exception{
        var customer = customerService.getById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not exist"));

        ProfileReview profileReview = new ProfileReview();
        profileReview.setCustomer(customer);
        profileReview.setDni(request.getDni());
        profileReview.setDocDNI(request.getDocDNI());
        profileReview.setDocRUC(request.getDocRUC());
        profileReview.setDocSanitation(request.getDocSanitation());
        profileReview.setLicense(request.getLicense());
        profileReview.setPermMunicipa(request.getPermMunicipa());
        profileReview.setRuc(request.getRuc());
        profileReview.setProcess(1L);

        profileReviewService.create(profileReview);

        var response = mapper.map(profileReview, ProfileReviewResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/action/{id}")
    private ResponseEntity<ProfileReviewResponse> denyProfileReview(@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody ProfileResult profileResult) throws Exception{
        var profileReview = profileReviewService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Profile not exist"));
        profileReview.setMessage(profileResult.getMessage());
        profileReview.setProcess(profileResult.getProcess());
        profileReview.setBakerPassword(profileResult.getBakerPassword());
        profileReview.setBakerUsername(profileResult.getBakerUsername());
        profileReviewService.update(profileReview);
        var response = mapper.map(profileReview, ProfileReviewResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/action/asd/{id}")
    private ResponseEntity<CustomerResponse> getByIdCustomer(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var entity = customerService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        var response = mapper.map(entity, CustomerResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/action/{username}")
    private ResponseEntity<Boolean> validBakerUsername(@Valid @PathVariable(name = "username") String username) throws Exception{
        var validUsername = bakerService.getBakerByUsername(username).orElse(null);
        Boolean valid;
        if(validUsername == null) { valid = true; }
        else { valid = false; }
        return new ResponseEntity<>(valid ,HttpStatus.OK);
    }
}
