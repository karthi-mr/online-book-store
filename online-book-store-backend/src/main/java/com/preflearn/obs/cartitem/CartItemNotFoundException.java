package com.preflearn.obs.cartitem;

public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException(Long cartItemId) {
        super("Cart item with id " + cartItemId + " not found");
    }
}
