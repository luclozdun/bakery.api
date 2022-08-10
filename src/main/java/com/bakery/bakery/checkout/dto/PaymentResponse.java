package com.bakery.bakery.checkout.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentResponse {
    List<PurchaseUnits> purchase_units;
}
