package com.preflearn.obs.cartitem;

import com.preflearn.obs.book.Book;
import com.preflearn.obs.cart.Cart;
import com.preflearn.obs.cart.CartRepository;
import com.preflearn.obs.cartitem.dto.CartItemRequest;
import com.preflearn.obs.cartitem.dto.CartItemResponse;
import com.preflearn.obs.exception.OperationNotPermitted;
import com.preflearn.obs.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.preflearn.obs.cartitem.CartItemSpecification.searchCartItemByCartId;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartRepository cartRepository;

    public void addBookToCart(CartItemRequest cartItemRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;
        Cart cart = this.cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user: " + user.getId()));

        CartItem cartItem = CartItem.builder()
                .book(Book.builder().id(cartItemRequest.bookId()).build())
                .cart(Cart.builder().id(cart.getId()).build())
                .quantity(cartItemRequest.quantity())
                .build();
        this.cartItemRepository.save(cartItem);
    }

    private CartItem getCartItem(Long cartItemId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;

        CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException(cartItemId));

        if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new OperationNotPermitted("You don't have permission to delete this item");
        }
        return cartItem;
    }

    public void removeBookFromCart(Long cartItemId, Authentication connectedUser) {
        CartItem cartItem = this.getCartItem(cartItemId, connectedUser);
        this.cartItemRepository.deleteById(cartItem.getId());
    }

    public void updateQuantity(Long cartItemId, int quantity, Authentication connectedUser) {
        CartItem cartItem = this.getCartItem(cartItemId, connectedUser);
        if (cartItem.getQuantity() < quantity) {
            throw new OperationNotPermitted("You don't have permission to update this item");
        }
        cartItem.setQuantity(quantity);
        this.cartItemRepository.save(cartItem);
    }

    public List<CartItemResponse> getAllCartItemsForCart(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;

        Cart cart = this.cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user: " + user.getId()));

        List<CartItem> cartItems = this.cartItemRepository.findAll(searchCartItemByCartId(cart.getId()));
        return cartItems.stream()
                .map(this.cartItemMapper::toCartItemResponse)
                .toList();

    }
}
