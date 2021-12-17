package com.bakery.bakery.cake.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cakess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cake {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "typecake_id")
    private TypeCake typeCake;

    @ManyToOne
    @JoinColumn(name = "covercake_id")
    private CoverCake coverCake;

    @ManyToOne
    @JoinColumn(name = "sizecake_id")
    private SizeCake sizeCake;

    @ManyToOne
    @JoinColumn(name = "tastecake_id")
    private TasteCake tasteCake;

    @Column(name = "quantify")
    private Long quantify;

    @Column(name = "price")
    private Double price;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<FillerCake> fillerCakes;
}