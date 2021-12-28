package com.bakery.bakery.membership.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SubscriptionResponse {
    private Long id;
    private String name;
    private String description;
    private ExchangeResponse exchange;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateStart;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateExpirence;
    private Long month;
    private Double price;
}
