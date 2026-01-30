package com.preflearn.obs.book.dto;

import com.preflearn.obs.category.dto.CategoryResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookResponse(
        Long id,

        String title,

        String author,

        String isbn,

        String description,

        BigDecimal price,

        Integer stockQuantity,

        byte[] imageUrl,

        boolean isActive,

        LocalDateTime createdAt,

        LocalDateTime lastModifiedAt,

        CategoryResponse category
) {
}
