package com.bakery.bakery.order.dto;

import java.util.ArrayList;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DictionaryProduct {
    public Map<Long, Long> dictionary;
    public ArrayList<Long> ids;
}
