package com.bakery.bakery.checkout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Breakdown {
    private ValueCurrency item_total;
}
