package com.bakery.bakery.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bakery.bakery.security.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profileReview")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "docDNI")
    private String docDNI;

    @Column(name = "RUC")
    private String ruc;

    @Column(name = "docRUC")
    private String docRUC;

    @Column(name = "docSanitation")
    private String docSanitation;

    @Column(name = "permMunicipa")
    private String permMunicipa;

    @Column(name = "license")
    private String license;

    @Column(name = "name_bakery")
    private String nameBakery;

    @Column(name = "cost")
    private String cost;

    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "process")
    private Long process;

    @Column(name = "message")
    private String message;

    @Column(name = "bakerUsername")
    private String bakerUsername;

    @Column(name = "bakerPassword")
    private String bakerPassword;
}
