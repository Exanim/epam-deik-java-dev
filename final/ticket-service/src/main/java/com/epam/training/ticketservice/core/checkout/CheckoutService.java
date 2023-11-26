package com.epam.training.ticketservice.core.checkout;

import com.epam.training.ticketservice.core.cart.Cart;
import com.epam.training.ticketservice.core.cart.grossprice.GrossPriceCalculator;
import com.epam.training.ticketservice.core.checkout.model.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CheckoutService {

    private final GrossPriceCalculator calculator;
    private final CheckoutObservable checkoutObservable;

    public OrderDto checkout(Cart cart) {
        OrderDto orderDto = new OrderDto(
            cart.getProductMap(), cart.getAggregatedNetPrice(), calculator.getAggregatedGrossPrice(cart)
        );
        checkoutObservable.notifyObservers(orderDto);
        return orderDto;
    }
}
