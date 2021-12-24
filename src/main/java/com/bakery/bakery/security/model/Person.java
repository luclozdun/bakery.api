package com.bakery.bakery.security.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "image")
    protected String image;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;
    
    @Column(name = "email")
    protected String email;

    @Column(name = "number")
    protected String number;

    @Column(name = "brithday")
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected Date brithday;
}
