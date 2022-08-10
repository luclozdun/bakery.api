package com.bakery.bakery.checkout.service;

import com.bakery.bakery.checkout.dto.PaymentResponse;
import com.bakery.bakery.order.dto.PaymentDetail;

public interface PaymentService {

    PaymentResponse authorizePayment(PaymentDetail request);

}
