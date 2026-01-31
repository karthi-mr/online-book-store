package com.preflearn.obs.order;

import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    public static Specification<Order> getAllOrdersByUserId(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user_id"), userId);
    }

    public static Specification<Order> getAllOrdersByShippingStatus(Long userId, ShippingStatus shippingStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("user").get("id"), userId),
                criteriaBuilder.equal(root.get("shippingStatus"), shippingStatus)
        );
    }
}
