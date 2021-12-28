package com.bakery.bakery.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.cake.service.CakeService;
import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.order.dto.OrderCakeResponse;
import com.bakery.bakery.order.dto.OrderPieResponse;
import com.bakery.bakery.order.dto.OrderRequest;
import com.bakery.bakery.order.dto.OrderResponse;
import com.bakery.bakery.order.model.Order;
import com.bakery.bakery.order.model.OrderCake;
import com.bakery.bakery.order.model.OrderPie;
import com.bakery.bakery.order.service.OrderCakeService;
import com.bakery.bakery.order.service.OrderPieService;
import com.bakery.bakery.order.service.OrderService;
import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.pie.service.PieService;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.service.CustomerService;

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

    @Autowired
    private CakeService cakeService;

    @Autowired
    private BakerService bakerService;

    @Autowired
    private PieService pieService;

    @Autowired
    private OrderCakeService orderCakeService;

    @Autowired
    private OrderPieService orderPieService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    private ResponseEntity<List<OrderResponse>> getAll() throws Exception{
        var entities = orderService.getAll();
        var response = entities.stream().map(entity -> mapper.map(entity, OrderResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/asd")
    private ResponseEntity<?> nose(@Valid @RequestBody OrderRequest request) throws Exception{
        var baker = bakerService.getById(request.getBakerId()).orElseThrow(() -> new ResourceNotFoundException("Baker not found"));
        var customer = customerService.getById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        var listcakesBaker = baker.getCakes();
        var listpiesBaker = baker.getPies();
        Map<Long, Cake> dicCake = new HashMap<Long, Cake>();
        Map<Long, Pie> dicPie = new HashMap<Long, Pie>();

        //  Initialize dictionary for each Product

        listcakesBaker.forEach(cake -> {
            dicCake.put(cake.getId(), cake);
        });

        listpiesBaker.forEach(pie -> {
            dicPie.put(pie.getId(), pie);
        });

        //  Create List OrderProducts

        List<OrderCake> orderCakes = new ArrayList<OrderCake>();
        List<OrderPie> orderPies = new ArrayList<OrderPie>();
        
        //  Create Order

        Order order = new Order();

        //  Valid request and verify if the quantify exist in stock
        
        request.getCakeId().forEach(cakeFilterClass -> {
            var cake = dicCake.get(cakeFilterClass.getId());
            if(cake == null){ throw new ResourceNotFoundException("Cake not found"); }

            Long validQuantify = cake.getQuantify() - cakeFilterClass.getQuantify();
            if(validQuantify < 0) { throw new ResourceNotFoundException("Quantify Cake not available"); }
        });

        request.getPiesId().forEach(pieFilterClass -> {
            var pie = dicPie.get(pieFilterClass.getId());
            if(pie == null){ throw new ResourceNotFoundException("Pie not found"); }

            Long validQuantify = pie.getQuantify() - pieFilterClass.getQuantify();
            if(validQuantify < 0) { throw new ResourceNotFoundException("Quantify Pie not available"); } 
        });

        //  If that's ok. Start a order an associate you data in OrderProducts
        order.setBaker(baker);
        order.setCustomer(customer);
        order.setAdmCost(request.getAdmCost());
        order.setDelivery(request.getDelivery());
        order.setTotal(request.getTotal());
        orderService.create(order);

        //  Verify

        request.getCakeId().forEach(cakeFilterClass -> {
            OrderCake orderCake = new OrderCake();
            var cake = dicCake.get(cakeFilterClass.getId());
            
            cake.setQuantify(cake.getQuantify()-cakeFilterClass.getQuantify());

            try {
                cakeService.update(cake);
            } catch (Exception e) {
                throw new ResourceNotFoundException("Error ocurred while updated OrderCake");
            }

            orderCake.setCake(cake);
            orderCake.setOrder(order);
            orderCake.setQuantify(cakeFilterClass.getQuantify());
            orderCake.setPriceUnid(cake.getPrice());
            orderCakes.add(orderCake);
        });
        System.out.println("Esta bien hasta verify cake");

        request.getPiesId().forEach(pieFilterClass -> {
            OrderPie orderPie = new OrderPie();
            var pie = dicPie.get(pieFilterClass.getId());

            pie.setQuantify(pie.getQuantify() - pieFilterClass.getQuantify());

            try {
                pieService.update(pie);
            } catch (Exception e) {
                throw new ResourceNotFoundException("Error ocurred while updated OrderPie");
            }

            orderPie.setPie(pie);
            orderPie.setOrder(order);
            orderPie.setPrice(pie.getPrice());
            orderPie.setQuantify(pieFilterClass.getQuantify());
            orderPies.add(orderPie);
        });
        System.out.println("Esta bien hasta verify pie");
        System.out.println("-----------------------------------------------");
        System.out.println(orderPies);
        System.out.println("-----------------------------------------------");
        //  Save the OrderProducts

        //order.setTotal(250.0);
        orderCakeService.saveAll(orderCakes);
        orderPieService.saveAll(orderPies);

        var responseOrderCakes = orderCakes.stream().map(entity -> mapper.map(entity, OrderCakeResponse.class)).collect(Collectors.toList());
        var responseOrderPies = orderPies.stream().map(entity -> mapper.map(entity, OrderPieResponse.class)).collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();
        try{
            orderResponse.setOrderCakes(responseOrderCakes);
            orderResponse.setOrderPies(responseOrderPies);
        }catch(Exception e){
            throw new ResourceNotFoundException("Error while created order");
        }
        

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }


}
