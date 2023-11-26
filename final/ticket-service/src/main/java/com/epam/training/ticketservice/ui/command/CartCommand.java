package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.cart.Cart;
import com.epam.training.ticketservice.core.checkout.CheckoutService;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class CartCommand {

    private final RoomService roomService;
    private final Cart cart;
    private final CheckoutService checkoutService;

    @ShellMethod(key = "user cart addProduct", value = "Add product to your cart.")
    public String addProduct(String productName, Integer amount) {
        Optional<RoomDto> product = roomService.getRoomByName(productName);
        if (product.isEmpty()) {
            return productName + " is not found!";
        } else {
            cart.addProduct(product.get(), amount);
            return productName + " is added to your cart!";
        }
    }

    @ShellMethod(key = "user cart checkout", value = "Checkout the cart.")
    public String checkoutCart() {
        if (cart.isEmpty()) {
            return "You cannot checkout because cart is empty!";
        } else {
            return "Your order " + checkoutService.checkout(cart);
        }
    }

    @ShellMethod(key = "user cart clear", value = "Clear the cart content.")
    public String clearCart() {
        if (cart.isEmpty()) {
            return "You cannot clear your cart because it is empty!";
        } else {
            cart.clear();
            return "The cart is cleared successfully!";
        }
    }

    @ShellMethod(key = "user cart list", value = "List the cart content.")
    public String listCart() {
        if (cart.isEmpty()) {
            return "Cart is empty!";
        }
        return cart.getProductMap().toString();
    }

    @ShellMethod(key = "user cart removeProduct", value = "Remove product from cart.")
    public String removeProduct(String productName) {
        Optional<RoomDto> product = roomService.getRoomByName(productName);
        if (product.isEmpty()) {
            return productName + " is not found!";
        }
        if (cart.containsProduct(product.get())) {
            cart.removeProduct(product.get());
            return "Product removed from the cart!";
        }
        return "Product is not found in the cart!";
    }
}
