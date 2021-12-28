package com.bakery.bakery.security.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bakery.bakery.membership.model.Subscription;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends Person{
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
