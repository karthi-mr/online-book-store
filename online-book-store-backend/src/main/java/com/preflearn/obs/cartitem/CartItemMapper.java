package com.preflearn.obs.cartitem;

import com.preflearn.obs.book.BookMapper;
import com.preflearn.obs.cartitem.dto.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final BookMapper bookMapper;

    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                this.bookMapper.toBookResponse(cartItem.getBook()),
                cartItem.getCart(),
                cartItem.getQuantity(),
                cartItem.getCreatedAt(),
                cartItem.getLastModifiedAt()
        );
    }
}
