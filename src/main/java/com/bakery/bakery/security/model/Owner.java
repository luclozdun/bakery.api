package com.bakery.bakery.security.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owners")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person{
    
}
