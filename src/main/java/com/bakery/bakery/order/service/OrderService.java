package com.bakery.bakery.order.service;

import com.bakery.bakery.order.dto.ItemDetail;
import com.bakery.bakery.order.dto.OrderRequest;
import com.bakery.bakery.order.dto.PaymentDetail;
import com.bakery.bakery.order.model.Order;
import com.bakery.bakery.service.CrudService;

public interface OrderService extends CrudService<Order, Long> {
    boolean validStock(OrderRequest request);

    PaymentDetail paymentDetail(OrderRequest request);

    ItemDetail createOrder(OrderRequest request);
}
