package com.preflearn.obs.order.dto;

import com.preflearn.obs.order.PaymentMethod;
import com.preflearn.obs.order.PaymentStatus;
import com.preflearn.obs.order.ShippingStatus;
import com.preflearn.obs.orderitem.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public record OrderDetailResponse(
        Long orderId,

        String customerName,

        String customerEmail,

        String customerMobile,

        String shippingAddress,

        String shippingCity,

        String shippingState,

        String shippingPincode,

        String shippingCountry,

        ShippingStatus status,

        PaymentMethod paymentMethod,

        PaymentStatus paymentStatus,

        BigDecimal subTotalAmount,

        BigDecimal deliveryAmount,

        BigDecimal totalAmount,

        List<OrderItem> orderItems
) {
}
