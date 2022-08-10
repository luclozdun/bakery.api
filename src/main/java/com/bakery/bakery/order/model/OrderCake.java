package com.bakery.bakery.order.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bakery.bakery.cake.model.Cake;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_cakes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCake {
    @EmbeddedId
    private OrderCakeId id = new OrderCakeId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cakeId")
    @JoinColumn(name = "cake_id")
    private Cake cake;

    @Column(name = "quantify")
    private Long quantify;

    @Column(name = "priceUnid")
    private Double priceUnid;
}
