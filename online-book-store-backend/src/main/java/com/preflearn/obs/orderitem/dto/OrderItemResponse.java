package com.preflearn.obs.orderitem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderItemResponse(
        Long id,

        Long orderId,

        Long bookId,

        String bookTitle,

        BigDecimal unitPrice,

        int quantity,

        BigDecimal lineTotal,

        LocalDateTime createdAt
) {
}
