package com.epam.training.ticketservice.core.checkout.model;

import com.epam.training.ticketservice.core.finance.money.Money;
import com.epam.training.ticketservice.core.room.model.RoomDto;

import java.util.Map;

public record OrderDto(Map<RoomDto, Integer> productMap, Money netPrice, Money grossPrice) {
}
