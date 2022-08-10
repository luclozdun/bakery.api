package com.bakery.bakery.order.dto;

import java.util.List;
import java.util.Map;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.pie.model.Pie;

import lombok.Data;

@Data
public class PaymentDetail {
    private Double delivery;
    private Double admCost;
    private Long customerId;
    private Long bakerId;
    private List<Pie> pies;
    private List<Cake> cakes;
    private Double total;
    private Map<String, DictionaryProduct> dictionary;
}
