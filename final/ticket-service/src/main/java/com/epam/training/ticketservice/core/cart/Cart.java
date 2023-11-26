package com.epam.training.ticketservice.core.cart;

import com.epam.training.ticketservice.core.checkout.CheckoutObserver;
import com.epam.training.ticketservice.core.checkout.model.OrderDto;
import com.epam.training.ticketservice.core.finance.bank.Bank;
import com.epam.training.ticketservice.core.finance.money.Money;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cart implements CheckoutObserver {

    private final Bank bank;

    @Getter
    private final Map<RoomDto, Integer> productMap;

    public static Cart createEmptyCart(Bank bank) {
        return new Cart(bank, new HashMap<>());
    }

    public void addProduct(RoomDto roomDto, int amount) {
        if (roomDto != null && amount > 0) {
            productMap.merge(roomDto, amount, Integer::sum);
        }
    }

    public void removeProduct(RoomDto roomDto) {
        productMap.remove(roomDto);
    }

    public boolean containsProduct(RoomDto roomDto) {
        return productMap.containsKey(roomDto);
    }

    public void clear() {
        productMap.clear();
    }

    public boolean isEmpty() {
        return productMap.isEmpty();
    }

    public Money getAggregatedNetPrice() {
        Money aggregatedPrice = new Money(0, Currency.getInstance("HUF"));
        for (Map.Entry<RoomDto, Integer> entry : productMap.entrySet()) {
            aggregatedPrice = aggregatedPrice.add(entry.getKey().getNetPrice().multiply(entry.getValue()), bank);
        }
        return aggregatedPrice;
    }

    @Override
    public void handleOrder(OrderDto orderDto) {
        clear();
    }
}
