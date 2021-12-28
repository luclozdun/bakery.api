package com.bakery.bakery.pie.model;

import lombok.*;

import java.util.List;

import javax.persistence.*;

import com.bakery.bakery.order.model.OrderPie;
import com.bakery.bakery.security.model.Baker;

@Entity
@Table(name = "pies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coverpie_id")
    private CoverPie coverPie;

    @ManyToOne
    @JoinColumn(name = "formpie_id")
    private FormPie formPie;

    @ManyToOne
    @JoinColumn(name = "sizepie_id")
    private SizePie sizePie;

    @ManyToOne
    @JoinColumn(name = "typedought_id")
    private TypeDought typeDought;

    @ManyToOne
    @JoinColumn(name = "typepie_id")
    private TypePie typePie;

    @ManyToOne
    @JoinColumn(name = "baker_id")
    private Baker baker;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantify")
    private Long quantify;

    @OneToMany(mappedBy = "pie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderPie> orderPies;

}
