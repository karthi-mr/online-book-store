package com.preflearn.obs.category;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super("Category not found for id: " + categoryId);
    }
}
