package com.preflearn.obs.order;

import com.preflearn.obs.order.dto.OrderDetailResponse;
import com.preflearn.obs.order.dto.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getCustomerMobile(),
                order.getShippingState(),
                order.getShippingPincode(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getPaymentStatus(),
                order.getTotalAmount()
        );
    }

    public OrderDetailResponse toOrderDetailResponse(Order order) {
        return new OrderDetailResponse(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getCustomerMobile(),
                order.getShippingAddress(),
                order.getShippingCity(),
                order.getShippingState(),
                order.getShippingPincode(),
                order.getShippingCountry(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getPaymentStatus(),
                order.getSubtotalAmount(),
                order.getDeliveryAmount(),
                order.getTotalAmount(),
                order.getOrderItems()
        );
    }
}
