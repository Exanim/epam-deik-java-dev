package com.epam.training.ticketservice.core.checkout;

import com.epam.training.ticketservice.core.checkout.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckoutObservable {

    private final List<CheckoutObserver> observerList;

    public void notifyObservers(OrderDto orderDto) {
        observerList.forEach(checkoutObserver -> checkoutObserver.handleOrder(orderDto));
    }
}
