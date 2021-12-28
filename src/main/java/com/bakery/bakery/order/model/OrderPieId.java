package com.bakery.bakery.order.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderPieId implements Serializable{
    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "pieId")
    private Long pieId;
}
