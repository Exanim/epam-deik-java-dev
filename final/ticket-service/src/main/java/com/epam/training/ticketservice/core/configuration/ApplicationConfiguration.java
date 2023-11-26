package com.epam.training.ticketservice.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Bank bank() {
        return new StaticBank();
    }

    @Bean
    public Cart cart(Bank bank) {
        return Cart.createEmptyCart(bank);
    }

    @Bean
    public GrossPriceCalculator calculator() {
        return new HungarianTaxGrossPriceDecorator(new GrossPriceCalculatorImpl());
    }
}
