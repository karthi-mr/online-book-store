package com.preflearn.obs.cartitem.dto;

import com.preflearn.obs.book.dto.BookResponse;
import com.preflearn.obs.cart.Cart;

import java.time.LocalDateTime;

public record CartItemResponse(
        Long id,

        BookResponse book,

        Cart cart,

        int quantity,

        LocalDateTime createdAt,

        LocalDateTime lastModifiedAt
) {
}
