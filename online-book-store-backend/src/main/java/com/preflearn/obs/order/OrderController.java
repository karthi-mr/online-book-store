package com.preflearn.obs.order;

import com.preflearn.obs.common.PageResponse;
import com.preflearn.obs.order.dto.OrderDetailResponse;
import com.preflearn.obs.order.dto.OrderRequest;
import com.preflearn.obs.order.dto.OrderResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
@Tag(
        name = "Orders",
        description = "Manage orders"
)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("my-orders")
    public ResponseEntity<PageResponse<OrderResponse>> findAllMyOrders(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ok(this.orderService.findAllMyOrders(page, size, connectedUser));
    }

    @GetMapping
    public ResponseEntity<PageResponse<OrderResponse>> findAllOrders(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ok(this.orderService.findAllOrders(page, size));
    }

    @GetMapping("status")
    public ResponseEntity<PageResponse<OrderResponse>> findAllOrderByShippingStatus(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "status") ShippingStatus shippingStatus,
            Authentication connectedUser

    ) {
        return ok(this.orderService.findAllOrderByShippingStatus(page, size, connectedUser, shippingStatus));
    }

    @PostMapping("place-order")
    public ResponseEntity<Void> placeOrder(
            @RequestBody @Valid OrderRequest orderRequest,
            Authentication connectedUser
    ) {
        this.orderService.placeOrder(orderRequest, connectedUser);
        return status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("{order-id}")
    public ResponseEntity<OrderDetailResponse> findOrderById(
            @PathVariable("order-id") Long orderId,
            Authentication connectedUser
    ) {
        return ok(this.orderService.findOrderById(orderId, connectedUser));
    }

    @PatchMapping("update-status/{order-id}")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable("order-id") Long orderId,
            @RequestParam(name = "status") ShippingStatus shippingStatus,
            Authentication connectedUser
    ) {
        this.orderService.updateOrderStatus(orderId, shippingStatus, connectedUser);
        return noContent().build();
    }
}
