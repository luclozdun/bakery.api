package com.bakery.bakery.order.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bakery.bakery.pie.model.Pie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_pies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPie {
    @EmbeddedId
    private OrderPieId id = new OrderPieId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pieId")
    @JoinColumn(name = "pie_id")
    private Pie pie;

    @Column(name = "quantify")
    private Long quantify;

    @Column(name = "priceUnit")
    private Double price;
}
