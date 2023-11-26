package com.epam.training.ticketservice.core.checkout;

import com.epam.training.ticketservice.core.checkout.model.OrderDto;
import com.epam.training.ticketservice.core.checkout.persistence.Order;
import com.epam.training.ticketservice.core.checkout.persistence.OrderItem;
import com.epam.training.ticketservice.core.checkout.persistence.OrderRepository;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.User;
import com.epam.training.ticketservice.core.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements CheckoutObserver {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void handleOrder(OrderDto orderDto) {
        UserDto userDto = userService.describe()
            .orElseThrow(() -> new IllegalArgumentException("You need to first login!"));
        User user = userRepository.findByUsername(userDto.username())
            .orElseThrow(() -> new IllegalArgumentException("No such username!"));
        Order order = new Order(user, createOrderItemList(orderDto));
        orderRepository.save(order);
    }

    private List<OrderItem> createOrderItemList(OrderDto orderDto) {
        return orderDto.productMap()
            .keySet()
            .stream()
            .map(productDto -> new OrderItem(productDto.getName()))
            .toList();
    }
}
