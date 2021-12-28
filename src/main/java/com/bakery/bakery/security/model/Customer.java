package com.bakery.bakery.security.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bakery.bakery.membership.model.Subscription;
import com.bakery.bakery.profile.model.ProfileReview;

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

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ProfileReview profileReview;
}
