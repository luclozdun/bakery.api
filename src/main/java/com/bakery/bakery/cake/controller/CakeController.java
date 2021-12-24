package com.bakery.bakery.cake.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bakery.bakery.cake.dto.CakeRequest;
import com.bakery.bakery.cake.dto.CakeResponse;
import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.cake.service.CakeService;
import com.bakery.bakery.cake.service.CoverCakeService;
import com.bakery.bakery.cake.service.FillerCakeService;
import com.bakery.bakery.cake.service.SizeCakeService;
import com.bakery.bakery.cake.service.TasteCakeService;
import com.bakery.bakery.cake.service.TypeCakeService;
import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.security.service.BakerService;

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
@RequestMapping("/cakes")
public class CakeController {
    
    @Autowired
    private CakeService cakeService;

    @Autowired
    private CoverCakeService coverCakeService;

    @Autowired
    private SizeCakeService sizeCakeService;

    @Autowired
    private TasteCakeService tasteCakeService;

    @Autowired
    private TypeCakeService typeCakeService;

    @Autowired
    private FillerCakeService fillerCakeService;

    @Autowired
    private BakerService bakerService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    private ResponseEntity<?> getAll() throws Exception{
        var entities = cakeService.getAll();
        //var convert = entities.stream().map(entity -> mapper.map(entity, CakeResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createCake(@Valid @RequestBody CakeRequest request) throws Exception{        
        Cake cake = new Cake();
        var coverCake = coverCakeService.getById(request.getCovercakeId()).orElseThrow(() -> new ResourceNotFoundException("Cover cake not found"));
        var sizeCake = sizeCakeService.getById(request.getSizecakeId()).orElseThrow(() -> new ResourceNotFoundException("Size cake not found"));
        var tasteCake = tasteCakeService.getById(request.getTastecakeId()).orElseThrow(() -> new ResourceNotFoundException("Taste cake not found"));
        var typeCake = typeCakeService.getById(request.getTypecakeId()).orElseThrow(() -> new ResourceNotFoundException("Type cake not found"));
        var baker = bakerService.getById(request.getBakerId()).orElseThrow(() -> new ResourceNotFoundException("Baker not found"));
        var fillers = request.getFillerCakeIds().stream().map(filler -> {
            try{
                return fillerCakeService.getById(filler.getFillerId()).orElseThrow(() -> new ResourceNotFoundException("Filler not found"));
            }
            catch( Exception e){
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        cake.setTypeCake(typeCake);
        cake.setSizeCake(sizeCake);
        cake.setCoverCake(coverCake);
        cake.setTasteCake(tasteCake);
        cake.setPrice(request.getPrice());
        cake.setQuantify(request.getQuantify());
        cake.setFillerCakes(fillers);
        cake.setBaker(baker);

        cakeService.create(cake);
        
        var response = mapper.map(cake, CakeResponse.class);
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }
}
