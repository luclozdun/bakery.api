package com.bakery.bakery.cake.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.security.service.BakerService;

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
        private ResponseEntity<List<CakeResponse>> getAll() throws Exception {
                var entities = cakeService.getAll();
                var response = entities.stream().map(entity -> mapper.map(entity, CakeResponse.class))
                                .collect(Collectors.toList());
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        private ResponseEntity<List<CakeResponse>> getAllByBakerId(@Valid @PathVariable(name = "id") Long id)
                        throws Exception {
                var entities = cakeService.getAllByBakerId(id);
                var response = entities.stream().map(entity -> mapper.map(entity, CakeResponse.class))
                                .collect(Collectors.toList());
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PostMapping("/list")
        private ResponseEntity<List<CakeResponse>> getAllByBakerId(@RequestBody ArrayList<Long> ids)
                        throws Exception {
                var entities = cakeService.getAllByIds(ids);
                var response = entities.stream().map(entity -> mapper.map(entity, CakeResponse.class))
                                .collect(Collectors.toList());
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PostMapping
        private ResponseEntity<CakeResponse> createCake(@Valid @RequestBody CakeRequest request) throws Exception {
                Cake cake = new Cake();
                var coverCake = coverCakeService.getById(request.getCovercakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cover cake not found"));
                var sizeCake = sizeCakeService.getById(request.getSizecakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Size cake not found"));
                var tasteCake = tasteCakeService.getById(request.getTastecakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Taste cake not found"));
                var typeCake = typeCakeService.getById(request.getTypecakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Type cake not found"));
                var baker = bakerService.getById(request.getBakerId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Baker not found"));
                var fillers = request.getFillerCakeIds().stream().map(filler -> {
                        try {
                                return fillerCakeService.getById(filler)
                                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Filler not found"));
                        } catch (Exception e) {
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
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        private ResponseEntity<?> deleteCake(@Valid @PathVariable(name = "id") Long id) throws Exception {
                cakeService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        @PutMapping("/{id}")
        private ResponseEntity<CakeResponse> updateCake(@Valid @PathVariable(name = "id") Long id,
                        @Valid @RequestBody CakeRequest request) throws Exception {
                var entity = cakeService.getById(id).orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cake not found"));
                var coverCake = coverCakeService.getById(request.getCovercakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Cover cake not found"));
                var sizeCake = sizeCakeService.getById(request.getSizecakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Size cake not found"));
                var tasteCake = tasteCakeService.getById(request.getTastecakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Taste cake not found"));
                var typeCake = typeCakeService.getById(request.getTypecakeId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Type cake not found"));
                var baker = bakerService.getById(request.getBakerId())
                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Baker not found"));
                var fillers = request.getFillerCakeIds().stream().map(filler -> {
                        try {
                                return fillerCakeService.getById(filler)
                                                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Filler not found"));
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        return null;
                }).collect(Collectors.toList());

                entity.setTypeCake(typeCake);
                entity.setSizeCake(sizeCake);
                entity.setCoverCake(coverCake);
                entity.setTasteCake(tasteCake);
                entity.setPrice(request.getPrice());
                entity.setQuantify(request.getQuantify());
                entity.setFillerCakes(fillers);
                entity.setBaker(baker);

                cakeService.update(entity);
                var response = mapper.map(entity, CakeResponse.class);
                return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
