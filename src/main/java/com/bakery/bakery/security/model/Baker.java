package com.bakery.bakery.security.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.pie.model.Pie;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bakers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Baker extends Person{
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baker")
    private List<Cake> cakes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baker")
    private List<Pie> pies;
}
