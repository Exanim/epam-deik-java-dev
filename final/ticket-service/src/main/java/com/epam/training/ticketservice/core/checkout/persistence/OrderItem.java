package com.epam.training.ticketservice.core.checkout.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public OrderItem(String name) {
        this.name = name;
    }
}
