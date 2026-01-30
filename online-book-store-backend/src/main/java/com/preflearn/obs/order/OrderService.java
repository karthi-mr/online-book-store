package com.preflearn.obs.order;

import com.preflearn.obs.common.PageResponse;
import com.preflearn.obs.exception.OperationNotPermitted;
import com.preflearn.obs.order.dto.OrderDetailResponse;
import com.preflearn.obs.order.dto.OrderRequest;
import com.preflearn.obs.order.dto.OrderResponse;
import com.preflearn.obs.user.Role;
import com.preflearn.obs.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.preflearn.obs.order.OrderSpecification.getAllOrdersByShippingStatus;
import static com.preflearn.obs.order.OrderSpecification.getAllOrdersByUserId;
import static com.preflearn.obs.order.ShippingStatus.CANCELLED;
import static com.preflearn.obs.order.ShippingStatus.PLACED;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    // place order
    public void placeOrder(OrderRequest orderRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Order order = Order.builder()
                .customerName(orderRequest.customerName())
                .customerEmail(orderRequest.customerEmail())
                .customerMobile(orderRequest.customerMobile())
                .shippingAddress(orderRequest.shippingAddress())
                .shippingCity(orderRequest.shippingCity())
                .shippingState(orderRequest.shippingState())
                .shippingPincode(orderRequest.shippingPincode())
                .shippingCountry(orderRequest.shippingCountry())
                .status(PLACED)
                .paymentMethod(orderRequest.paymentMethod())
                .paymentStatus(orderRequest.paymentStatus())
                .subtotalAmount(orderRequest.subTotalAmount())
                .deliveryAmount(orderRequest.deliveryAmount())
                .totalAmount(orderRequest.totalAmount())
                .user(user)
                .build();
        this.orderRepository.save(order);
    }

    // find all orders history for particular user
    public PageResponse<OrderResponse> findAllMyOrders(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Order> orders = this.orderRepository.findAll(getAllOrdersByUserId(user.getId()), pageable);
        List<OrderResponse> orderResponses = orders.stream()
                .map(this.orderMapper::toOrderResponse)
                .toList();
        return new PageResponse<>(
                orderResponses,
                orders.getNumber(),
                orders.getSize(),
                orders.getTotalElements(),
                orders.getTotalPages(),
                orders.isFirst(),
                orders.isLast()
        );
    }

    // find order in detail
    public OrderDetailResponse findOrderById(Long orderId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        if (order.getUser().getRole() != Role.ADMIN && !order.getUser().getId().equals(user.getId())) {
            throw new OperationNotPermitted("You don't have permission to view this order");
        }
        return this.orderMapper.toOrderDetailResponse(order);
    }

    // find all orders history for all user (Admin)
    public PageResponse<OrderResponse> findAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Order> orders = this.orderRepository.findAll(pageable);
        List<OrderResponse> orderResponses = orders.stream()
                .map(this.orderMapper::toOrderResponse)
                .toList();
        return new PageResponse<>(
                orderResponses,
                orders.getNumber(),
                orders.getSize(),
                orders.getTotalElements(),
                orders.getTotalPages(),
                orders.isFirst(),
                orders.isLast()
        );
    }

    public void updateOrderStatus(Long orderId, ShippingStatus shippingStatus, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        if (shippingStatus == CANCELLED && !order.getUser().getId().equals(user.getId())) {
            throw new OperationNotPermitted("You don't have permission to cancel this order");
        }
        if (user.getRole() != Role.ADMIN) {
            throw new OperationNotPermitted("You don't have permission to update status of this order");
        }
        order.setStatus(shippingStatus);
        this.orderRepository.save(order);
    }

    public PageResponse<OrderResponse> findAllOrderByShippingStatus(
            int page,
            int size,
            Authentication connectedUser,
            ShippingStatus shippingStatus
    ) {
        User user = (User) connectedUser.getPrincipal();
        assert user != null;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Order> orders = this.orderRepository.findAll(
                getAllOrdersByShippingStatus(user.getId(), shippingStatus),
                pageable
        );
        List<OrderResponse> orderResponses = orders.stream()
                .map(this.orderMapper::toOrderResponse)
                .toList();
        return new PageResponse<>(
                orderResponses,
                orders.getNumber(),
                orders.getSize(),
                orders.getTotalElements(),
                orders.getTotalPages(),
                orders.isFirst(),
                orders.isLast()
        );
    }
}
