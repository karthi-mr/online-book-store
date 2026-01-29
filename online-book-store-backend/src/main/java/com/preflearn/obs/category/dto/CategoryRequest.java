package com.preflearn.obs.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(

        @NotBlank(message = "Category name should not be blank")
        String name
) {
}
