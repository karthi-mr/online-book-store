package com.preflearn.obs.order.dto;

import com.preflearn.obs.order.PaymentMethod;
import com.preflearn.obs.order.PaymentStatus;
import com.preflearn.obs.order.ShippingStatus;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,

        String customerName,

        String customerEmail,

        String customerMobile,

        String shippingState,

        String shippingPincode,

        ShippingStatus status,

        PaymentMethod paymentMethod,

        PaymentStatus paymentStatus,

        BigDecimal totalAmount
) {
}
