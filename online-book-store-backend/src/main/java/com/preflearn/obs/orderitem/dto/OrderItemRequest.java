package com.preflearn.obs.orderitem.dto;

import java.math.BigDecimal;

public record OrderItemRequest(
        Long orderId,

        Long bookId,

        String bookTitle,

        BigDecimal unitPrice,

        int quantity
) {
}
