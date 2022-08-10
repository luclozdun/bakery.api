package com.bakery.bakery.checkout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueCurrency {
    private String value;
    private String currency_code;
}
