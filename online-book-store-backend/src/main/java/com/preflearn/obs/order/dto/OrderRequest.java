package com.preflearn.obs.order.dto;

import com.preflearn.obs.order.PaymentMethod;
import com.preflearn.obs.order.PaymentStatus;

import java.math.BigDecimal;

public record OrderRequest(
        String customerName,

        String customerEmail,

        String customerMobile,

        String shippingAddress,

        String shippingCity,

        String shippingState,

        String shippingPincode,

        String shippingCountry,

        PaymentMethod paymentMethod,

        PaymentStatus paymentStatus,

        BigDecimal subTotalAmount,

        BigDecimal deliveryAmount,

        BigDecimal totalAmount
) {
}
