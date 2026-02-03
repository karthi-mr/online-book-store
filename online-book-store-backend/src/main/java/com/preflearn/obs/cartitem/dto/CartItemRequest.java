package com.preflearn.obs.cartitem.dto;

public record CartItemRequest(
        Long bookId,

        int quantity
) {
}
