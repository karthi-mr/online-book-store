package com.preflearn.obs.orderitem;

import com.preflearn.obs.orderitem.dto.OrderItemResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getId(),
                orderItem.getOrder().getId(),
                orderItem.getBook().getId(),
                orderItem.getBookTitle(),
                orderItem.getUnitPrice(),
                orderItem.getQuantity(),
                orderItem.getLineTotal(),
                orderItem.getCreatedAt()
        );
    }
}
