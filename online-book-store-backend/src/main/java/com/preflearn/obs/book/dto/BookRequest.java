package com.preflearn.obs.book.dto;

import java.math.BigDecimal;

public record BookRequest(
        String title,

        String author,

        String isbn,

        String description,

        BigDecimal price,

        Integer stockQuantity,

        Long categoryId
) {
}
