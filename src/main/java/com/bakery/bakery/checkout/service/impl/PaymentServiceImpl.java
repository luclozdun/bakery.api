package com.bakery.bakery.checkout.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.checkout.dto.Amount;
import com.bakery.bakery.checkout.dto.Breakdown;
import com.bakery.bakery.checkout.dto.Item;
import com.bakery.bakery.checkout.dto.PaymentResponse;
import com.bakery.bakery.checkout.dto.PurchaseUnits;
import com.bakery.bakery.checkout.dto.ValueCurrency;
import com.bakery.bakery.checkout.service.PaymentService;
import com.bakery.bakery.exception.ResourceNotFoundExceptionRequest;
import com.bakery.bakery.order.dto.PaymentDetail;
import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.security.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PaymentResponse authorizePayment(PaymentDetail request) {
        try {
            PaymentDetail product = request;
            if (product == null) {
                throw new ResourceNotFoundExceptionRequest("Error ocurred while request to order controller");
            }

            var customer = customerRepository.getById(request.getCustomerId());
            if (customer == null) {
                throw new ResourceNotFoundExceptionRequest("Customer not found");
            }
            PurchaseUnits purchase = createPaypalDetail(request);

            PaymentResponse response = new PaymentResponse();

            List<PurchaseUnits> units = new ArrayList<PurchaseUnits>();
            units.add(purchase);

            response.setPurchase_units(units);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while authorize payment");
        }
    }

    public String convertString(Double value) {
        String string = String.format("%.02f", value).replace(",", ".");
        return string;
    }

    public PurchaseUnits createPaypalDetail(PaymentDetail payment) {
        PurchaseUnits purchase = new PurchaseUnits();

        String total = convertString(payment.getAdmCost() + payment.getDelivery() + payment.getTotal());

        Amount amount = new Amount();
        amount.setValue(total);
        amount.setCurrency_code("USD");
        amount.setBreakdown(new Breakdown(new ValueCurrency(total, "USD")));

        List<Cake> cakes = payment.getCakes();
        List<Pie> pies = payment.getPies();

        List<Item> items = new ArrayList<Item>();

        for (Cake cake : cakes) {
            String price = convertString(cake.getPrice());
            String quantify = payment.getDictionary().get("cake").getDictionary().get(cake.getId()).toString();

            Item item = new Item();
            item.setName(cake.getTypeCake().getName());
            item.setQuantity(quantify);
            item.setUnit_amount(new ValueCurrency(price, "USD"));

            items.add(item);
        }

        for (Pie pie : pies) {
            String price = convertString(pie.getPrice());
            String quantify = payment.getDictionary().get("pie").getDictionary().get(pie.getId()).toString();

            Item item = new Item();
            item.setName(pie.getTypePie().getName());
            item.setQuantity(quantify);
            item.setUnit_amount(new ValueCurrency(price, "USD"));

            items.add(item);
        }

        String admCost = convertString(payment.getAdmCost());
        String delivery = convertString(payment.getDelivery());

        Item item = new Item();
        item.setName("Delivery");
        item.setQuantity("1");
        item.setUnit_amount(new ValueCurrency(delivery, "USD"));
        items.add(item);

        item = new Item();
        item.setName("Costo Administrativo");
        item.setQuantity("1");
        item.setUnit_amount(new ValueCurrency(admCost, "USD"));
        items.add(item);

        purchase.setAmount(amount);
        purchase.setDescription("Keekooya productos de pasteleria");
        purchase.setItems(items);

        return purchase;
    }
}
