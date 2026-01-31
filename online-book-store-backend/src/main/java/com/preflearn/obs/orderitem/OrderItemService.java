package com.preflearn.obs.orderitem;

import com.preflearn.obs.book.Book;
import com.preflearn.obs.book.BookNotFoundException;
import com.preflearn.obs.book.BookRepository;
import com.preflearn.obs.common.PageResponse;
import com.preflearn.obs.exception.OperationNotPermitted;
import com.preflearn.obs.order.Order;
import com.preflearn.obs.order.OrderNotFoundException;
import com.preflearn.obs.order.OrderRepository;
import com.preflearn.obs.orderitem.dto.OrderItemRequest;
import com.preflearn.obs.orderitem.dto.OrderItemResponse;
import com.preflearn.obs.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.preflearn.obs.orderitem.OrderItemSpecification.findByOrderId;
import static com.preflearn.obs.user.Role.ADMIN;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public void createOrderItem(OrderItemRequest orderItemRequest) {
        Order order = this.orderRepository.findById(orderItemRequest.orderId())
                .orElseThrow(() -> new OrderNotFoundException(orderItemRequest.orderId()));
        Book book = this.bookRepository.findById(orderItemRequest.bookId())
                .orElseThrow(() -> new BookNotFoundException(orderItemRequest.bookId()));
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .book(book)
                .bookTitle(orderItemRequest.bookTitle())
                .unitPrice(orderItemRequest.unitPrice())
                .quantity(orderItemRequest.quantity())
                .lineTotal(BigDecimal.valueOf(orderItemRequest.unitPrice().doubleValue() * orderItemRequest.quantity()))
                .build();
        this.orderItemRepository.save(orderItem);
    }

    public PageResponse<OrderItemResponse> findAllOrderItemsByOrderId(
            int page,
            int size,
            Long orderId,
            Authentication connectedUser
    ) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;
        Pageable pageable = PageRequest.of(page, size);

        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (user.getRole() != ADMIN && !order.getUser().getId().equals(user.getId())) {
            throw new OperationNotPermitted("You are not allowed to view this order.");
        }

        Page<OrderItem> orderItems = this.orderItemRepository.findAll(findByOrderId(order.getId()), pageable);
        List<OrderItemResponse> orderItemResponses = orderItems.stream()
                .map(this.orderItemMapper::toOrderItemResponse)
                .toList();
        return new PageResponse<>(
                orderItemResponses,
                orderItems.getNumber(),
                orderItems.getSize(),
                orderItems.getTotalElements(),
                orderItems.getTotalPages(),
                orderItems.isFirst(),
                orderItems.isLast()
        );
    }
}
