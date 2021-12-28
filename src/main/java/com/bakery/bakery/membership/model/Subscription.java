package com.bakery.bakery.membership.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bakery.bakery.security.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "month")
    private Long month;

    @ManyToOne
    @JoinColumn(name = "exchange")
    private Exchange exchange;

    @Column(name = "date_start")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateStart;

    @Column(name = "date_expirence")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateExpirence;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscription")
    private List<Customer> customers;

    @Column(name = "price")
    private Double price;
}
