package com.preflearn.obs.orderitem;

import com.preflearn.obs.common.PageResponse;
import com.preflearn.obs.orderitem.dto.OrderItemRequest;
import com.preflearn.obs.orderitem.dto.OrderItemResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("order-items")
@RequiredArgsConstructor
@Tag(
        name = "Order Items",
        description = "Managing order items"
)
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<Void> createOrderItem(
            @RequestBody @Valid OrderItemRequest orderItemRequest
    ) {
        this.orderItemService.createOrderItem(orderItemRequest);
        return status(CREATED).build();
    }

    @GetMapping("{order-id}")
    public ResponseEntity<PageResponse<OrderItemResponse>> findAllOrderItemsByOrderId(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable(name = "order-id") Long orderId,
            Authentication connectedUser
    ) {
        return ok(this.orderItemService.findAllOrderItemsByOrderId(page, size, orderId, connectedUser));
    }
}
