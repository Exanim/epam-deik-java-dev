package com.epam.training.ticketservice.core.finance.bank;

import com.epam.training.ticketservice.core.finance.bank.model.CurrencyPair;

import java.util.Optional;

public interface Bank {

    Optional<Double> getExchangeRate(CurrencyPair pair);
}
