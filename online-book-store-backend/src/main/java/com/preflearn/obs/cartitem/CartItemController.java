package com.preflearn.obs.cartitem;

import com.preflearn.obs.cartitem.dto.CartItemRequest;
import com.preflearn.obs.cartitem.dto.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<Void> addBookToCart(
            @RequestBody CartItemRequest cartItemRequest,
            Authentication connectedUser
    ) {
        this.cartItemService.addBookToCart(cartItemRequest, connectedUser);
        return status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getAllCartItems(Authentication connectedUser) {
        return ok(this.cartItemService.getAllCartItemsForCart(connectedUser));
    }

    @DeleteMapping("{cart-item-id}")
    public ResponseEntity<Void> removeBookFromCart(
            @PathVariable("cart-item-id") Long cartItemId,
            Authentication connectedUser
    ) {
        this.cartItemService.removeBookFromCart(cartItemId, connectedUser);
        return ok().build();
    }

    @PatchMapping("quantity/{cart-item-id}")
    public ResponseEntity<Void> updateQuantity(
            @PathVariable("cart-item-id") Long cartItemId,
            @RequestParam(name = "quantity", defaultValue = "1", required = false) int quantity,
            Authentication connectedUser
    ) {
        this.cartItemService.updateQuantity(cartItemId, quantity, connectedUser);
        return ok().build();
    }
}
