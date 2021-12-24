package com.bakery.bakery.security.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PersonResponse {
    protected Long id;
    protected String name;
    protected String image;
    protected String username;
    protected String password;
    protected String email;
    protected String number;
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected Date brithday;
}
