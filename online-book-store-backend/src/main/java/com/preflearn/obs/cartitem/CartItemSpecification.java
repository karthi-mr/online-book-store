package com.preflearn.obs.cartitem;

import org.springframework.data.jpa.domain.Specification;

public class CartItemSpecification {

    public static Specification<CartItem> searchCartItemByCartId(Long cartId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cart").get("id"), cartId);
    }
}
