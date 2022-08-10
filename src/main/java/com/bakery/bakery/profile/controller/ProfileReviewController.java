package com.bakery.bakery.profile.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.profile.dto.ProfileResult;
import com.bakery.bakery.profile.dto.ProfileReviewRequest;
import com.bakery.bakery.profile.dto.ProfileReviewResponse;
import com.bakery.bakery.profile.model.ProfileReview;
import com.bakery.bakery.profile.service.ProfileReviewService;
import com.bakery.bakery.security.dto.BakerResponse;
import com.bakery.bakery.security.dto.CustomerResponse;
import com.bakery.bakery.security.model.Baker;
import com.bakery.bakery.security.service.BCryptService;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.service.CustomerService;

import org.modelmapper.ModelMapper;
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
@RequestMapping("/profilereviews")
public class ProfileReviewController {

    @Autowired
    private ProfileReviewService profileReviewService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BakerService bakerService;

    @Autowired
    private BCryptService bCryptService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<List<ProfileReviewResponse>> getAll() throws Exception {
        var entities = profileReviewService.getAllByProcessId(1L);
        var response = entities.stream().map(entity -> mapper.map(entity, ProfileReviewResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ProfileReviewResponse> createProfileReview(@Valid @RequestBody ProfileReviewRequest request)
            throws Exception {
        var customer = customerService.getById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Customer not exist"));
        if (customer.getProfileReview() != null) {
            throw new ResourceNotFoundExceptionRequest("Customer have a profile");
        }

        ProfileReview profileReview = new ProfileReview();
        profileReview.setCustomer(customer);
        profileReview.setDni(request.getDni());
        profileReview.setDocDNI(request.getDocDNI());
        profileReview.setDocRUC(request.getDocRUC());
        profileReview.setDocSanitation(request.getDocSanitation());
        profileReview.setLicense(request.getLicense());
        profileReview.setPermMunicipa(request.getPermMunicipa());
        profileReview.setRuc(request.getRuc());
        profileReview.setCost(request.getCost());
        profileReview.setLocation(request.getLocation());
        profileReview.setNameBakery(request.getNameBakery());
        profileReview.setProcess(1L);

        profileReviewService.create(profileReview);

        var response = mapper.map(profileReview, ProfileReviewResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteProfileReview(@Valid @PathVariable(name = "id") Long id)
            throws Exception {
        profileReviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProfileReviewResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception {
        var profile = profileReviewService.getByCustomerId(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Customer profile not found"));
        var response = mapper.map(profile, ProfileReviewResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/action/{id}")
    private ResponseEntity<ProfileReviewResponse> updateProcess(@Valid @PathVariable(name = "id") Long id,
            @Valid @RequestBody ProfileResult profileResult) throws Exception {
        var profileReview = profileReviewService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Profile not found"));

        // 2 -> Aceptado
        if (profileResult.getProcess() == 2L) {
            System.out.print("2L");
            var customer = customerService.getById(profileReview.getCustomer().getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Customer not foud"));

            profileReview.setProcess(2L);
            profileReview.setBakerPassword("123456");
            profileReview.setBakerUsername(customer.getUsername());

            profileReviewService.update(profileReview);

            Baker baker = new Baker();
            baker.setBrithday(customer.getBrithday());
            baker.setDni(profileReview.getDni());
            baker.setDocDNI(profileReview.getDocDNI());
            baker.setDocRUC(profileReview.getDocRUC());
            baker.setDocSanitation(profileReview.getDocSanitation());
            baker.setEmail(customer.getEmail());
            baker.setImage(customer.getImage());
            baker.setLicense(profileReview.getLicense());
            baker.setName(customer.getName());
            baker.setNumber(customer.getNumber());
            baker.setPassword(bCryptService.encryptPassword("123456"));
            baker.setPermMunicipa(profileReview.getPermMunicipa());
            baker.setRuc(profileReview.getRuc());
            baker.setUsername(customer.getUsername());
            baker.setLocation(profileReview.getLocation());
            baker.setCost(profileReview.getCost());
            baker.setNameBakery(profileReview.getNameBakery());

            bakerService.create(baker);
            // 3 -> Denegado
        } else if (profileResult.getProcess() == 3L) {
            System.out.print("3L");
            profileReview.setProcess(3L);
            profileReviewService.update(profileReview);
        } else {
            System.out.print("Nada");
            throw new ResourceNotFoundExceptionRequest("Invalid process");
        }

        var response = mapper.map(profileReview, ProfileReviewResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/action/asd/{id}")
    private ResponseEntity<CustomerResponse> getByIdCustomer(@Valid @PathVariable(name = "id") Long id)
            throws Exception {
        var entity = customerService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Customer not found"));
        var response = mapper.map(entity, CustomerResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bakers")
    private ResponseEntity<List<BakerResponse>> getallBakers() throws Exception {
        var entities = bakerService.getAll();
        var response = entities.stream().map(entity -> mapper.map(entity, BakerResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
