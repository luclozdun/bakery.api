package com.bakery.bakery.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.cake.repository.CakeRepository;
import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.order.dto.BakerSimpleResponse;
import com.bakery.bakery.order.dto.CustomerSimpleResponse;
import com.bakery.bakery.order.dto.DictionaryProduct;
import com.bakery.bakery.order.dto.ItemDescription;
import com.bakery.bakery.order.dto.ItemDetail;
import com.bakery.bakery.order.dto.ItemType;
import com.bakery.bakery.order.dto.OrderProductId;
import com.bakery.bakery.order.dto.OrderRequest;
import com.bakery.bakery.order.dto.PaymentDetail;
import com.bakery.bakery.order.model.Order;
import com.bakery.bakery.order.model.OrderCake;
import com.bakery.bakery.order.model.OrderPie;
import com.bakery.bakery.order.repository.OrderCakeRepository;
import com.bakery.bakery.order.repository.OrderPieRepository;
import com.bakery.bakery.order.repository.OrderRepository;
import com.bakery.bakery.order.service.OrderService;
import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.pie.repository.PieRepository;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Baker;
import com.bakery.bakery.security.model.Customer;
import com.bakery.bakery.security.repository.BakerRepository;
import com.bakery.bakery.security.repository.CustomerRepository;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends CrudServiceImpl<Order, Long> implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CakeRepository cakeRepository;

    @Autowired
    private PieRepository pieRepository;

    @Autowired
    private BakerRepository bakerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderCakeRepository orderCakeRepository;

    @Autowired
    private OrderPieRepository orderPieRepository;

    @Override
    protected GenericRepository<Order, Long> repository() {
        return orderRepository;
    }

    public Map<String, DictionaryProduct> createDictionary(OrderRequest request) {

        Map<String, DictionaryProduct> dictionary = new HashMap<String, DictionaryProduct>();

        ArrayList<Long> idsCake = new ArrayList<Long>();
        ArrayList<Long> idsPie = new ArrayList<Long>();

        Map<Long, Long> dictionaryCake = new HashMap<Long, Long>();
        Map<Long, Long> dictionaryPie = new HashMap<Long, Long>();

        for (OrderProductId product : request.getCakeId()) {
            idsCake.add(product.getId());
            dictionaryCake.put(product.getId(), product.getQuantify());
        }
        for (OrderProductId product : request.getPiesId()) {
            idsPie.add(product.getId());
            dictionaryPie.put(product.getId(), product.getQuantify());
        }

        dictionary.put("cake", new DictionaryProduct(dictionaryCake, idsCake));
        dictionary.put("pie", new DictionaryProduct(dictionaryPie, idsPie));
        return dictionary;
    }

    public PaymentDetail valid(OrderRequest request, Map<String, DictionaryProduct> dictionary) {
        ArrayList<Long> idsCake = dictionary.get("cake").getIds();
        ArrayList<Long> idsPie = dictionary.get("pie").getIds();

        Map<Long, Long> dictionaryCake = dictionary.get("cake").getDictionary();
        Map<Long, Long> dictionaryPie = dictionary.get("cake").getDictionary();

        List<Cake> cakes = cakeRepository.findByIdIn(idsCake);
        List<Pie> pies = pieRepository.findByIdIn(idsPie);

        PaymentDetail product = new PaymentDetail();
        product.setCakes(cakes);
        product.setPies(pies);

        Double total = 0.0;

        for (Cake cake : cakes) {
            var quantify = cake.getQuantify() - dictionaryCake.get(cake.getId());
            if (quantify < 0) {
                return null;
            }
            if (cake.getBaker().getId() != request.getBakerId()) {
                throw new ResourceNotFoundExceptionRequest(
                        "The product is invalid. An error ocurred while valided baker id");
            }

            total = total + (dictionaryCake.get(cake.getId()) * cake.getPrice());
        }

        for (Pie pie : pies) {
            var quantify = pie.getQuantify() - dictionaryPie.get(pie.getId());
            if (quantify < 0) {
                return null;
            }
            if (pie.getBaker().getId() != request.getBakerId()) {
                throw new ResourceNotFoundExceptionRequest(
                        "The product is invalid. An error ocurred while valided baker id");
            }
            total = total + (dictionaryPie.get(pie.getId()) * pie.getPrice());
        }

        product.setTotal(total);
        return product;
    }

    @Override
    public boolean validStock(OrderRequest request) {

        Map<String, DictionaryProduct> dictionary = createDictionary(request);
        PaymentDetail dictionaryProduct = valid(request, dictionary);

        if (dictionaryProduct == null) {
            return false;
        }
        return true;
    }

    @Override
    public ItemDetail createOrder(OrderRequest request) {

        Map<String, DictionaryProduct> dictionary = createDictionary(request);

        DictionaryProduct dictionaryCake = dictionary.get("cake");
        DictionaryProduct dictionaryPie = dictionary.get("pie");

        PaymentDetail product = valid(request, dictionary);

        if (product == null) {
            throw new ResourceNotFoundExceptionRequest("Any product doens't have stock");
        }

        Baker baker = bakerRepository.getById(request.getBakerId());

        if (baker == null) {
            throw new ResourceNotFoundExceptionRequest("Baker not found by id");
        }

        Customer customer = customerRepository.getById(request.getCustomerId());

        if (customer == null) {
            throw new ResourceNotFoundExceptionRequest("customer not found by id");
        }

        Order order = new Order();
        order.setAdmCost(request.getAdmCost());
        order.setBaker(baker);
        order.setCustomer(customer);
        order.setDelivery(request.getDelivery());

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving order");
        }

        List<ItemDescription> items = new ArrayList<ItemDescription>();

        List<Cake> cakes = product.getCakes();
        List<Pie> pies = product.getPies();

        for (Cake cake : cakes) {
            OrderCake orderCake = new OrderCake();
            orderCake.setCake(cake);
            orderCake.setOrder(order);
            orderCake.setPriceUnid(cake.getPrice());
            orderCake.setQuantify(dictionaryCake.getDictionary().get(cake.getId()));

            cake.setQuantify(cake.getQuantify() - dictionaryCake.getDictionary().get(cake.getId()));

            try {
                cakeRepository.save(cake);
            } catch (Exception e) {
                throw new ResourceNotFoundExceptionRequest("Error ocurred while update cake");
            }

            ItemDescription item = new ItemDescription();
            item.setId(cake.getId());
            item.setType(ItemType.CAKE);
            item.setName(cake.getTypeCake().getName());
            item.setPrice(cake.getPrice());
            item.setQuantity(dictionaryCake.getDictionary().get(cake.getId()));

            items.add(item);

            orderCakeRepository.save(orderCake);
        }

        for (Pie pie : pies) {
            OrderPie orderPie = new OrderPie();
            orderPie.setOrder(order);
            orderPie.setPie(pie);
            orderPie.setPrice(pie.getPrice());
            orderPie.setQuantify(dictionaryPie.getDictionary().get(pie.getId()));

            pie.setQuantify(pie.getQuantify() - dictionaryPie.getDictionary().get(pie.getId()));

            try {
                pieRepository.save(pie);
            } catch (Exception e) {
                throw new ResourceNotFoundExceptionRequest("Error ocurred while update pie");
            }

            ItemDescription item = new ItemDescription();
            item.setId(pie.getId());
            item.setType(ItemType.PIE);
            item.setName(pie.getTypePie().getName());
            item.setPrice(pie.getPrice());
            item.setQuantity(dictionaryCake.getDictionary().get(pie.getId()));

            items.add(item);

            orderPieRepository.save(orderPie);
        }

        order.setTotal(product.getTotal());

        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating order");
        }

        BakerSimpleResponse bakerResponse = new BakerSimpleResponse();
        bakerResponse.setBakeryName(baker.getNameBakery());
        bakerResponse.setName(baker.getName());
        bakerResponse.setRuc(baker.getRuc());

        CustomerSimpleResponse customerResponse = new CustomerSimpleResponse();
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setName(customer.getName());
        customerResponse.setNumber(customer.getNumber());

        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setItems(items);
        itemDetail.setAdmCost(request.getAdmCost());
        itemDetail.setBaker(bakerResponse);
        itemDetail.setCustomer(customerResponse);
        itemDetail.setDelivery(request.getDelivery());

        return itemDetail;
    }

    @Override
    public PaymentDetail paymentDetail(OrderRequest request) {
        Map<String, DictionaryProduct> dictionary = createDictionary(request);
        PaymentDetail payment = valid(request, dictionary);
        payment.setDictionary(dictionary);
        payment.setAdmCost(request.getAdmCost());
        payment.setBakerId(request.getBakerId());
        payment.setCustomerId(request.getCustomerId());
        payment.setDelivery(request.getDelivery());
        return payment;
    }

}
