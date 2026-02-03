package com.preflearn.obs.cart;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(Long cartId) {
        super("Cart with id " + cartId + " not found");
    }
}
