package com.bakery.bakery.security.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PersonRequest {
    protected String name;
    protected String image;
    protected String username;
    protected String password;
    protected String email;
    protected String number;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    protected Date brithday;
}