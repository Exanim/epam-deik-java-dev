package com.epam.training.ticketservice.core.checkout.persistence;

import com.epam.training.ticketservice.core.user.persistence.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItemList;

    public Order(User user, List<OrderItem> orderItemList) {
        this.user = user;
        this.orderItemList = orderItemList;
    }
}
