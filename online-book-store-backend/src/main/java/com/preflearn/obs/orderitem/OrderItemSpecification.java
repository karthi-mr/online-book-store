package com.preflearn.obs.orderitem;

import org.springframework.data.jpa.domain.Specification;

public class OrderItemSpecification {

    public static Specification<OrderItem> findByOrderId(Long orderId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("order").get("id"), orderId);
    }
}
